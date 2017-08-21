package org.usfirst.frc.team1126.robot.subsystems;

import org.usfirst.frc.team1126.robot.utilities.EncoderData;
import org.usfirst.frc.team1126.robot.RobotMap;
import org.usfirst.frc.team1126.robot.commands.AcqExe;
import org.usfirst.frc.team1126.robot.commands.AcqSafe;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Acquisitions extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static Talon rotatingMotorPWM;
    private static Talon acqRollerPWM;
    private static Solenoid tiltBrake;
    private static DigitalInput upperLimit;
    private static DigitalInput lowerLimit;
    private static DigitalInput ballDetector;
    private static boolean upperLimitSwitch = true;
    private static boolean lowerLimitSwitch = true;
    private static int acquisitionState;
    private static final boolean BRAKE_EXTENDED = false;
    private static double lastUnBrakeTime;
    private static boolean brakePosition;
    private static EncoderData rotateEncoderData;
    private Encoder rotateEncoder;
    private final static double DEGREES_PER_TICK = 0.14064698;
    private static final double UNBRAKE_TIME = 0.2;
    private static double rotationSpeed = 0;
    private static final double TILT_HOLD_POSITION                          = -0.07;
    private static double wantedShooterAngle = 0;
    private static final double UP_POSITION = 0;
    private static double wantedAcqSpeed = 0;
    private static final double PIVOT_THRESHOLD                             = 4;
    private static boolean firstReadyToShot = true;
    private static int wantedState;
    private final static double CENTER_OF_GRAVITY_ANGLE = 25;
    private final static double ROTATE_UP_SPEED = -80;
    private static final double PIVOT_UP_CLOSE_POWER                        = .1;
    private final static int ACQ_ROLLER_ALLOWED_TO_EXTEND = 105;
    private final static int ACQ_ROLLER_ALLOWED_TO_EXTEND_UPPER = 45;
    private static final double DOWN_POSITION = 120;
    private static final double ACQUIRING_THRESHOLD = 110;
    private final static double ROTATE_DOWN_SPEED = 75;
    private final static double INTAKE_ROLLER_SPEED = 1.0;
    private final static double FAR_SHOOTER_PRESET = 58.5;
    private static double lastShooterAngle = FAR_SHOOTER_PRESET;
    private static boolean isBrakeEnabled = true;
    private final static double MID_SHOOTER_PRESET = 48;
    private static double lastCorrectionTime = 0;
    private static boolean manualAcquisition;
    private static final double ERROR_CORRECT_TIME = 0.5;
    private static double lastMotorCheck;
    private static final double MOTOR_OVERHEAT_TIME = 2.0;
    private final static boolean SHORT_SHOT_ACTIVATED = true;
    private static boolean shortShot;
    private final static double TRUSS_SHOOTER_PRESET = 25;
    private final static double ONE_POINT_SHOT_PRESET = 15;
    public static final double TRIM_ANGLE = 4;
    public static boolean isWorking = false;
    
	public Acquisitions() {
        rotatingMotorPWM = new Talon(RobotMap.PWM_PIVOT);
        acqRollerPWM = new Talon(RobotMap.PWM_ACQ);
        tiltBrake = new Solenoid(RobotMap.PNU_BRAKE);
        ballDetector = new DigitalInput(RobotMap.ACQ_BALL_DETECTOR);
        upperLimit = new DigitalInput(RobotMap.SHOOTER_SAFE_MODE_CHAN);
        lowerLimit = new DigitalInput(RobotMap.SHOOTER_ACQ_MODE_CHAN);
        rotateEncoder = new Encoder(RobotMap.PIVOT_ENCODER_CHAN_1, RobotMap.PIVOT_ENCODER_CHAN_2, false);
        rotateEncoder.setDistancePerPulse(DEGREES_PER_TICK);
        rotateEncoderData = new EncoderData(rotateEncoder, DEGREES_PER_TICK);
        
        setMode(AcqState.SAFE_STATE);
        return;
	}
	
    public static boolean execute() {
    	rotateEncoderData.calculateSpeed();
        upperLimitSwitch = !upperLimit.get();
        lowerLimitSwitch = !lowerLimit.get();
        switch (acquisitionState) {
            case AcqState.ROTATE_UP:
                if(tiltBrake.get() == BRAKE_EXTENDED){
                    lastUnBrakeTime = Timer.getFPGATimestamp();
                    brakePosition = !BRAKE_EXTENDED;
                    break;
                }else if(Timer.getFPGATimestamp() - lastUnBrakeTime < UNBRAKE_TIME){
                    rotationSpeed = -TILT_HOLD_POSITION;
                    break;
                }
                if (wantedShooterAngle == UP_POSITION && (lowerLimitSwitch|| 
                        (rotateEncoderData.getDistance() > ACQUIRING_THRESHOLD))) {
                    rotationSpeed = 0;
                    acquisitionState = wantedState;
                } else if (wantedShooterAngle - PIVOT_THRESHOLD <= rotateEncoderData.getDistance()) {
                    rotationSpeed = 0;
                    firstReadyToShot = true;
                    acquisitionState = wantedState;
                } else {
                    if (rotateEncoderData.getSpeed() < ROTATE_UP_SPEED) {
                        rotationSpeed -= .05;
                    } else {
                        rotationSpeed += .1;
                    }
                    if (rotationSpeed > 1) {
                        rotationSpeed = 1;
                    } else if (rotationSpeed < -1) {
                        rotationSpeed = -1;
                    }
                }
                
                break;
            case AcqState.ROTATE_DOWN:
                if(tiltBrake.get() == BRAKE_EXTENDED){
                    lastUnBrakeTime = Timer.getFPGATimestamp();
                    brakePosition = !BRAKE_EXTENDED;
                    break;
                }else if(Timer.getFPGATimestamp() - lastUnBrakeTime < UNBRAKE_TIME){
                    rotationSpeed = -TILT_HOLD_POSITION;
                    break;
                }
                
                if (wantedShooterAngle == DOWN_POSITION && (lowerLimitSwitch|| 
                        (rotateEncoderData.getDistance() > ACQUIRING_THRESHOLD))) {
                    rotationSpeed = 0;
                    acquisitionState = wantedState;
                } else if (wantedShooterAngle - PIVOT_THRESHOLD <= rotateEncoderData.getDistance()) {
                    rotationSpeed = 0;
                    firstReadyToShot = true;
                    acquisitionState = wantedState;
                } else {
                    if (rotateEncoderData.getSpeed() < ROTATE_DOWN_SPEED) {
                        rotationSpeed -= .05;
                    } else {
                        rotationSpeed += .1;
                    }
                    if (rotationSpeed > 1) {
                        rotationSpeed = 1;
                    } else if (rotationSpeed < -1) {
                        rotationSpeed = -1;
                    }
                }
                
                break;
            case AcqState.READY_TO_SHOOT:
                if(wantedShooterAngle == FAR_SHOOTER_PRESET){
                    wantedShooterAngle = FAR_SHOOTER_PRESET - 3.0;
                }
                lastShooterAngle = wantedShooterAngle;
                rotationSpeed = (rotateEncoderData.getDistance() - wantedShooterAngle) / 7.5;
                if (isBrakeEnabled && wantedShooterAngle != MID_SHOOTER_PRESET) {
                    if (firstReadyToShot) {
                        lastCorrectionTime = Timer.getFPGATimestamp();
                        firstReadyToShot = false;
                    } else if (Timer.getFPGATimestamp() - lastCorrectionTime >= ERROR_CORRECT_TIME + .25){
                        rotationSpeed = TILT_HOLD_POSITION;
                    } else if (Timer.getFPGATimestamp() - lastCorrectionTime >= ERROR_CORRECT_TIME) {
                        brakePosition = BRAKE_EXTENDED;
                    }
                }else{
                    brakePosition = !BRAKE_EXTENDED;
                }
                break;
            case AcqState.SAFE_STATE:
                if(rotateEncoderData.getDistance() < 0)
                    rotateEncoderData.reset();
                break;
            case AcqState.OFF_STATE:
                rotationSpeed = 0;
                break;
        }

        if(brakePosition != tiltBrake.get()) 
        tiltBrake.set(brakePosition);
        
        
        if(Math.abs(rotationSpeed) > -TILT_HOLD_POSITION && Math.abs(rotateEncoderData.getSpeed()) < 1.0 && 
                Timer.getFPGATimestamp() - lastMotorCheck > MOTOR_OVERHEAT_TIME){
            setPivotMotor(0);
            acquisitionState = AcqState.OFF_STATE;
        } else {
            if (Math.abs(rotationSpeed) <= -TILT_HOLD_POSITION) {
                lastMotorCheck = Timer.getFPGATimestamp();
            } 
            setPivotMotor(rotationSpeed);            
        }
        return false;
    }
    
    public static boolean isBrakeEnabled() {
        return isBrakeEnabled;
    }
    
    public static void setBrakeEnabled(boolean enabled){
        isBrakeEnabled = enabled;
    }
    
    public static void addOffset(double offset){
        wantedShooterAngle += offset;
        if(wantedShooterAngle < UP_POSITION){
            wantedShooterAngle = UP_POSITION;
        }else if(wantedShooterAngle > DOWN_POSITION){
            wantedShooterAngle = DOWN_POSITION;
        }
        if(offset < 0){
            acquisitionState = AcqState.ROTATE_UP;
        }else{
            acquisitionState = AcqState.ROTATE_DOWN;
        }
        wantedState = AcqState.READY_TO_SHOOT;
    }
    
    private static void setPivotMotor(double speed){
        rotatingMotorPWM.set(speed);
    }
    
    private static void setAcquiringMotor(double value){
        acqRollerPWM.set(value);
    }
    
    public static void setMode(int state) {
        wantedState = state;
        switch (state) {
            case AcqState.ACQUIRING:
                wantedShooterAngle = DOWN_POSITION;
                acquisitionState = AcqState.ROTATE_DOWN;
                break;
            case AcqState.SAFE_STATE:
                wantedShooterAngle = UP_POSITION;
                acquisitionState = AcqState.ROTATE_UP;
                break;
            case AcqState.READY_TO_SHOOT:
                setAngle(lastShooterAngle);
                break;
            case AcqState.EJECT_BALL:
                wantedShooterAngle = DOWN_POSITION;//Acquiring
                acquisitionState = AcqState.ROTATE_DOWN;
                break;
            case AcqState.OFF_STATE:
                acquisitionState = AcqState.OFF_STATE;
                break;

        }
    }
    
    private static void setAngle(double angle){
        if(wantedState == AcqState.READY_TO_SHOOT){
            wantedShooterAngle = angle;
            if(rotateEncoderData.getDistance() > wantedShooterAngle){
                acquisitionState = AcqState.ROTATE_UP;
            }else{
                acquisitionState = AcqState.ROTATE_DOWN;
            }
        }
    }
    
    public static class AcqState{
        public static final int ROTATE_UP = 1;
        public static final int ROTATE_DOWN = 2;
        public static final int ACQUIRING = 4;
        public static final int ACQUIRED = 5;
        public static final int EJECT_BALL = 6;
        public static final int READY_TO_SHOOT = 9;
        public static final int SAFE_STATE = 10;
        public static final int OFF_STATE = 11;
        
        public static final int TRUSS_SHOT_PRESET = 20;
        public static final int ONE_POINT_PRESET = 21;
        public static final int MIDDLE_SHOOTER_PRESET = 22;
        public static final int FAR_SHOOTER_PRESET = 23;
        
        /**
         * @param state - the state to get the string version of
         * @return A string version of the state.
         */
        public static String getStateName(int state){
            switch(state){
                case ROTATE_UP:
                    return "Rotating Up";
                case ROTATE_DOWN:
                    return "Rotating Down";
                case READY_TO_SHOOT:
                    return "Ready to Shoot";
                case SAFE_STATE:
                    return "Safe State";
                case OFF_STATE:
                    return "Off State";
                default:
                    return "UNKNOWN";
            }
        }
    }
    
    public static void setManualAcq(boolean acqOn){
        manualAcquisition = acqOn;
    }
    
    public static void setPreset(int preset){
        shortShot = !SHORT_SHOT_ACTIVATED;
        wantedState = AcqState.READY_TO_SHOOT;
        switch(preset){
            case AcqState.TRUSS_SHOT_PRESET:
                setAngle(TRUSS_SHOOTER_PRESET);
                break;
            case AcqState.ONE_POINT_PRESET:
                setAngle(ONE_POINT_SHOT_PRESET);
                shortShot = SHORT_SHOT_ACTIVATED;
                break;
            case AcqState.MIDDLE_SHOOTER_PRESET:
                setAngle(MID_SHOOTER_PRESET);
                break;
            case AcqState.FAR_SHOOTER_PRESET:
                setAngle(FAR_SHOOTER_PRESET);
                break;
            default:
                wantedShooterAngle = UP_POSITION;
        }
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new AcqExe());
    }
}


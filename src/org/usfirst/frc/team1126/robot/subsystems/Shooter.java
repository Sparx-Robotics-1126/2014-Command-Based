package org.usfirst.frc.team1126.robot.subsystems;

import org.usfirst.frc.team1126.robot.RobotMap;
import org.usfirst.frc.team1126.robot.utilities.PotentiometerData;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private static Talon rightWinchMotor;
	private static Talon leftWinchMotor;
	private static DigitalInput latchSwitch;
	private static Solenoid latch;
	private static PotentiometerData potData;
	public static int shooterState;
	private AnalogPotentiometer winchPot;
    private static final boolean LATCH_ENGAGED = false;
    private static double wantedWinchSpeed;
    private static boolean limitSwitchValue;
    private static final boolean LATCH_DISENGAGED = !LATCH_ENGAGED;
    private static double lastShotTime;
    private static boolean lastShotWound = false;
    public static final double MAX_UNWIND_INCHES = 14;
    private static final double TIME_BETWEEN_SHOTS = 1.00;
    private static final double WINCH_SPEED = -1.00;
    private static double lastUnwindTime;
    private static final double LATCH_TIME = 0.5;
    private static final double UNWIND_TIMEOUT = 2; //original 5
    private static double inchesUnwound;
    private static final double INCHES_PER_VOLT = -12.566370614359172953850573533118;
    private static double potInches = 0;
    public static final double MIN_UNWIND_INCHES = 1.5;
    public static double lastOffsetTime;
    public static final double OFFSET_TIME = 0.5;
    private static final double HALF_WINCH_SPEED = -0.5;
    
	public Shooter() {
	        rightWinchMotor = new Talon(RobotMap.PWM_RIGHT_WINCH);
	        latchSwitch = new DigitalInput(RobotMap.LATCH_LIMIT_SWITCH_CHAN);
	        latch = new Solenoid(RobotMap.LATCH_CHAN);
	        latch.set(LATCH_ENGAGED);
	        shooterState = State.STANDBY;
	        winchPot = new AnalogPotentiometer(RobotMap.WINCH_POT_CHAN);
	        potData = new PotentiometerData(winchPot, INCHES_PER_VOLT);
	        leftWinchMotor = new Talon(RobotMap.PWM_LEFT_WINCH);
	        if(!latchSwitch.get()){
	        	System.out.println("reset");
	            potData.reset();
	        }
	        return;
	}
    public static boolean execute()
    {
        potInches = -potData.getInches();
        wantedWinchSpeed = 0;
        limitSwitchValue = !latchSwitch.get();
        switch(shooterState)
        {
            case State.SHOOT:
                latch.set(LATCH_DISENGAGED);
                lastShotTime = Timer.getFPGATimestamp();
                shooterState = State.SHOOTER_COOLDOWN;
                lastShotWound = potInches < MAX_UNWIND_INCHES/2;
                break;
            case State.STANDBY:
                latch.set(LATCH_ENGAGED);
                wantedWinchSpeed = 0;
                break;
            case State.SHOOTER_COOLDOWN:
                if (Timer.getFPGATimestamp() - lastShotTime >= TIME_BETWEEN_SHOTS)
                {
                        shooterState = State.STANDBY;
                }
                
                break;
        	case State.SET_HOME:
        		latch.set(LATCH_DISENGAGED);
        		wantedWinchSpeed = WINCH_SPEED;
        				if(limitSwitchValue){
        					shooterState = State.HOLD_WINCH;
        					potData.reset();
        					lastUnwindTime = Timer.getFPGATimestamp();
        				}
        				break;
            case State.HOLD_WINCH:
                latch.set(LATCH_ENGAGED);
                wantedWinchSpeed = WINCH_SPEED;
                if(Timer.getFPGATimestamp() - lastUnwindTime >= LATCH_TIME){
                    lastUnwindTime = Timer.getFPGATimestamp();
                    wantedWinchSpeed = 0;
                    shooterState = State.UNWINDING;
                }
                break;
            case State.UNWINDING:
                wantedWinchSpeed = -HALF_WINCH_SPEED;
                System.out.println("unwinding");
                if((Timer.getFPGATimestamp() - lastUnwindTime >= UNWIND_TIMEOUT)/* || potInches >= MAX_UNWIND_INCHES*/){
                	System.out.println("finishedunwind");
                    wantedWinchSpeed = 0;
                    shooterState = State.STANDBY;
                }
                break;
           case State.SHOOTER_WINDING:
                wantedWinchSpeed = -(Math.abs(potInches - inchesUnwound) / 7.5) - 0.20;
                if (potInches <= inchesUnwound - 0.05) {
                	System.out.println("finished winding");
                    wantedWinchSpeed = 0;
                    shooterState = State.STANDBY;
                }
                break;
            case State.SHOOTER_UNWINDING:
                wantedWinchSpeed = (Math.abs(potInches - inchesUnwound) / 7.5) + 0.20;
                if(potInches >= inchesUnwound + 0.05){
                    wantedWinchSpeed = 0;
                    shooterState = State.STANDBY;
                }
                break;
            default:
                break;
        }
        rightWinchMotor.set(wantedWinchSpeed);
        leftWinchMotor.set(-wantedWinchSpeed);
        return false;
    }
    public void initDefaultCommand() {
        setDefaultCommand(new org.usfirst.frc.team1126.robot.commands.ShootExe());
    }
    
    public static void setMode(int wantedState){
        shooterState = wantedState;
    }
    
    public static boolean shoot(){
        if(shooterState == State.STANDBY) { 
            shooterState = State.SHOOT;
            return true;
        }
        return false;
     }
    
    public static void setAdjustSlack(double inchesUnwound){
        Shooter.inchesUnwound = inchesUnwound;
        if(inchesUnwound < potInches){
            shooterState = State.SHOOTER_WINDING;
        }else{
            shooterState = State.SHOOTER_UNWINDING;
        }
    }
    public static class State{
        public static final int SHOOT = 1;
        public static final int STANDBY = 3;
        public static final int SHOOTER_COOLDOWN = 4;
        public static final int SET_HOME = 5;
        public static final int HOLD_WINCH = 6;
        public static final int UNWINDING = 7;
        public static final int SHOOTER_WINDING = 8;
        public static final int SHOOTER_UNWINDING = 9;
        
        /**
         * Returns a string version of the state. 
         */
        public static String getState(int state){
            switch(state){
                case STANDBY:
                    return "Standby";
                case SHOOT:
                    return "Shooting";
                case SHOOTER_COOLDOWN:
                    return "Shooter is cooling down";
                case SET_HOME:
                    return "Setting home";
                case HOLD_WINCH:
                    return "Holding Winch";
                case UNWINDING:
                    return "Unwinding Winch";
                case SHOOTER_WINDING:
                    return "Winding Winch";
                case SHOOTER_UNWINDING:
                    return "Shooter Unwinding";
            }
            return "UNKNOWN MODE: " + state;
        }
    }
    
}


package org.usfirst.frc.team1126.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	//these are drives
    public static final int LEFT_FRONT_DRIVES_PWM       = 3;
    public static final int LEFT_REAR_DRIVES_PWM        = 4;
    public static final int RIGHT_FRONT_DRIVES_PWM      = 6;
    public static final int RIGHT_REAR_DRIVES_PWM       = 7;
    public static final int LEFT_DRIVER_JOY_PORT        = 0;
    public static final int RIGHT_DRIVER_JOY_PORT       = 1;
    public static final int OPER_JOY_PORT               = 2;
    
    public static final double JOYSTICK_DEADZONE        = 0.04;
    
    public static final int PWM_RIGHT_WINCH                   = 5;
    public static final int LATCH_LIMIT_SWITCH_CHAN     = 4;
    public static final int LATCH_CHAN                  = 3;
    public static final int WINCH_POT_CHAN              = 3;
    public static final int PWM_LEFT_WINCH                 = 0;
    
	public static final int XBOX_A = 1;
	public static final int XBOX_B = 2;
	public static final int XBOX_X = 3;
	public static final int XBOX_Y = 4;
	public static final int XBOX_L1 = 5;
	public static final int XBOX_R1 = 6;
	public static final int XBOX_BACK = 7;
	public static final int XBOX_START = 8;
	public static final int XBOX_L3 = 9;
	public static final int XBOX_R3 = 10;
	public static final int XBOX_LEFT_X = 0;
	public static final int XBOX_LEFT_Y = 1;
	public static final int XBOX_L2 = 2;
	public static final int XBOX_R2 = 3;
	public static final int XBOX_RIGHT_X = 4;
	public static final int XBOX_RIGHT_Y = 5;
	public static final int XBOX_POV = 0;
    
    public static final int DPAD_DOWN = 180;
    public static final int DPAD_UP = 0;
    public static final int DPAD_LEFT = 90;
    public static final int DPAD_RIGHT = 270;
    
    public static final int PWM_PIVOT                   = 2;
    public static final int PWM_ACQ                     = 1;
    public static final int PNU_BRAKE                   = 5;
    public static final int ACQ_TOGGLE_CHAN             = 1;
    public static final int KEEP_IN_FRAME_CHAN          = 2;
    public static final int ACQ_BALL_DETECTOR           = 5;
    public static final int SHOOTER_SAFE_MODE_CHAN      = 2;
    public static final int SHOOTER_ACQ_MODE_CHAN       = 3;

    public static final int PIVOT_ENCODER_CHAN_1        = 0;
    public static final int PIVOT_ENCODER_CHAN_2        = 1;
    
    public static final double XBOX_R2_THRESHOLD           = 0.80;
    public static final int XBOX_LY1_THRESHOLD       = 1;
    public static final int XBOX_LYN1_THRESHOLD      = -1;
    public static final double XBOX_L2_THRESHOLD        = 0.80;
    public static final double XBOX_RY_THRESHOLD        = 0.5; 
}

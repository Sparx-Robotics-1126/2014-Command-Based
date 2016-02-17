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
    //these r joystick points
    public static final int LEFT_DRIVER_JOY_PORT        = 0;
    public static final int RIGHT_DRIVER_JOY_PORT       = 1;
    public static final int OPER_JOY_PORT               = 2;
    
    public static final double JOYSTICK_DEADZONE        = 0.04;
}

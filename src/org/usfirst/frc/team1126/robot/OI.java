package org.usfirst.frc.team1126.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	//MAKE SURE THAT JOYSTICKS ARE IN SLOTS 0&1, XBOX CONTROLLER IN 2
    public static Joystick leftJoy = new Joystick(RobotMap.LEFT_DRIVER_JOY_PORT);
    static Joystick rightJoy = new Joystick(RobotMap.RIGHT_DRIVER_JOY_PORT);
    public static Joystick operatorJoy = new Joystick(RobotMap.OPER_JOY_PORT);
    public static double getOpJoyLY() {
        return operatorJoy.getRawAxis(1);
    }
    
    public static double getOpJoyRY() {
        return operatorJoy.getRawAxis(5);
    }  
    
    public static double getleftJoyY() {
    	return leftJoy.getY();
    }
    
    public static double getrightJoyY() {
    	return rightJoy.getY();
    }
    
}


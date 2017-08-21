package org.usfirst.frc.team1126.robot;

import org.usfirst.frc.team1126.robot.commands.AcqAddTrim;
import org.usfirst.frc.team1126.robot.commands.AcqAquire;
import org.usfirst.frc.team1126.robot.commands.AcqAuto;
import org.usfirst.frc.team1126.robot.commands.AcqBrake;
import org.usfirst.frc.team1126.robot.commands.AcqEject;
import org.usfirst.frc.team1126.robot.commands.AcqMan;
import org.usfirst.frc.team1126.robot.commands.AcqMinTrim;
import org.usfirst.frc.team1126.robot.commands.AcqOff;
import org.usfirst.frc.team1126.robot.commands.AcqPresets;
import org.usfirst.frc.team1126.robot.commands.AcqSafe;
import org.usfirst.frc.team1126.robot.commands.FarMax;
import org.usfirst.frc.team1126.robot.commands.Shoot;
import org.usfirst.frc.team1126.robot.commands.ShootHome;
import org.usfirst.frc.team1126.robot.commands.ShootMax;
import org.usfirst.frc.team1126.robot.commands.ShootMin;
import org.usfirst.frc.team1126.robot.commands.ShootStand;
import org.usfirst.frc.team1126.robot.commands.ShootUnwind;
import org.usfirst.frc.team1126.robot.commands.ShootWind;
import org.usfirst.frc.team1126.robot.utilities.Joy2Button;
import org.usfirst.frc.team1126.robot.utilities.POV;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

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
    public POV XBOX_DOWN = new POV(operatorJoy, RobotMap.XBOX_POV, RobotMap.DPAD_DOWN);
    public POV XBOX_UP = new POV(operatorJoy, RobotMap.XBOX_POV, RobotMap.DPAD_UP);
    public POV XBOX_LEFT = new POV(operatorJoy, RobotMap.XBOX_POV, RobotMap.DPAD_LEFT);
    public POV XBOX_RIGHT = new POV(operatorJoy, RobotMap.XBOX_POV, RobotMap.DPAD_RIGHT);
    public Joy2Button XBOX_R2 = new Joy2Button(operatorJoy, RobotMap.XBOX_R2, RobotMap.XBOX_R2_THRESHOLD, false);
    public Joy2Button XBOX_LY1 = new Joy2Button(operatorJoy, RobotMap.XBOX_LEFT_Y, RobotMap.XBOX_LY1_THRESHOLD, true);
    public Joy2Button XBOX_LYN1 = new Joy2Button(operatorJoy, RobotMap.XBOX_LEFT_Y, RobotMap.XBOX_LYN1_THRESHOLD, true);
    public Joy2Button XBOX_L2 = new Joy2Button(operatorJoy, RobotMap.XBOX_L2, RobotMap.XBOX_L2_THRESHOLD, false);
    public Joy2Button XBOX_RYA = new Joy2Button(operatorJoy, RobotMap.XBOX_RIGHT_Y, RobotMap.XBOX_RY_THRESHOLD, false);
    public Joy2Button XBOX_RYN = new Joy2Button(operatorJoy, RobotMap.XBOX_RIGHT_Y, RobotMap.XBOX_RY_THRESHOLD, true);
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
    public static double getOpJoyDPAD() {
    	return operatorJoy.getPOV();
    }
    public static double getOPJoyL2() {
    	return operatorJoy.getRawAxis(2);
    }
    public static double getOPJoyR2() {
    	return operatorJoy.getRawAxis(3);
    }
    Button XBOX_A = new JoystickButton(operatorJoy, RobotMap.XBOX_A);
    Button XBOX_B = new JoystickButton(operatorJoy, RobotMap.XBOX_B);
    Button XBOX_X = new JoystickButton(operatorJoy, RobotMap.XBOX_X);
    Button XBOX_Y = new JoystickButton(operatorJoy, RobotMap.XBOX_Y);
    Button XBOX_START = new JoystickButton(operatorJoy, RobotMap.XBOX_START);
    Button XBOX_L3 = new JoystickButton(operatorJoy, RobotMap.XBOX_L3);
    Button XBOX_R3 = new JoystickButton(operatorJoy, RobotMap.XBOX_R3);
    Button XBOX_R1 = new JoystickButton(operatorJoy, RobotMap.XBOX_R1);
    Button XBOX_L1 = new JoystickButton(operatorJoy, RobotMap.XBOX_L1);
    Button XBOX_BACK = new JoystickButton(operatorJoy, RobotMap.XBOX_BACK);		
    //Trigger XBOX_POV = new JoystickButton(operatorJoy, RobotMap.XBOX_POV);
    
    public OI() {
    	System.out.println(getOPJoyR2());
    	XBOX_A.whenPressed(new AcqOff());
    	//XBOX_B.whenPressed(new AcqAquire());
    	XBOX_B.whenPressed(new ShootUnwind());
    	//TEMP
    	XBOX_X.whenPressed(new AcqSafe());
    	XBOX_Y.whenPressed(new AcqEject());
    	XBOX_START.whenPressed(new FarMax());
    	//XBOX_L3.whenPressed(new ShootWind());
    	//XBOX_R3.whenPressed(new ShootStand());
    	XBOX_DOWN.whenPressed(new AcqPresets());
    	XBOX_UP.whenPressed(new AcqPresets());
    	XBOX_LEFT.whenPressed(new AcqPresets());
    	XBOX_RIGHT.whenPressed(new AcqPresets());
    	XBOX_R1.whenPressed(new ShootHome());
    	XBOX_R2.whenPressed(new Shoot());
    	XBOX_LY1.whenPressed(new ShootMin());
    	XBOX_LYN1.whenPressed(new ShootMax());
    	XBOX_L1.whenPressed(new AcqMinTrim());
    	XBOX_L2.whenPressed(new AcqAddTrim());
    	XBOX_BACK.whenPressed(new AcqBrake());
    	XBOX_RYA.whenPressed(new AcqAuto());
    	XBOX_RYN.whenPressed(new AcqMan());
    }
}


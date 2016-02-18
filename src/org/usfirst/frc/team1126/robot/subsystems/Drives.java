package org.usfirst.frc.team1126.robot.subsystems;

import org.usfirst.frc.team1126.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Hello LOfgan
 */
public class Drives extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private static Talon leftFront;
	private static Talon leftRear;
	private static Talon rightFront;
	private static Talon rightRear;
	public static double wantedLeft;
	public static double wantedRight;
	public static Drives drives;
	//why is this here?
    public static Drives getInstance() {
        if (drives == null) {
            drives = new Drives();
        }
        return drives;
    }
	//set speeds
	public static void setSpeed(double left, double right){
		wantedLeft = -left;
		wantedRight = right;
		wantedLeft = getSpeed(wantedLeft);
		wantedRight = getSpeed(wantedRight);
	}
    //init talons
	public Drives() {
		leftFront = new Talon(RobotMap.LEFT_FRONT_DRIVES_PWM);
		leftRear = new Talon(RobotMap.LEFT_REAR_DRIVES_PWM);
		rightFront = new Talon(RobotMap.RIGHT_FRONT_DRIVES_PWM);
		rightRear = new Talon(RobotMap.RIGHT_REAR_DRIVES_PWM);
	}
	//DRIVE
	public static void Driving(){
		leftFront.set(wantedLeft);
		leftRear.set(wantedLeft);
		rightFront.set(wantedRight);
		rightRear.set(wantedRight);
		
	}
	//Attempts at deadzoning. May not work.
	private static double getSpeed(double joystickValue) {
		if (Math.abs(joystickValue) < RobotMap.JOYSTICK_DEADZONE) {
			joystickValue = 0;
		}
		return joystickValue;
	}
	//set default command to driving
    public void initDefaultCommand() {
    	setDefaultCommand(new org.usfirst.frc.team1126.robot.commands.Driving());
    }
}


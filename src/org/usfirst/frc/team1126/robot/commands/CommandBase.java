package org.usfirst.frc.team1126.robot.commands;

import org.usfirst.frc.team1126.robot.OI;
import org.usfirst.frc.team1126.robot.subsystems.Acquisitions;
import org.usfirst.frc.team1126.robot.subsystems.Drives;
import org.usfirst.frc.team1126.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public abstract class CommandBase extends Command {

    
    public static OI oi; //Operation Interface - Associates OI Buttons with
    //commands and groups
	
    //public static BadRobotMap map; //It tells us what port (a port is one of the 
    //many places in that blue thingy on the robot >.<'). The port is associated 
    //with hardware parts. Say port one is the FrontLeftJaguar (a jaguar is 
    //a speed controller, controls voltage to the motor, hence speed of motor).
    //So the map can be used to access hardware components on the robot.
    public static Drives drives = new Drives();
    public static Shooter shooter = new Shooter();
    public static Acquisitions acquisitions = new Acquisitions();
	public static void init() {
		oi = new OI();
	}
}

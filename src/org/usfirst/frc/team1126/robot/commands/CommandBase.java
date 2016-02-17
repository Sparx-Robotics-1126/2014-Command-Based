package org.usfirst.frc.team1126.robot.commands;

import org.usfirst.frc.team1126.robot.OI;
import org.usfirst.frc.team1126.robot.subsystems.Drives;

import edu.wpi.first.wpilibj.command.Command;

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
    //So the map can be used to access harware components on the robot.
    public static Drives drives = new Drives();

	public static void init() {
		oi = new OI();
	}
}

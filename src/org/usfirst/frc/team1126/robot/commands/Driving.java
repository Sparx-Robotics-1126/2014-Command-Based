package org.usfirst.frc.team1126.robot.commands;

import org.usfirst.frc.team1126.robot.OI;
import org.usfirst.frc.team1126.robot.Robot;
import org.usfirst.frc.team1126.robot.subsystems.Drives;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Driving extends Command {

    public Driving() {
    	requires(CommandBase.drives);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    //basically what this does is it chooses joysticks if there is no xBox controller, but disabled because it shows warnings.
    //	if(OI.operatorJoy.getType() != -1) {
    		Drives.setSpeed(OI.getOpJoyLY(), OI.getOpJoyRY());
    	//}
    	//else {
    	//	Drives.setSpeed(OI.getleftJoyY(), OI.getrightJoyY());
    //	}
    	Drives.Driving();
    }
    	
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

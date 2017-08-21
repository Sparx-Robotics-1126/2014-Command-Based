package org.usfirst.frc.team1126.robot.commands;

import org.usfirst.frc.team1126.robot.subsystems.Acquisitions;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AcqOff extends Command {

    public AcqOff() {
    	requires(CommandBase.acquisitions);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Acquisitions.setMode(Acquisitions.AcqState.OFF_STATE);
    }
    //CAUTION

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

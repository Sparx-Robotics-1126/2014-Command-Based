package org.usfirst.frc.team1126.robot.commands;

import org.usfirst.frc.team1126.robot.subsystems.Acquisitions;
import org.usfirst.frc.team1126.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FarMax extends Command {

    public FarMax() {
        requires(CommandBase.acquisitions);
        requires(CommandBase.shooter);
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
        Acquisitions.setPreset(Acquisitions.AcqState.FAR_SHOOTER_PRESET);
        Shooter.setAdjustSlack(Shooter.MAX_UNWIND_INCHES);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team1126.robot.commands;

import org.usfirst.frc.team1126.robot.OI;
import org.usfirst.frc.team1126.robot.RobotMap;
import org.usfirst.frc.team1126.robot.subsystems.Acquisitions;
import org.usfirst.frc.team1126.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AcqPresets extends Command {

    public AcqPresets() {
    	requires(CommandBase.acquisitions);
    	requires(CommandBase.shooter);
    }
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

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
    	//System.out.println(OI.getOpJoyDPAD());
        if (OI.getOpJoyDPAD() == RobotMap.DPAD_DOWN) {
            Acquisitions.setPreset(Acquisitions.AcqState.FAR_SHOOTER_PRESET);
            //Shooter.setAdjustSlack(Shooter.MAX_UNWIND_INCHES);
        } else if (OI.getOpJoyDPAD() == RobotMap.DPAD_RIGHT) {
            Acquisitions.setPreset(Acquisitions.AcqState.MIDDLE_SHOOTER_PRESET);
            //Shooter.setAdjustSlack(Shooter.MAX_UNWIND_INCHES);
        } else if (OI.getOpJoyDPAD() == RobotMap.DPAD_UP) {
            Acquisitions.setPreset(Acquisitions.AcqState.ONE_POINT_PRESET);
            //Shooter.setAdjustSlack(Shooter.MIN_UNWIND_INCHES + 0.85);
        } else if (OI.getOpJoyDPAD() == RobotMap.DPAD_LEFT) {
            Acquisitions.setPreset(Acquisitions.AcqState.TRUSS_SHOT_PRESET);
            //Shooter.setAdjustSlack(2.75);
        }
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

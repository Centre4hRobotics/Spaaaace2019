/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

/**
 * An example command. You can replace me with your own command.
 */
public class DriveBackClimber extends Command {
    private double height;
    public DriveBackClimber(double height) {
        // Use requires() here to declare subsystem dependencies
        this.height = height;
        requires(Robot.get().getClimber());
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        //diff is right minus left
        double mult = 1;
        if (Robot.get().getClimber().getEncoderBL().getDistance()-height>0) mult = -1;
        if (Math.abs(Robot.get().getClimber().getEncoderBL().getDistance()-height)<2) {
            mult*=.3;
        }
        double diff = Robot.get().getClimber().getEncoderBR().getDistance()-Robot.get().getClimber().getEncoderBL().getDistance();
        Robot.get().getClimber().setBLSpeed(mult*RobotConstants.CLIMBER_BASE_SPEED+diff*RobotConstants.CLIMBER_ADJUST_SPEED);
        Robot.get().getClimber().setBRSpeed(mult*RobotConstants.CLIMBER_BASE_SPEED-diff*RobotConstants.CLIMBER_ADJUST_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (Math.abs(Robot.get().getClimber().getEncoderBL().getDistance()-height) < 0.2 
            && Math.abs(Robot.get().getClimber().getEncoderBR().getDistance()-height)<0.2) 
                return true;
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.get().getClimber().setBLSpeed(0.0);
        Robot.get().getClimber().setBRSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}

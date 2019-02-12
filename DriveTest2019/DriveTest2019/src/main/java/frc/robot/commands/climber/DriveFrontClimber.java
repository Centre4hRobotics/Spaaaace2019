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
public class DriveFrontClimber extends Command {
    private int dir;
    public DriveFrontClimber(int direction) {
        // Use requires() here to declare subsystem dependencies
        this.dir = direction;
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
        double diff = Robot.get().getClimber().getEncoderFR().getDistance()-Robot.get().getClimber().getEncoderFL().getDistance();
        Robot.get().getClimber().setFLSpeed(dir*(RobotConstants.CLIMBER_BASE_SPEED+diff*RobotConstants.CLIMBER_ADJUST_SPEED));
        Robot.get().getClimber().setFRSpeed(dir*(RobotConstants.CLIMBER_BASE_SPEED-diff*RobotConstants.CLIMBER_ADJUST_SPEED));
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        Robot.get().getClimber().setFLSpeed(0.0);
        Robot.get().getClimber().setFRSpeed(0.0);
    }
}

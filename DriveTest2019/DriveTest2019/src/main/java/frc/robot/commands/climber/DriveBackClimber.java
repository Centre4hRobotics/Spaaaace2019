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
    private int dir;
    public DriveBackClimber(int direction) {
        // Use requires() here to declare subsystem dependencies
        this.dir = direction;
        requires(Robot.get().getClimber());
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        //diff is right minus left
        double diff = Robot.get().getClimber().getEncoderBR().getDistance()-Robot.get().getClimber().getEncoderBL().getDistance();
        Robot.get().getClimber().setBLSpeed(dir*(RobotConstants.CLIMBER_BASE_SPEED+diff*RobotConstants.CLIMBER_ADJUST_SPEED));
        Robot.get().getClimber().setBRSpeed(dir*(RobotConstants.CLIMBER_BASE_SPEED-diff*RobotConstants.CLIMBER_ADJUST_SPEED));
    }

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
        Robot.get().getClimber().setBLSpeed(0.0);
        Robot.get().getClimber().setBRSpeed(0.0);
    }
}

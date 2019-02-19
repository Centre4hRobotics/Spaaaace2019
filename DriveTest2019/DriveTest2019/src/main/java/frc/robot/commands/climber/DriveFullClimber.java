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
public class DriveFullClimber extends Command {
    private double height, avg;
    private int dir;
    public DriveFullClimber(double height) {
        // Use requires() here to declare subsystem dependencies
        this.height = height;
        requires(Robot.get().getClimber());
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        double fl = Robot.get().getClimber().getEncoderFL(), fr = Robot.get().getClimber().getEncoderFR(),
               bl = Robot.get().getClimber().getEncoderBL(), br = Robot.get().getClimber().getEncoderBR();
        avg = 0.25*(fl+fr+bl+br);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double fl = Robot.get().getClimber().getEncoderFL(), fr = Robot.get().getClimber().getEncoderFR(),
               bl = Robot.get().getClimber().getEncoderBL(), br = Robot.get().getClimber().getEncoderBR();
        avg = 0.25*(fl+fr+bl+br);
        if (height<avg) dir = 1;
        Robot.get().getClimber().setFLSpeed(dir*RobotConstants.CLIMBER_BASE_SPEED+RobotConstants.CLIMBER_ADJUST_SPEED*(avg-fl)/avg);
        Robot.get().getClimber().setFRSpeed(dir*RobotConstants.CLIMBER_BASE_SPEED+RobotConstants.CLIMBER_ADJUST_SPEED*(avg-fr)/avg);
        Robot.get().getClimber().setBLSpeed(dir*RobotConstants.CLIMBER_BASE_SPEED+RobotConstants.CLIMBER_ADJUST_SPEED*(avg-bl)/avg);
        Robot.get().getClimber().setBRSpeed(dir*RobotConstants.CLIMBER_BASE_SPEED+RobotConstants.CLIMBER_ADJUST_SPEED*(avg-br)/avg);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (Math.abs(avg-height)<0.4) return true;
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.get().getClimber().setFLSpeed(0.0);
        Robot.get().getClimber().setFRSpeed(0.0);
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

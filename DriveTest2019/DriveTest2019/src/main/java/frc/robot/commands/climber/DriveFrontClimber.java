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
    private double height;
    public DriveFrontClimber(double height) {
        // Use requires() here to declare subsystem dependencies
        this.height = height;
        requires(Robot.get().getClimber());
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        //diff is right minus left
        double mult = 1;
        if (Robot.get().getClimber().getEncoderFL().getDistance()-height>0) mult = -1;
        if (Math.abs(Robot.get().getClimber().getEncoderFL().getDistance()-height)<2) {
            mult*=.3;
        }
        double diff = Robot.get().getClimber().getEncoderFR().getDistance()-Robot.get().getClimber().getEncoderFL().getDistance();
        Robot.get().getClimber().setFLSpeed(mult*RobotConstants.CLIMBER_BASE_SPEED+diff*RobotConstants.CLIMBER_ADJUST_SPEED);
        Robot.get().getClimber().setFRSpeed(mult*RobotConstants.CLIMBER_BASE_SPEED-diff*RobotConstants.CLIMBER_ADJUST_SPEED);
    }

    @Override
    protected boolean isFinished() {
        if (Math.abs(Robot.get().getClimber().getEncoderFL().getDistance()-height) < 0.2 
            && Math.abs(Robot.get().getClimber().getEncoderFR().getDistance()-height)<0.2) 
                return true;
        return false;
    }

    @Override
    protected void end() {
        Robot.get().getClimber().setFLSpeed(0.0);
        Robot.get().getClimber().setFRSpeed(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}

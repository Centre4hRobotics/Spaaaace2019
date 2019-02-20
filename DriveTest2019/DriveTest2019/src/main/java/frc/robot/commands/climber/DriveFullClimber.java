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
    private double[] speeds, adjusts, dists;
    private int dir;

    public DriveFullClimber(double height) {
        // Use requires() here to declare subsystem dependencies
        this.height = height;
        requires(Robot.get().getClimber());
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        dists = new double[4];
        dists[0] = Robot.get().getClimber().getEncoderFL();
        dists[1] = Robot.get().getClimber().getEncoderFR();
        dists[2] = Robot.get().getClimber().getEncoderBL(); 
        dists[3] = Robot.get().getClimber().getEncoderBR();
        avg = 0.25*(dists[0]+dists[1]+dists[2]+dists[3]);
        speeds = new double[4];
        for (int i = 0; i<4; i++) speeds[i] = 1;
        adjusts = new double[4];
    }

    @Override
    protected void execute() {
        dists[0] = Robot.get().getClimber().getEncoderFL();
        dists[1] = Robot.get().getClimber().getEncoderFR();
        dists[2] = Robot.get().getClimber().getEncoderBL(); 
        dists[3] = Robot.get().getClimber().getEncoderBR();
        avg = 0.25*(dists[0]+dists[1]+dists[2]+dists[3]);
        if (height<avg) dir = 1;
        else dir = -1;
        //pos 0 is fl, pos 1 is fr, pos 2 is bl, pos 3 is br
        for (int i = 0; i<4; i++) {
            if (Math.abs(dists[i]-height)<2) speeds[i]=0.5;
            if (Math.abs(dists[i]-height)<0.2) speeds[i] = 0;
            adjusts[i] = Math.max(Math.min(avg-dists[i], 1), -1);
        }
        
        Robot.get().getClimber().setFLSpeed(speeds[0]*(dir*RobotConstants.CLIMBER_BASE_SPEED+RobotConstants.CLIMBER_ADJUST_SPEED*adjusts[0]));
        Robot.get().getClimber().setFRSpeed(speeds[1]*(dir*RobotConstants.CLIMBER_BASE_SPEED+RobotConstants.CLIMBER_ADJUST_SPEED*adjusts[1]));
        Robot.get().getClimber().setBLSpeed(speeds[2]*(dir*RobotConstants.CLIMBER_BASE_SPEED+RobotConstants.CLIMBER_ADJUST_SPEED*adjusts[2]));
        Robot.get().getClimber().setBRSpeed(speeds[3]*(dir*RobotConstants.CLIMBER_BASE_SPEED+RobotConstants.CLIMBER_ADJUST_SPEED*adjusts[3]));
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        for (int i = 0; i<4; i++) {
            if (Math.abs(dists[i]-height)>0.2) return false;
        }
        return true;
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

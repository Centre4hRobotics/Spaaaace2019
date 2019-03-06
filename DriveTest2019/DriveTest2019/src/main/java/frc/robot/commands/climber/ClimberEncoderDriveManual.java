/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Robot;
import frc.robot.RobotConstants;

/**
 * An example command.  You can replace me with your own command.
 */
public class ClimberEncoderDriveManual extends Command {
  public ClimberEncoderDriveManual() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.get().getClimber());
  }

  @Override
  protected void execute() {
    double fSpeed = Robot.get().getOI().getFn1Joystick().getY();
    double bSpeed = Robot.get().getOI().getFn2Joystick().getY();
    //double fSpeed = Robot.get().getOI().getTestJoystick().getY(Hand.kLeft);
    //double bSpeed = Robot.get().getOI().getTestJoystick().getY(Hand.kRight);
    if (Math.abs(fSpeed) < 0.3 || !Robot.get().getClimber().isClimbMode()) fSpeed = 0;
    if (Math.abs(bSpeed) < 0.3 || !Robot.get().getClimber().isClimbMode()) bSpeed = 0;
    
    
    /*if (Robot.get().getOI().getTestJoystick().getStartButtonPressed()) 
        Robot.get().getClimber().setClimbMode(!Robot.get().getClimber().isClimbMode());

    /*double wSpeed = Robot.get().getOI().getTestJoystick().getTriggerAxis(Hand.kLeft);
    if (wSpeed < Robot.get().getOI().getTestJoystick().getTriggerAxis(Hand.kRight)) {
        wSpeed = Robot.get().getOI().getTestJoystick().getTriggerAxis(Hand.kRight)*-1;
    }
    if (Math.abs(wSpeed) < 0.4 || !Robot.get().getClimber().isClimbMode()) wSpeed = 0;
    Robot.get().getClimber().setWheelSpeed(wSpeed*RobotConstants.CLIMB_WHEEL_MULT);*/

    double diff = Robot.get().getClimber().getEncoderFR()-Robot.get().getClimber().getEncoderFL();
    Robot.get().getClimber().setFLSpeed(fSpeed*RobotConstants.CLIMBER_BASE_SPEED+diff*RobotConstants.CLIMBER_ADJUST_SPEED);
    Robot.get().getClimber().setFRSpeed(fSpeed*RobotConstants.CLIMBER_BASE_SPEED-diff*RobotConstants.CLIMBER_ADJUST_SPEED);

    diff = Robot.get().getClimber().getEncoderBR()-Robot.get().getClimber().getEncoderBL();
    Robot.get().getClimber().setBLSpeed(bSpeed*RobotConstants.CLIMBER_BASE_SPEED+diff*RobotConstants.CLIMBER_ADJUST_SPEED);
    Robot.get().getClimber().setBRSpeed(bSpeed*RobotConstants.CLIMBER_BASE_SPEED-diff*RobotConstants.CLIMBER_ADJUST_SPEED);
  }
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.get().getClimber().setFLSpeed(0);
    Robot.get().getClimber().setFRSpeed(0);
    Robot.get().getClimber().setBLSpeed(0);
    Robot.get().getClimber().setBRSpeed(0);
    //Robot.get().getClimber().setWheelSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

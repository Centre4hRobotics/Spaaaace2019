/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

/**
 * An example command.  You can replace me with your own command.
 */
public class ClimberDriveManual extends Command {
  public ClimberDriveManual() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.get().getClimber());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.get().getClimber().isClimbMode()) {
      double fSpeed = Robot.get().getOI().getTestJoystick().getY(Hand.kLeft);
      //double fSpeed = Robot.get().getOI().getFn1Joystick().getY();
      double bSpeed = Robot.get().getOI().getTestJoystick().getY(Hand.kRight);
      //double bSpeed = -1*Robot.get().getOI().getFn2Joystick().getY();
      /*double wSpeed = Robot.get().getOI().getTestJoystick().getTriggerAxis(Hand.kLeft);
      if (wSpeed < Robot.get().getOI().getTestJoystick().getTriggerAxis(Hand.kRight)) {
        wSpeed = -1*Robot.get().getOI().getTestJoystick().getTriggerAxis(Hand.kRight);
      }

      /*if (Robot.get().getOI().getTestJoystick().getSsdtartButtonPressed())
        Robot.get().getClimber().toggleClimbMode();*/

      if (Math.abs(fSpeed) < 0.1) fSpeed = 0;
      if (Math.abs(bSpeed) < 0.1) bSpeed = 0;

      //if (fSpeed>0) fSpeed*=0.3;
      //if (bSpeed>0) bSpeed*=0.3;

      Robot.get().getClimber().setSpeed(fSpeed*RobotConstants.CLIMBER_MANUAL_SPEED_MULT, 5);
      Robot.get().getClimber().setSpeed(RobotConstants.CLIMBER_MANUAL_SPEED_MULT*bSpeed, 6);
      //Robot.get().getClimber().setWheelSpeed(wSpeed*RobotConstants.CLIMBER_SPEED_MULT);
    } else {
      end();
    }
  }
  
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.get().getClimber().setSpeed(0, 4);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

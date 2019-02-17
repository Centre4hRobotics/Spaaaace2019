/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.gripper;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class DriveCargoGripper extends Command {
  public DriveCargoGripper() {
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
      double speed = Robot.get().getOI().getTestJoystick().getTriggerAxis(Hand.kLeft);
      if (speed<Robot.get().getOI().getTestJoystick().getTriggerAxis(Hand.kRight)) {
          speed = -1*Robot.get().getOI().getTestJoystick().getTriggerAxis(Hand.kRight);
      }

      if (Math.abs(speed)<0.3 || Robot.get().getClimber().isClimbMode()) speed = 0;
      Robot.get().getCargoGripper().setBallSpeed(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.get().getCargoGripper().setBallSpeed(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
      end();
  }
}

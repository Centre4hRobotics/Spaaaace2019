/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * A testing command that uses an xbox joystick to control the lift speed
 */
public class SetLiftSpeed extends Command {
  public SetLiftSpeed() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.get().getLifter());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      double speed = Robot.get().getOI().getFnJoystick().getY(Hand.kLeft);
      if (Math.abs(speed*0.2)<0.3) speed = 0;
      Robot.get().getLifterArm().setSpeed(speed*0.2);
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
    Robot.get().getLifterArm().setSpeed(0);
  }
}

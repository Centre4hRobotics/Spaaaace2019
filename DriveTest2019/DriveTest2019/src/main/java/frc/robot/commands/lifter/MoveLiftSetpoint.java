/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

/**
 * A testing command that uses an xbox joystick to control the lift speed
 */
public class MoveLiftSetpoint extends Command {
  public MoveLiftSetpoint() {
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
    double speed = Robot.get().getOI().getFn1Joystick().getY();  
    if (Math.abs(speed) > 0.3 &&!Robot.get().getClimber().isClimbMode()) {
      Robot.get().getLifter().setHeightInches(Robot.get().getLifter().getHeightSetpoint()+speed*RobotConstants.LIFT_MANUAL_DELTA);
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    //Robot.get().getLifter().setHeightInches(Robot.get().getLifter().getHeightInches());
  }
}

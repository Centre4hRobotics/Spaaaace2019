/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lifter;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

/**
 * A testing command that uses an xbox joystick to control the lift speed
 */
public class MoveLiftAndArmSetpoint extends Command {
  public MoveLiftAndArmSetpoint() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.get().getLifter());
    requires(Robot.get().getLifterArm());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed = Robot.get().getOI().getTestJoystick().getY(Hand.kLeft);
    if (Math.abs(speed) > 0.3 &&!Robot.get().getClimber().isClimbMode()) {
        Robot.get().getLifter().setHeightInches(Robot.get().getLifter().getHeightSetpoint()+speed*RobotConstants.LIFT_MANUAL_DELTA);
    }
    
    speed = Robot.get().getOI().getTestJoystick().getY(Hand.kRight);
    if (Math.abs(speed)>0.3 &&!Robot.get().getClimber().isClimbMode()) {
        Robot.get().getLifterArm().setDegree(Robot.get().getLifterArm().getDegreeSetpoint()+speed*RobotConstants.ARM_MANUAL_DELTA);
    }

    if (Robot.get().getOI().getTestJoystick().getPOV() !=-1) {
        Robot.get().getLifter().setHeightInches(0);
        Robot.get().getLifterArm().setDegree(RobotConstants.DEGREE_START);
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

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * A command to lift to a height parameter as a position.
 */
public class LiftHeight extends Command {
    private double height;

  public LiftHeight(double height) {
    this.height = height;
    requires(Robot.get().getLifter());
  }

  @Override
  protected void initialize() {
    setTimeout(5);
  }

  //Sets the lift height to the passed-in parameter
  @Override
  protected void execute() {
      Robot.get().getLifter().setHeightInches(height);
  }

  // This finishes immediately because it only happens once (just sets the setpoint and then quits)
  @Override
  protected boolean isFinished() {
    return isTimedOut()||Math.abs(Robot.get().getLifter().getHeightInches()-height) < 2;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}

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
public class ArmDegree extends Command {
    private double degree;

  public ArmDegree(double degree) {
    this.degree = degree;
    requires(Robot.get().getLifterArm());
  }

  @Override
  protected void initialize() {
    setTimeout(3);
  }

  //Sets the lift degree to the passed-in parameter
  @Override
  protected void execute() {
      Robot.get().getLifterArm().setDegree(degree);
  }

  // This finishes immediately because it only happens once (just sets the setpoint and then quits)
  @Override
  protected boolean isFinished() {
    //System.out.println("Setpoint: " + degree + "\nDegree: " + Robot.get().getLifterArm().getDegree());
    return isTimedOut()||Math.abs(Robot.get().getLifterArm().getDegree()-degree)<3;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}

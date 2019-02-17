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
    private long initTime;

  public ArmDegree(double degree) {
    this.degree = degree;
    this.initTime = System.currentTimeMillis();
    requires(Robot.get().getLifterArm());
  }

  @Override
  protected void initialize() {
  }

  //Sets the lift degree to the passed-in parameter
  @Override
  protected void execute() {
      Robot.get().getLifterArm().setDegree(degree);
  }

  // This finishes immediately because it only happens once (just sets the setpoint and then quits)
  @Override
  protected boolean isFinished() {
    if (System.currentTimeMillis()-initTime>3000) return true;
    return Math.abs(Robot.get().getLifterArm().getDegree()-degree)<1;
  }

  @Override
  protected void end() {
    Robot.get().getLifterArm().setSpeed(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}

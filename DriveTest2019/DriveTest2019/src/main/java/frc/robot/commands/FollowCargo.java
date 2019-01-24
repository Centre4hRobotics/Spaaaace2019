/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FollowCargo extends Command {

  public FollowCargo() {
    requires(Robot.get().getDriveTrain());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if (Robot.get().getNTInst().getTable("Datatable").getEntry("Found Contour").getDouble(0.0) == 0) {
        return;
      }
      double steer = Robot.get().getNTInst().getTable("Datatable").getEntry("Steer").getDouble(0.0);
      double speed = Robot.get().getNTInst().getTable("Datatable").getEntry("Speed").getDouble(0.0);
      Robot.get().getDriveTrain().drive(speed,steer);
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
  }
}

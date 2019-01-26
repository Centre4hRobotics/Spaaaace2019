/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FindTargets extends Command {

  public static final double STEER_MULT = 0.4;
  public static final double SPEED_MULT = 0.35;

  public FindTargets() {
    super("FindTargets");
    requires(Robot.get().getDriveTrain());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if (Robot.get().getNTInst().getTable("Vision Targets").getEntry("Contours Found").getDouble(0.0) < 2) {
        Robot.get().getDriveTrain().drive(0,0);
        return;
      }
      double steer = Robot.get().getNTInst().getTable("Vision Targets").getEntry("Steer").getDouble(0.0);
      double speed = Robot.get().getNTInst().getTable("Vision Targets").getEntry("Speed").getDouble(0.0);
      Robot.get().getDriveTrain().drive(-1.0*speed*SPEED_MULT,steer*STEER_MULT);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.get().getDriveTrain().drive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.get().getDriveTrain().drive(0,0);
  }
}

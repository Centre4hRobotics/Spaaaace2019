/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

public class FindTargetsStraight extends Command {

  public FindTargetsStraight() {
    super("FindTargetsStraight");
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
        end();
        return;
      }
      double xCenterDiff = Robot.get().getNTInst().getTable("Vision Targets").getEntry("XCenter2").getDouble(0.0)-Robot.get().getNTInst().getTable("Vision Targets").getEntry("XCenter1").getDouble(0.0);
      //left minus right
      double areaDiff = Robot.get().getNTInst().getTable("Vision Targets").getEntry("Area2").getDouble(0.0)-Robot.get().getNTInst().getTable("Vision Targets").getEntry("Area1").getDouble(0.0);
      double areaAvg = 0.5*(Robot.get().getNTInst().getTable("Vision Targets").getEntry("Area2").getDouble(0.0)+Robot.get().getNTInst().getTable("Vision Targets").getEntry("Area1").getDouble(0.0));
      if (Math.abs(areaDiff/areaAvg)<RobotConstants.AREA_PERCENT_ERROR) areaDiff = 0;
      double targetAngle = areaDiff/xCenterDiff*RobotConstants.TARGET_ANGLE_MULT+0.5;
      
      if (targetAngle > 0.9) targetAngle = 0.9;
      if (targetAngle < 0.1) targetAngle = 0.1;

      double speed = Math.min(1,Math.max(RobotConstants.TARGET_STRAIGHT_SPEED_MULT/xCenterDiff,0));
      double steer = Math.min(1,Math.max(-2*(targetAngle-Robot.get().getNTInst().getTable("Vision Targets").getEntry("XCenter").getDouble(0.0)),-1));

      //correction factors
      //if (Math.abs(steer)>0.15) speed = 0;
      if (Robot.get().getNTInst().getTable("Vision Targets").getEntry("Area1").getDouble(0.0) == 0 || 
          Robot.get().getNTInst().getTable("Vision Targets").getEntry("Area2").getDouble(0.0) == 0) 
          end();
      steer*=RobotConstants.TARGET_STRAIGHT_STEER_MULT;
      //if (Math.abs(speed)>0.25) steer+=0.2;
      Robot.get().getNTInst().getTable("Find Target").getEntry("Speed (2)").setNumber(-1.0*speed);
      Robot.get().getNTInst().getTable("Find Target").getEntry("Steer (2)").setNumber(steer);
      Robot.get().getNTInst().getTable("Find Target").getEntry("Target Angle").setNumber(targetAngle);
      Robot.get().getDriveTrain().drive(-1.0*speed,steer);
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
    end();
  }
}

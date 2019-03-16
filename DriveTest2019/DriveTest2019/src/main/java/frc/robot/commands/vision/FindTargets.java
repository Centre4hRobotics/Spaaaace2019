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

public class FindTargets extends Command {

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
      double steer = 2*Robot.get().getNTInst().getTable("Vision Targets").getEntry("XCenter").getDouble(-0.1)-1;
      
      if (steer > 1.0) steer = 1.0;
      if (steer < -1.0) steer = -1.0;

      double speed = 1-Robot.get().getNTInst().getTable("Vision Targets").getEntry("Area1").getDouble(0.0)/RobotConstants.TARGET_AREA;
      if(speed < -0.2) speed = -0.2;
      
      if (Robot.get().getNTInst().getTable("Vision Targets").getEntry("Area1").getDouble(0.0) == 0 || 
          Robot.get().getNTInst().getTable("Vision Targets").getEntry("Area2").getDouble(0.0) == 0) 
          speed = 0;

      //correction factor
      //if (Math.abs(speed)>0.25) steer+=0.2;

      Robot.get().getNTInst().getTable("Find Target").getEntry("Speed").setNumber(-1.0*speed*RobotConstants.TARGET_SPEED_MULT);
      Robot.get().getNTInst().getTable("Find Target").getEntry("Steer").setNumber(steer*RobotConstants.TARGET_STEER_MULT);
      Robot.get().getDriveTrain().drive(-1.0*speed*RobotConstants.TARGET_SPEED_MULT,steer*RobotConstants.TARGET_STEER_MULT);
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

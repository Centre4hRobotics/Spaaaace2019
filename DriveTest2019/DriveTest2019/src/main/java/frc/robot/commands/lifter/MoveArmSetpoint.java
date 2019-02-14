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
public class MoveArmSetpoint extends Command {
  public MoveArmSetpoint() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.get().getLifterArm());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  //True means its fine, false means it will not work
  private boolean heightRestrict (double height) {
    double lHeight = Robot.get().getLifter().getHeightInches();
    if (((Robot.get().getLifterArm().willBeInsideFramePerimeter(height)&&lHeight+height<3)||lHeight+height<-4)) 
        return false;
    return true;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed = Robot.get().getOI().getFn2Joystick().getY();  
    if (Math.abs(speed)>0.4) {
    //double speed = Robot.get().getOI().getTestJoystick().getY(Hand.kRight);
       /*if (Math.abs(speed*RobotConstants.LIFT_SPEED_MULT)<0.3||!heightRestrict(Robot.get().getLifter().getHeightInches()+speed*RobotConstants.LIFT_MANUAL_DELTA)) {
        speed = 0;
      }*/
      Robot.get().getLifterArm().setDegree(Robot.get().getLifterArm().getDegreeSetpoint()+speed*RobotConstants.ARM_MANUAL_DELTA);
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
    Robot.get().getLifterArm().setHeightInches(Robot.get().getLifterArm().getHeightInches());
  }
}

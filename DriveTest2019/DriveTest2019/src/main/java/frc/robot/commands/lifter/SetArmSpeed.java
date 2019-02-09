/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lifter;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

/**
 * An example command.  You can replace me with your own command.
 */
public class SetArmSpeed extends Command {
  public SetArmSpeed() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.get().getLifterArm());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  //True means its fine, false means it will not work
  private boolean heightRestrict (double speed) {
    double aHeight = Robot.get().getLifterArm().getHeightInches();
    double lHeight = Robot.get().getLifter().getHeightInches();
    if (((Robot.get().getLifterArm().willBeInsideFramePerimeter(aHeight)&&lHeight+aHeight<3)||lHeight+aHeight<-4)&&speed<0) 
        return false;
    return true;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      //double speed = Robot.get().getOI().getFn1Joystick().getY();
      double speed = Robot.get().getOI().getTestJoystick().getY(Hand.kRight);
      if (Math.abs(speed) < 0.07) {
        speed = 0;
      }
      /*if (Math.abs(speed*RobotConstants.ARM_SPEED_MULT)<0.3||!heightRestrict(speed)) {
        speed = 0;
      }*/
      Robot.get().getLifterArm().setSpeed(speed*RobotConstants.ARM_SPEED_MULT);
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
    Robot.get().getLifterArm().setSpeed(0);
  }
}

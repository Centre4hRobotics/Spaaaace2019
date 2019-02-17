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
 * A command to reset the arm angle
 */
public class ArmOverride extends Command {
  public ArmOverride() {
    requires(Robot.get().getLifterArm());
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.get().getOI().getBaseJoystick().getStartButton()&&Robot.get().getOI().getBaseJoystick().getBackButton()) {
        Robot.get().getLifterArm().overrideAngle(RobotConstants.DEGREE_START);
    }
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}

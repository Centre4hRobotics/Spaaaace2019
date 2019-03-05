/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

/**
 * A command to reset the arm angle
 */
public class ArmOverride extends Command {
  public ArmOverride() {
    requires(Robot.get().getLifterArm());
    Robot.get().getNTInst().getTable("Command Test").getEntry("ArmOverride").setNumber(0);
  }

  @Override
  protected void initialize() {
    Robot.get().getNTInst().getTable("Command Test").getEntry("ArmOverride").setNumber(0);
  }

  @Override
  protected void execute() {
    if (Robot.get().getOI().getBaseJoystick().getStartButton()&&Robot.get().getOI().getBaseJoystick().getBackButton()){
      Robot.get().getLifterArm().overrideAngle(RobotConstants.DEGREE_START);
      Robot.get().getNTInst().getTable("Command Test").getEntry("ArmOverride").setNumber(1);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.get().getNTInst().getTable("Command Test").getEntry("ArmOverride").setNumber(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.lifter.*;
import frc.robot.RobotConstants;

/**
 * A command to lift to a height parameter as a position and then the arm as a degree.
 */
public class LiftThenArm extends CommandGroup {
  public LiftThenArm(double height, double degree) {
    //addSequential(new ArmDegree(RobotConstants.DEGREE_START));
    addSequential(new LiftHeight(height));
    addSequential(new ArmDegree(degree));
  }
}

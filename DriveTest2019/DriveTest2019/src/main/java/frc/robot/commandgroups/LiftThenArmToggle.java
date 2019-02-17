/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.lifter.*;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * A command to lift to a height parameter as a position and then the arm as a degree.
 */
public class LiftThenArmToggle extends CommandGroup {
  public LiftThenArmToggle(double height1, double degree1, double height2, double degree2) {
    if (Robot.get().getOI().getTestJoystick().getBumper(Hand.kLeft)) {
        addSequential(new LiftHeight(height2));
        addSequential(new ArmDegree(degree2));
    } else {
        addSequential(new LiftHeight(height1));
        addSequential(new ArmDegree(degree1));
    }
  }
}

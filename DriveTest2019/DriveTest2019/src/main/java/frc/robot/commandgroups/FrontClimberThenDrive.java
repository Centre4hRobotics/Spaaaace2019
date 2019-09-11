/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climber.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.lifter.*;

/**
 * A command to lift to a height parameter as a position and then the arm as a degree.
 */
public class FrontClimberThenDrive extends CommandGroup {
  public FrontClimberThenDrive(double height, double speed) {
    addSequential(new DriveFrontClimber(height));
    addParallel(new ArmDegree(-66.6));
    //addSequential(new DriveStraight(speed));
  }
}

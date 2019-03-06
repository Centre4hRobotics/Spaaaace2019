/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * A command that toggles the state of the hatch input.
 */
public class SetClimbMode extends Command {
    private boolean state;

  public SetClimbMode(boolean state) {
    this.state = state;
    requires(Robot.get().getClimber());
  }

  // Gets the time as it starts to run
  @Override
  protected void initialize() {
      Robot.get().getClimber().setClimbMode(state);
  }

  /** 
   * Finishes after 0.4 seconds (long enough to trip limit switch on robot)
   */
  @Override
  protected boolean isFinished() {
    return true;
  }
}

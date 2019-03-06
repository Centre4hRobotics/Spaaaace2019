/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.gripper;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * A command that toggles the state of the hatch input.
 */
public class HatchSet extends Command {
    private boolean endState;

  public HatchSet(boolean state) {
    endState = state;
    requires(Robot.get().getHatchGripper());
  }

  // Gets the time as it starts to run
  @Override
  protected void initialize() {
      setTimeout(0.4);
  }

  /**
   *  Sets the speed to 1 or -1 based on current hatch state (goes other way). 
   *  EndState is basically a current update on what state the hatch is trying to go to.
   */
  
  @Override
  protected void execute() {
    if (endState) {
        Robot.get().getHatchGripper().setHatchSpeed(1);
    } else {
        Robot.get().getHatchGripper().setHatchSpeed(-1);
    }
  }

  /** 
   * Finishes after 0.4 seconds (long enough to trip limit switch on robot)
   */
  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  // Puts the current state into the HatchGripper subsystem just before it finishes.
  @Override
  protected void end() {
    Robot.get().getHatchGripper().setHatchState(endState);
    Robot.get().getHatchGripper().setHatchSpeed(0);
  }

  @Override
  protected void interrupted() {
      end();
  }
}

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
 * A command to eject the cargo
 */
public class BallOut extends Command {
    public BallOut() {
        requires(Robot.get().getCargoGripper());
    }

    @Override
    protected void initialize() {
    }

    // Sets the speed of the motor to maximum
    @Override
    protected void execute() {
        Robot.get().getCargoGripper().setBallSpeed(1.0);
    }

    // Never finished, but can be interrupted.
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    // Stops the motor when interrupted
    @Override
    protected void interrupted() {
        Robot.get().getCargoGripper().setBallSpeed(0.0);
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * A command to lift to a height parameter as a position.
 */
public class LiftAndArmHeight extends Command {
    private double liftHeight, armHeight;

  public LiftAndArmHeight(double liftHeight, double armHeight) {
    this.liftHeight = liftHeight;
    this.armHeight = armHeight;
    requires(Robot.get().getLifter());
    requires(Robot.get().getLifterArm());
  }
  
  //True means its fine, false means it will not work
  private boolean heightRestrict (double lHeight, double aHeight) {
    if ((Robot.get().getLifterArm().willBeInsideFramePerimeter(aHeight)&&lHeight+aHeight<3)||lHeight+aHeight<-4) 
        return false;
    return true;
  }

  @Override
  protected void initialize() {
  }

  //Sets the lift height to the passed-in parameter
  @Override
  protected void execute() {
      if(heightRestrict(liftHeight,armHeight)) {
        Robot.get().getLifter().setHeightInches(liftHeight);
        Robot.get().getLifterArm().setHeightInches(armHeight);
      }
  }

  // This finishes immediately because it only happens once (just sets the setpoint and then quits)
  @Override
  protected boolean isFinished() {
    /*if (Robot.get().getLifter().getHeightInches()-height < 0.1) {
      Robot.get().getLifter().setHeightInches(Robot.get().getLifter().getHeightInches());
      return true;
    }*/
    return true;
  }

  @Override
  protected void end() {
    //Robot.get().getLifter().setSpeed(0);
  }

  @Override
  protected void interrupted() {
    Robot.get().getLifter().setSpeed(0);
    Robot.get().getLifterArm().setSpeed(0);
  }
}

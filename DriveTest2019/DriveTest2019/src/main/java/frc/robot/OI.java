/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.lifter.*;
import frc.robot.commands.gripper.*;
import frc.robot.commands.climber.*;

import frc.robot.commands.*;

public class OI {
  private XboxController base = new XboxController(0);
  private Button buttonbA = new JoystickButton(base, 1);
  private Button buttonbB = new JoystickButton(base, 2);
  private Button buttonbX = new JoystickButton(base,3);
  private Button buttonbY = new JoystickButton(base,4);

  /*private XboxController _fn = new XboxController(1);
  private Button _buttonfA = new JoystickButton(_fn, 1);
  private Button _buttonfB = new JoystickButton(_fn, 2);
  private Button _buttonfX = new JoystickButton(_fn,3);
  private Button _buttonfY = new JoystickButton(_fn,4);
  private Button _buttonfLB = new JoystickButton(_fn, 5);
  private Button _buttonfRB = new JoystickButton(_fn,6);
  private Button _buttonfBack = new JoystickButton(_fn,7);
  private Button _buttonfStart = new JoystickButton(_fn,8);*/

  //fn1 does heights and elevator movement
  private Joystick fn1 = new Joystick(1);
  private Button floorHeight = new JoystickButton(fn1, 1);
  private Button hatchHeight1 = new JoystickButton(fn1, 2);
  private Button cargoHeight1 = new JoystickButton(fn1, 3);
  private Button hatchHeight2 = new JoystickButton(fn1, 4);
  private Button cargoHeight2 = new JoystickButton(fn1, 5);
  private Button hatchHeight3 = new JoystickButton(fn1, 6);
  private Button cargoHeight3 = new JoystickButton(fn1, 7);

  //fn2 does climb, gripper stuff, and arm movement
  //private Joystick _fn2 = new Joystick(2);
  private Joystick fn2 = new Joystick(2);
  private Button cargoIn = new JoystickButton (fn2, 1);
  private Button cargoOut = new JoystickButton (fn2, 2);
  private Button hatch = new JoystickButton (fn2, 3);
  private Button hab3Climb = new JoystickButton (fn2, 4);
  private Button hab2Climb = new JoystickButton (fn2, 5);
  private Button hab2Exit = new JoystickButton (fn2, 6);

  public OI () {
    buttonbA.whileHeld(new FollowCargo());
    buttonbB.whenPressed(new LiftHeight(0));
    buttonbX.whileHeld(new FindTargets());
    buttonbY.whenPressed(new LiftHeight(20));

    
    floorHeight.whenPressed(new TestPrint());
    cargoIn.whileHeld(new BallIn());
    cargoOut.whileHeld(new BallOut());
    hatch.whenPressed(new HatchToggle());

    /*_buttonfLB.whileHeld(new BackDown());
    _buttonfRB.whileHeld(new FrontDown());
    _buttonfBack.whileHeld(new BackUp());
    _buttonfStart.whileHeld(new FrontUp());
    _buttonfX.whileHeld(new BallIn());
    _buttonfA.whileHeld(new BallOut());
    _buttonfY.whenPressed(new HatchToggle());*/
  }

  public XboxController getBaseJoystick() {
    return this.base;
  }

  public Joystick getFn1Joystick() {
    return this.fn1;
  }

  public Joystick getFn2Joystick() {
    return this.fn2;
  }
}

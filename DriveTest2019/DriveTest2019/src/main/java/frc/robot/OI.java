/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.*;

public class OI {
  private XboxController _base = new XboxController(0);
  private Button _buttonbA = new JoystickButton(_base, 1);
  private Button _buttonbB = new JoystickButton(_base, 2);
  private Button _buttonbX = new JoystickButton(_base,3);
  private Button _buttonbY = new JoystickButton(_base,4);

  private XboxController _fn = new XboxController(1);
  private Button _buttonfA = new JoystickButton(_fn, 1);
  //private Button _buttonfB = new JoystickButton(_fn, 2);
  private Button _buttonfX = new JoystickButton(_fn,3);
  private Button _buttonfY = new JoystickButton(_fn,4);
  private Button _buttonfLB = new JoystickButton(_fn, 5);
  private Button _buttonfRB = new JoystickButton(_fn,6);
  private Button _buttonfBack = new JoystickButton(_fn,7);
  private Button _buttonfStart = new JoystickButton(_fn,8);

  public OI () {
    _buttonbA.whileHeld(new FollowCargo());
    _buttonbB.whenPressed(new LiftHeight(0));
    _buttonbX.whileHeld(new FindTargets());
    _buttonbY.whenPressed(new LiftHeight(20));
    _buttonfLB.whileHeld(new BackDown());
    _buttonfRB.whileHeld(new FrontDown());
    _buttonfBack.whileHeld(new BackUp());
    _buttonfStart.whileHeld(new FrontUp());
    _buttonfX.whileHeld(new BallIn());
    _buttonfA.whileHeld(new BallOut());
    _buttonfY.whenPressed(new HatchToggle());
  }

  public XboxController getBaseJoystick() {
    return this._base;
  }

  public XboxController getFnJoystick() {
    return this._fn;
  }
}

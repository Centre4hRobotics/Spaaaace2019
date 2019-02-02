/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private XboxController _base = new XboxController(0);
  private Button _buttonbA = new JoystickButton(_base, 1);
  private Button _buttonbB = new JoystickButton(_base, 2);
  private Button _buttonbX = new JoystickButton(_base,3);
  private Button _buttonbY = new JoystickButton(_base,4);

  private XboxController _fn = new XboxController(1);
  private Button _buttonfA = new JoystickButton(_fn, 1);
  private Button _buttonfB = new JoystickButton(_fn, 2);
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


  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}

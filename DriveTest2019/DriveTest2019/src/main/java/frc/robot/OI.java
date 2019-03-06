/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.lifter.*;
import frc.robot.commands.gripper.*;
import frc.robot.commandgroups.*;
import frc.robot.commands.climber.*;
import frc.robot.commands.vision.*;
import frc.robot.commands.drive.*;

public class OI {
  private XboxController base = new XboxController(0);
  private Button buttonbA = new JoystickButton(base, 1);
  private Button buttonbB = new JoystickButton(base, 2);
  private Button buttonbX = new JoystickButton(base,3);
  private Button buttonbY = new JoystickButton(base,4);
  private Button buttonbStart = new JoystickButton(base,8);

  //Test Controller:
  private XboxController test = new XboxController(3);
  private Button buttontA = new JoystickButton(test, 1);
  private Button buttontB = new JoystickButton(test, 2);
  private Button buttontX = new JoystickButton(test, 3);
  private Button buttontY = new JoystickButton(test, 4);
  private Button buttontLB = new JoystickButton(test, 5);
  private Button buttontRB = new JoystickButton(test,6);
  private Button buttontBack = new JoystickButton(test,7);
  private Button buttontStart = new JoystickButton(test,8);
  
  
  /*private XboxController _fn = new XboxController(1);
  private Button buttonfA = new JoystickButton(_fn, 1);
  private Button buttonfB = new JoystickButton(_fn, 2);
  private Button buttonfX = new JoystickButton(_fn,3);
  private Button buttonfY = new JoystickButton(_fn,4);
  private Button buttonfLB = new JoystickButton(_fn, 5); 
  private Button buttonfRB = new JoystickButton(_fn,6);
  private Button buttonfBack = new JoystickButton(_fn,7);
  private Button buttonfStart = new JoystickButton(_fn,8);*/

  //fn1 does heights and elevator movement
  private Joystick fn1 = new Joystick(1);
  private Button floorHeight = new JoystickButton (fn1, 1);
  private Button hatch1 = new JoystickButton (fn1, 2);
  private Button cargo1 = new JoystickButton (fn1, 3);
  private Button hatch2 = new JoystickButton (fn1, 4);
  private Button cargo2 = new JoystickButton (fn1, 5);
  private Button hatch3 = new JoystickButton (fn1, 6);
  private Button cargo3 = new JoystickButton (fn1, 7);
  private Button stow = new JoystickButton (fn1, 8);
  private Button portal = new JoystickButton (fn1,9);
  private Button extraRed = new JoystickButton (fn1, 10);

  //fn2 does climb, gripper stuff, and arm movement
  //private Joystick _fn2 = new Joystick(2);
  private Joystick fn2 = new Joystick(2);
  private Button cargoIn = new JoystickButton (fn2, 1);
  private Button cargoOut = new JoystickButton (fn2, 2);
  private Button hatch = new JoystickButton (fn2, 3);
  private Button hab3Climb = new JoystickButton (fn2, 4);
  private Button hab2Climb = new JoystickButton (fn2, 5);
  private Button fup = new JoystickButton (fn2, 6);
  private Button allUp = new JoystickButton (fn2, 7);
  private Button climbWheel = new JoystickButton (fn2,8);
  private Button climbMode = new JoystickButton (fn2, 9);
  private Button extraBlue = new JoystickButton (fn2, 10);

  public OI () {
    //buttonbA.whileHeld(new FollowCargo());
    buttonbA.whileHeld(new FindTargets());
    buttonbStart.whileHeld(new ArmOverride());
   
    /*buttontB.whenPressed(new ArmDegree(0));
    buttontY.whenPressed(new ArmDegree(RobotConstants.DEGREE_START));
    buttontLB.whileHeld(new DriveClimberWheel(1.0));
    buttontRB.whileHeld(new DriveClimberWheel(-1.0));

    /*buttonfLB.whenPressed(new HatchToggle());
    buttonfRB.whileHeld(new FrontDown());
    /*buttonfBack.whileHeld(new BackUp());
    buttonfStart.whileHeld(new FrontUp());
    buttonfX.whileHeld(new BallIn());
    buttonfA.whileHeld(new BallOut());
    buttonfY.whenPressed(new HatchToggle());*/
    floorHeight.whenPressed(new LiftThenArm(0, RobotConstants.ARM_FLOOR_DEGREE));
    hatch1.whenPressed(new LiftThenArm(0, -66.6));
    hatch2.whenPressed(new LiftThenArm(18.5,-36));
    hatch3.whenPressed(new LiftThenArm(38/*35.25*/,-36));
    cargo1.whenPressed(new LiftThenArm(0,0));
    cargo2.whenPressed(new LiftThenArm(26,0));
    cargo3.whenPressed(new LiftThenArm(43,0));
    stow.whenPressed(new LiftAndArm(0,0));
    portal.whenPressed(new LiftThenArm(18.5,0));

    cargoIn.whileHeld(new BallIn());
    cargoOut.whileHeld(new BallOut());
    hatch.whenPressed(new HatchSet(false));
    hatch.whenReleased(new HatchSet(true));
    hab3Climb.whileHeld(new DriveFullClimber(20));
    hab2Climb.whileHeld(new DriveFullClimber(9));
    fup.whileHeld(new DriveFrontClimber(0));
    allUp.whileHeld(new DriveFullClimber(0));
    climbWheel.whileHeld(new DriveClimberWheel(1.0));
    climbMode.whenPressed(new SetClimbMode(true));
    climbMode.whenReleased(new SetClimbMode(false));

    /*buttontA.whenPressed(new LiftThenArmToggle(0,0,0,-66.6));
    buttontX.whenPressed(new LiftThenArmToggle(28,0,16.25,-36));
    buttontY.whenPressed(new LiftThenArmToggle(41.75,0,38,-36));
    buttontB.whenPressed(new LiftThenArmToggle(0,RobotConstants.ARM_FLOOR_DEGREE, 13.5,0));
    buttontBack.whenPressed(new LiftAndArm(0,0));
    buttontRB.whenPressed(new HatchSet(false));
    buttontRB.whenReleased(new HatchSet(true));
    buttontStart.whenPressed(new SetClimbMode(true));
    buttontStart.whenReleased(new SetClimbMode(false));
    buttontY.whileHeld(new DriveFullClimber(20));
    buttontX.whileHeld(new DriveFullClimber(9));
    buttontB.whileHeld(new DriveFrontClimber(0));
    buttontA.whileHeld(new DriveFullClimber(0));*/
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

  public XboxController getTestJoystick() {
    return this.test;
  }
}

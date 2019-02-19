/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.drive.DriveWithJoystick;
import frc.robot.subsystems.*;  
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
//import edu.wpi.first.networktables.NetworkTableInstance;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private DriveTrain s_driveTrain = null;
  private OI s_oi = null;
  private CargoGripper s_cargoGripper = null;
  private HatchGripper s_hatchGripper = null;
  private Lifter s_lifter = null;
  private LifterArm s_lifterArm = null;
  private Climber s_climber = null;
  private NetworkTableInstance _ntinst = null;
  public static Robot _instance = null;
  SendableChooser<Command> m_chooser = null;

  /**
   * Constructor instantiates all subsytems (except OI), the network table, and the command runner.
   * This is done before OI is instantiated to prevent dependency errors.
   */
  public Robot () {
    _instance = this;
    s_driveTrain = new DriveTrain();
    s_cargoGripper = new CargoGripper();
    s_hatchGripper = new HatchGripper();
    s_lifter = new Lifter();
    s_climber = new Climber();
    s_lifterArm = new LifterArm();
    _ntinst = NetworkTableInstance.getDefault();
    m_chooser = new SendableChooser<>();
  }

  public static Robot get() {
    if (_instance == null) {
      _instance = new Robot();
    }
    return _instance;
  }

  /**
   * Subsystem getters - returns subsystem of robot instance that called
   * @return
   */
  public NetworkTableInstance getNTInst() {
    return this._ntinst;
  }

  public OI getOI() {
    return this.s_oi;
  }

  public DriveTrain getDriveTrain() {
    return this.s_driveTrain;
  }

  public CargoGripper getCargoGripper() {
    return this.s_cargoGripper;
  }

  public HatchGripper getHatchGripper() {
    return this.s_hatchGripper;
  }

  public Lifter getLifter() {
    return this.s_lifter;
  }

  public LifterArm getLifterArm() {
    return this.s_lifterArm;
  }
  
  public Climber getClimber() {
    return this.s_climber;
  }

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default (Drive)", new DriveWithJoystick());
    s_oi = new OI();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   * 
   * Publishing values from lifter and lifterArm
   */
  @Override
  public void robotPeriodic() {
    getLifter().publishValues(getNTInst());
    getLifterArm().publishValues(getNTInst());
    getClimber().publishValues(getNTInst());
    if (getClimber().isClimbMode()) {
      getOI().getBaseJoystick().setRumble(RumbleType.kLeftRumble, 0.2);
      get().getOI().getBaseJoystick().setRumble(RumbleType.kRightRumble, 0.2);
    } else {
      Robot.get().getOI().getBaseJoystick().setRumble(RumbleType.kLeftRumble, 0);
      Robot.get().getOI().getBaseJoystick().setRumble(RumbleType.kRightRumble, 0);
    }
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}

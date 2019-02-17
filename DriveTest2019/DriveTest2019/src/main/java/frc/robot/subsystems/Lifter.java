/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotConstants;
import edu.wpi.first.wpilibj.command.Subsystem; 
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Robot;
import frc.robot.commands.lifter.MoveLiftSetpoint;
import frc.robot.commands.lifter.SetLiftSpeed;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Lifter extends Subsystem {
  private CANSparkMax m_motor, m_followMotor;
  private CANPIDController m_pidController;
  private CANEncoder m_encoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  private double initialEncoderVal, point = 0;

  public Lifter() {
    super();
    
    // initialize motor
    m_motor = new CANSparkMax(RobotConstants.LIFTER_MASTER_MOTOR, MotorType.kBrushless);
    m_followMotor = new CANSparkMax(RobotConstants. LIFTER_FOLLOWER_MOTOR, MotorType.kBrushless);
    m_followMotor.follow(m_motor);

    //initialize PID and encoder stuff
    m_pidController = m_motor.getPIDController();
    m_encoder = m_motor.getEncoder();
    initialEncoderVal = m_encoder.getPosition();

    // PID coefficients
    kP = 0.18; 
    kI = 0;//1e-4;
    kD = 0;//.5; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 0.5; 
    kMinOutput = -0.2;

    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);
  }

  //Returns the current height in inches (converts from encoder value)
  public double getHeightInches() {
      return (m_encoder.getPosition()-initialEncoderVal)/RobotConstants.ROTATIONS_PER_INCH;
  }

  public double getHeightSetpoint() {
    return point;
  }

  public void publishValues(NetworkTableInstance ntinst) {
    ntinst.getTable("Lifter").getEntry("Height Inches").setNumber(this.getHeightInches());
    ntinst.getTable("Lifter").getEntry("Current setpoint").setNumber(point);
    ntinst.getTable("Lifter").getEntry("BusVoltage").setNumber(m_motor.getBusVoltage());
    ntinst.getTable("Lifter").getEntry("AppliedOutput").setNumber(m_motor.getAppliedOutput());
    ntinst.getTable("Lifter").getEntry("OutputCurrent").setNumber(m_motor.getOutputCurrent());
  }

  /**
   * Gives the height setpoint to PID as a position.
   */

  public void setHeightInches(double height) {
      if (height>RobotConstants.MAX_LIFT_HEIGHT) height = RobotConstants.MAX_LIFT_HEIGHT;
      if (height<RobotConstants.MIN_LIFT_HEIGHT) height = RobotConstants.MIN_LIFT_HEIGHT;
      m_pidController.setReference(height*RobotConstants.ROTATIONS_PER_INCH+initialEncoderVal, ControlType.kPosition);
      point = height;
  }

  /** 
   * Sets lifter motor speed.
  */
  public void setSpeed (double speed) {
    //m_pidController.setReference(speed, ControlType.kVelocity);
    m_motor.set(-1*speed);
  }
 
  @Override
    public void initDefaultCommand() {
        setDefaultCommand(new MoveLiftSetpoint());
    }
}

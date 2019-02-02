/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.MotorConstants;
import edu.wpi.first.wpilibj.command.Subsystem; 
import edu.wpi.first.networktables.NetworkTableInstance;

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
  private double initialEncoderVal;
  public static double rotationsPerInch = 1.196*20/17.625;

  public Lifter() {
    super();
    
    // initialize motor
    m_motor = new CANSparkMax(MotorConstants.LIFTER_MASTER_MOTOR , MotorType.kBrushless);
    m_followMotor = new CANSparkMax(MotorConstants. LIFTER_FOLLOWER_MOTOR, MotorType.kBrushless);
    m_followMotor.follow(m_motor);

    m_pidController = m_motor.getPIDController();
    m_encoder = m_motor.getEncoder();
    initialEncoderVal = m_encoder.getPosition();

    // PID coefficients
    kP = 0.025; 
    kI = 0;//1e-4;
    kD = 0;//.5; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 0.5; 
    kMinOutput = -0.5;

    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);
  }

  public double getHeightInches() {
      return (m_encoder.getPosition()-initialEncoderVal)/rotationsPerInch;
  }

  public void publishValues(NetworkTableInstance ntinst) {
    ntinst.getTable("Lifter Arm").getEntry("Height Inches").setNumber(this.getHeightInches());
  }

  public void setHeightInches(double height) {
    m_pidController.setReference(height*rotationsPerInch+initialEncoderVal, ControlType.kPosition);
  }

  public void setSpeed (double speed) {
    m_pidController.setReference(speed, ControlType.kVelocity);
  }

  @Override
    public void initDefaultCommand() {
        setDefaultCommand(null);
    }
}

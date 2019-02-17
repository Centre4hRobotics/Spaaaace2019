/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotConstants;
import frc.robot.commands.lifter.MoveArmSetpoint;
import frc.robot.commands.lifter.SetArmSpeed;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTableInstance;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class LifterArm extends Subsystem {
  private CANSparkMax m_motor;
  private CANPIDController m_pidController;
  private CANEncoder m_encoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  private double initialEncoderVal, point;
  public static double armLength = RobotConstants.ARM_LENGTH;
  public static final double ARM_LIMIT = 19.0;

  public LifterArm() {
    super();
    point = RobotConstants.DEGREE_START;
    // initialize motor
    m_motor = new CANSparkMax(RobotConstants.LIFTER_ARM_MOTOR , MotorType.kBrushless);

    m_pidController = m_motor.getPIDController();
    m_encoder = m_motor.getEncoder();
    initialEncoderVal = m_encoder.getPosition();

    // PID coefficients
    kP = 0.025; 
    kI = 0;//1e-4;
    kD = 0;//.5; 
    kIz = 0; 
    kFF = 0; 
    kMaxOutput = 0.6; 
    kMinOutput = -0.6;

    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    this.setDegree(point);
  }

  public void resetInitialPosition() {
    initialEncoderVal = m_encoder.getPosition();
  }

  //0 is middle of arc
  public double getDegree() {
    return (m_encoder.getPosition()-initialEncoderVal)*RobotConstants.DEGREES_PER_ROTATION+RobotConstants.DEGREE_START;
  }

  public double getDegreeSetpoint() {
    return point;
  }

  //0 is middle of wrist arc
  public double getHeightInches() {
    return armLength*Math.sin(getDegree());
  }

  public void overrideAngle(double angleSet) {
    initialEncoderVal = angleSet;
  }

  public boolean willBeInsideFramePerimeter(double height) {
    return Math.sqrt(Math.pow(armLength, 2) - Math.pow(height,2))<20;
  }

  public void publishValues(NetworkTableInstance ntinst) {
      ntinst.getTable("Lifter Arm").getEntry("Height Inches").setNumber(this.getHeightInches());
      ntinst.getTable("Lifter Arm").getEntry("Degrees").setNumber(this.getDegree());
      ntinst.getTable("Lifter Arm").getEntry("Encoder Val").setNumber(m_encoder.getPosition());
      ntinst.getTable("Lifter Arm").getEntry("Current setpoint").setNumber(point);
      ntinst.getTable("Lifter Arm").getEntry("Applied Output").setNumber(m_motor.getAppliedOutput());
      ntinst.getTable("Lifter Arm").getEntry("Bus Voltage").setNumber(m_motor.getBusVoltage());
      ntinst.getTable("Lifter Arm").getEntry("Output Current").setNumber(m_motor.getOutputCurrent());
  }

  public void setHeightInches(double height) {
    if (Math.abs(height)<ARM_LIMIT)
      setDegree(Math.asin(height/armLength));
  }

  public void setDegree(double degree) {
      m_pidController.setReference((degree+initialEncoderVal-RobotConstants.DEGREE_START)/RobotConstants.DEGREES_PER_ROTATION, ControlType.kPosition);
      point = degree;
  }

  public void setSpeed (double speed) {
    //m_pidController.setReference(speed, ControlType.kVelocity);
    m_motor.set(-1*speed);
  }

  @Override
    public void initDefaultCommand() {
        setDefaultCommand(new MoveArmSetpoint());
    }
}

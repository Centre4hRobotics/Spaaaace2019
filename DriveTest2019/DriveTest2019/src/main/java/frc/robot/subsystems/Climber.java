package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.climber.*;
import frc.robot.RobotConstants;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.networktables.NetworkTableInstance;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;

public class Climber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private TalonSRX motorFL = null, motorFR = null, motorBL = null, motorBR = null;
    //private Encoder encoderFL = null, encoderFR = null, encoderBL = null, encoderBR = null;
    private PWMVictorSPX motorWheel = null;
    private boolean climbMode = false;
    public static int kTimeoutConst = 30;


    public Climber() {
        super();
        /*motorFL = new Spark(RobotConstants.CLIMBER_FL_MOTOR);
        motorFR = new Spark(RobotConstants.CLIMBER_FR_MOTOR);
        motorBL = new Spark(RobotConstants.CLIMBER_BL_MOTOR);
        motorBR = new Spark(RobotConstants.CLIMBER_BR_MOTOR);

        motorFR.setInverted(true);
        motorBR.setInverted(true);

        encoderFL = new Encoder(4,5,false);
        encoderFR = new Encoder(2,3,false);
        encoderBL = new Encoder(8,9,false);
        encoderBR = new Encoder(6,7,false);

        encoderFL.reset();
        encoderFR.reset();
        encoderBL.reset();
        encoderBR.reset();
        encoderFL.setDistancePerPulse(RobotConstants.CLIMBER_DPP);
        encoderFR.setDistancePerPulse(RobotConstants.CLIMBER_DPP);
        encoderBL.setDistancePerPulse(RobotConstants.CLIMBER_DPP);
        encoderBR.setDistancePerPulse(RobotConstants.CLIMBER_DPP);*/
        motorFL = new TalonSRX(RobotConstants.CLIMBER_FL_MOTOR);
        motorFR = new TalonSRX(RobotConstants.CLIMBER_FR_MOTOR);
        motorBL = new TalonSRX(RobotConstants.CLIMBER_BL_MOTOR);
        motorBR = new TalonSRX(RobotConstants.CLIMBER_BR_MOTOR);

        motorFR.setInverted(true);
        motorBL.setInverted(true);

        motorFL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutConst);
        motorFR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutConst);
        motorBL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutConst);
        motorBR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutConst);

        motorFL.setSensorPhase(true);
        motorFR.setSensorPhase(false);
        motorBL.setSensorPhase(false);
        motorBR.setSensorPhase(true);

        motorFL.setSelectedSensorPosition(0);
        motorFR.setSelectedSensorPosition(0);
        motorBL.setSelectedSensorPosition(0);
        motorBR.setSelectedSensorPosition(0);

        motorFL.config_kP(0,RobotConstants.CLIMBER_KP, kTimeoutConst);

        motorWheel = new PWMVictorSPX(RobotConstants.CLIMBER_WHEEL_MOTOR);
    }

    public void publishValues(NetworkTableInstance ntinst) {
        ntinst.getTable("Climber").getEntry("Encoder FL").setNumber(getEncoderFL());
        ntinst.getTable("Climber").getEntry("Encoder FR").setNumber(getEncoderFR());
        ntinst.getTable("Climber").getEntry("Encoder BL").setNumber(getEncoderBL());
        ntinst.getTable("Climber").getEntry("Encoder BR").setNumber(getEncoderBR());
        ntinst.getTable("Climber").getEntry("Climb Mode").setBoolean(isClimbMode());
      }

    //module 4 is all, 5 is front, 6 is back
    public void setPosition(double position, int module) {
        double pos = position/RobotConstants.CLIMBER_INCHES_PER_TICK;
        if (module == 0) motorFL.set(ControlMode.Position, pos);
        else if (module == 1) motorFR.set(ControlMode.Position, pos);
        else if (module == 2) motorBL.set(ControlMode.Position, pos);
        else if (module == 3) motorBR.set(ControlMode.Position, pos);
        else if (module == 4) {
            motorFL.set(ControlMode.Position, pos);
            motorFR.set(ControlMode.Position, pos);
            motorBL.set(ControlMode.Position, pos);
            motorBR.set(ControlMode.Position, pos);
        }
        else if (module == 5) {
            motorFL.set(ControlMode.Position, pos);
            motorFR.set(ControlMode.Position, pos);
        }
        else if (module == 6) {
            motorBL.set(ControlMode.Position, pos);
            motorBR.set(ControlMode.Position, pos);
        }
    }

    public void setSpeed(double speed, int module) {
        if (module == 0) motorFL.set(ControlMode.PercentOutput, speed);
        else if (module == 1) motorFR.set(ControlMode.PercentOutput, speed);
        else if (module == 2) motorBL.set(ControlMode.PercentOutput, speed);
        else if (module == 3) motorBR.set(ControlMode.PercentOutput, speed);
        else if (module == 4) {
            motorFL.set(ControlMode.PercentOutput, speed);
            motorFR.set(ControlMode.PercentOutput, speed);
            motorBL.set(ControlMode.PercentOutput, speed);
            motorBR.set(ControlMode.PercentOutput, speed);
        }
        else if (module == 5) {
            motorFL.set(ControlMode.PercentOutput, speed);
            motorFR.set(ControlMode.PercentOutput, speed);
        }
        else if (module == 6) {
            motorBL.set(ControlMode.PercentOutput, speed);
            motorBR.set(ControlMode.PercentOutput, speed);
        }
    }

    /*public void setFLSpeed(double speed) {
        motorFL.set(speed);
    }

    public void setFRSpeed(double speed) {
        motorFR.set(speed);
    }

    public void setBLSpeed(double speed) {
        motorBL.set(speed);
    }

    public void setBRSpeed(double speed) {
        motorBR.set(speed);
    }*/

    public double getEncoderFL () {
        return motorFL.getSelectedSensorPosition()*RobotConstants.CLIMBER_INCHES_PER_TICK-0.875;
    }

    public double getEncoderFR () {
        return motorFR.getSelectedSensorPosition()*RobotConstants.CLIMBER_INCHES_PER_TICK-0.625;
        //return encoderFR.getDistance()-1;
    }

    public double getEncoderBL () {
        return motorBL.getSelectedSensorPosition()*RobotConstants.CLIMBER_INCHES_PER_TICK-0.25;
    }

    public double getEncoderBR () {
        return motorBR.getSelectedSensorPosition()*RobotConstants.CLIMBER_INCHES_PER_TICK;
    }

    public void setWheelSpeed (double speed) {
        motorWheel.set(speed);
    }

    public boolean isClimbMode () {
        return climbMode;
    }

    public void setClimbMode (boolean state) {
        climbMode = state;
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ClimberDriveManual());
    }
}

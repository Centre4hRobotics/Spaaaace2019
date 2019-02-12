package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotConstants;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;

public class Climber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Spark motorFL = null, motorFR = null, motorBL = null, motorBR = null;
    private Encoder encoderFL = null, encoderFR = null, encoderBL = null, encoderBR = null;
    private PWMVictorSPX motorWheel = null;


    public Climber() {
        super();
        motorFL = new Spark(RobotConstants.CLIMBER_FL_MOTOR);
        motorFR = new Spark(RobotConstants.CLIMBER_FR_MOTOR);
        motorBL = new Spark(RobotConstants.CLIMBER_BL_MOTOR);
        motorBR = new Spark(RobotConstants.CLIMBER_BR_MOTOR);

        encoderFL = new Encoder(0,1,false);
        encoderFR = new Encoder(2,3,false);
        encoderBL = new Encoder(4,5,false);
        encoderBR = new Encoder(6,7,false);

        encoderFL.setDistancePerPulse(RobotConstants.CLIMBER_DISTANCE_PER_PULSE);
        encoderFR.setDistancePerPulse(RobotConstants.CLIMBER_DISTANCE_PER_PULSE);
        encoderBL.setDistancePerPulse(RobotConstants.CLIMBER_DISTANCE_PER_PULSE);
        encoderBR.setDistancePerPulse(RobotConstants.CLIMBER_DISTANCE_PER_PULSE);

        motorWheel = new PWMVictorSPX(RobotConstants.CLIMBER_WHEEL_MOTOR);
    }

    public void setFLSpeed(double speed) {
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
    }

    public Encoder getEncoderFL () {
        return encoderFL;
    }

    public Encoder getEncoderFR () {
        return encoderFR;
    }

    public Encoder getEncoderBL () {
        return encoderBL;
    }

    public Encoder getEncoderBR () {
        return encoderBR;
    }

    public void setWheelSpeed (double speed) {
        motorWheel.set(speed);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(null);
    }
}

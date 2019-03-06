package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.climber.*;
import frc.robot.RobotConstants;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Climber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Spark motorFL = null, motorFR = null, motorBL = null, motorBR = null;
    private Encoder encoderFL = null, encoderFR = null, encoderBL = null, encoderBR = null;
    private PWMVictorSPX motorWheel = null;
    private boolean climbMode = false;


    public Climber() {
        super();
        motorFL = new Spark(RobotConstants.CLIMBER_FL_MOTOR);
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
        encoderBR.setDistancePerPulse(RobotConstants.CLIMBER_DPP);
        

        motorWheel = new PWMVictorSPX(RobotConstants.CLIMBER_WHEEL_MOTOR);
    }

    public void publishValues(NetworkTableInstance ntinst) {
        ntinst.getTable("Climber").getEntry("Encoder FL").setNumber(getEncoderFL());
        ntinst.getTable("Climber").getEntry("Encoder FR").setNumber(getEncoderFR());
        ntinst.getTable("Climber").getEntry("Encoder BL").setNumber(getEncoderBL());
        ntinst.getTable("Climber").getEntry("Encoder BR").setNumber(getEncoderBR());
        ntinst.getTable("Climber").getEntry("Climb Mode").setBoolean(isClimbMode());
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

    public double getEncoderFL () {
        return encoderFL.getDistance()-1;
    }

    public double getEncoderFR () {
        return encoderFR.getDistance()-1;
    }

    public double getEncoderBL () {
        return encoderBL.getDistance();
    }

    public double getEncoderBR () {
        return encoderBR.getDistance();
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
        setDefaultCommand(new ClimberEncoderDriveManual());
    }
}

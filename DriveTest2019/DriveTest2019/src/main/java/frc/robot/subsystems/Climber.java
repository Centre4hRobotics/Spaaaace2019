package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotConstants;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Spark;

public class Climber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Spark motorFL = null, motorFR = null, motorBL = null, motorBR = null;
    private PWMVictorSPX motorWheel = null;


    public Climber() {
        super();
        motorFL = new Spark(RobotConstants.CLIMBER_FL_MOTOR);
        motorFR = new Spark(RobotConstants.CLIMBER_FR_MOTOR);
        motorBL = new Spark(RobotConstants.CLIMBER_BL_MOTOR);
        motorBR = new Spark(RobotConstants.CLIMBER_BR_MOTOR);
        motorWheel = new PWMVictorSPX(RobotConstants.CLIMBER_WHEEL_MOTOR);
    }

    public void setFrontSpeed(double speed) {
        motorFL.set(speed);
        motorFR.set(speed);
    }

    public void setBackSpeed(double speed) {
        motorBL.set(speed);
        motorBR.set(speed);
    }

    public void setWheelSpeed (double speed) {
        motorWheel.set(speed);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(null);
    }
}

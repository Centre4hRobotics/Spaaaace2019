package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.MotorConstants;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.Spark;;

public class Climber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Spark motorFL = null, motorFR = null, motorBL = null, motorBR = null;
    private PWMVictorSPX motorWheel = null;


    public Climber() {
        super();
        motorFL = new Spark(19);
        motorFR = new Spark(18);
        motorBL = new Spark(17);
        motorBR = new Spark(16);
        motorWheel = new PWMVictorSPX(15);
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

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.MotorConstants;
import edu.wpi.first.wpilibj.PWMVictorSPX;;

public class Climber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private PWMVictorSPX motorFront = null;
    private PWMVictorSPX motorBack = null;


    public Climber() {
        super();
        motorFront = new PWMVictorSPX(MotorConstants.CLIMBER_FRONT_MOTOR);
        motorBack = new PWMVictorSPX(MotorConstants.CLIMBER_BACK_MOTOR);
    }

    public void setFrontSpeed(double speed) {
        motorFront.set(speed);
    }

    public void setBackSpeed(double speed) {
        motorBack.set(speed);
    }

    @Override
    public void initDefaultCommand() {
        // setDefaultCommand(new DriveWithJoystick());
    }
}

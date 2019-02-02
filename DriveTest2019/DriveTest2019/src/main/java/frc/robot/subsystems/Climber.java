package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMTalonSRX;;

public class Climber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    PWMTalonSRX motorFR = null;
    PWMTalonSRX motorFL = null;
    PWMTalonSRX motorBR = null;
    PWMTalonSRX motorBL = null;
    


    public Climber() {
        super();
        motorFR = new PWMTalonSRX(0);
        motorFL = new PWMTalonSRX(1);
        motorBR = new PWMTalonSRX(2);
        motorBL = new PWMTalonSRX(3);

    }

    public void setFrontSpeed(double speed) {
        motorFR.set(speed);
        motorFL.set(speed);
    }

    public void setBackSpeed(double speed) {
        motorBR.set(speed);
        motorBL.set(speed);
    }

    @Override
    public void initDefaultCommand() {
        // setDefaultCommand(new DriveWithJoystick());
    }
}

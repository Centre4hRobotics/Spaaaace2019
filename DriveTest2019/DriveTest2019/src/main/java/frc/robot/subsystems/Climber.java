package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMTalonSRX;;

public class Climber extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    PWMTalonSRX motor0 = null;
    PWMTalonSRX motor1 = null;
    PWMTalonSRX motor2 = null;
    PWMTalonSRX motor3 = null;
    


    public Climber() {
        super();
        motor0 = new PWMTalonSRX(0);
        motor1 = new PWMTalonSRX(1);
        motor2 = new PWMTalonSRX(2);
        motor3 = new PWMTalonSRX(3);

    }

    public void setClimbSpeed(double speed) {
        motor0.set(speed);
        motor1.set(speed);
        motor2.set(speed);
        motor3.set(speed);

    }

    @Override
    public void initDefaultCommand() {
        // setDefaultCommand(new DriveWithJoystick());
    }
}

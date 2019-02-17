package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.RobotConstants;

/**
 * Controls the motor that spins the ball intake/outtake
 */
public class CargoGripper extends Subsystem {
    private Spark ballMotor = null;
    private DigitalInput limitSwitch = null;

    public CargoGripper() {
        super();
        ballMotor = new Spark(RobotConstants.GRIPPER_BALL_MOTOR);
        limitSwitch = new DigitalInput(0);
    }

    public boolean isSwitchTriggered() {
        return !limitSwitch.get();
    }

    public void setBallSpeed(double speed) {
        ballMotor.set(speed);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(null);
    }
}

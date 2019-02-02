package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.MotorConstants;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Gripper extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Spark ballMotor = null;
    Spark hatchMotor = null;

    public Gripper() {
        super();
        ballMotor = new Spark(MotorConstants.GRIPPER_BALL_MOTOR);
        hatchMotor = new Spark(MotorConstants.GRIPPER_HATCH_MOTOR);
    }

    public void setBallSpeed(double speed) {
        ballMotor.set(speed);
    }

    public void setHatchSpeed(double speed) {
        hatchMotor.set(speed);
    }

    @Override
    public void initDefaultCommand() {
        // setDefaultCommand(new DriveWithJoystick());
    }
}

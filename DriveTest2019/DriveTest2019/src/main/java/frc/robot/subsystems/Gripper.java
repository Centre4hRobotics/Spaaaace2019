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
    private Spark ballMotor = null;
    private Spark hatchMotor = null;

    //true is up, false is down
    private boolean hatchState = true;

    public Gripper() {
        super();
        ballMotor = new Spark(MotorConstants.GRIPPER_BALL_MOTOR);
        hatchMotor = new Spark(MotorConstants.GRIPPER_HATCH_MOTOR);
    }

    public boolean getHatchState() {
        return hatchState;
    }

    public void setHatchState(boolean state) {
        hatchState = state;
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

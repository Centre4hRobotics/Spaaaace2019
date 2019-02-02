package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.MotorConstants;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class HatchGripper extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private Spark hatchMotor = null;

    //true is up, false is down
    private boolean hatchState = true;

    public HatchGripper() {
        super();
        hatchMotor = new Spark(MotorConstants.GRIPPER_HATCH_MOTOR);
    }

    public boolean getHatchState() {
        return hatchState;
    }

    public void setHatchState(boolean state) {
        hatchState = state;
    }

    //Sets the hatch motor speed
    public void setHatchSpeed(double speed) {
        hatchMotor.set(speed);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(null);
    }
}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.RobotConstants;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class CargoGripper extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private Spark ballMotor = null;
  

    public CargoGripper() {
        super();
        ballMotor = new Spark(RobotConstants.GRIPPER_BALL_MOTOR);
        
    }

    public void setBallSpeed(double speed) {
        ballMotor.set(speed);
    }


    @Override
    public void initDefaultCommand() {
        setDefaultCommand(null);
    }
}

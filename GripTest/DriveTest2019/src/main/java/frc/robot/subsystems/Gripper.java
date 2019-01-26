package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.*;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Gripper extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Spark ballMotor = null;

    public Gripper() {
        super();
        ballMotor = new Spark(0);
    }

    public void setSpeed(double speed) {
        ballMotor.set(speed);
    }

    @Override
    public void initDefaultCommand() {
        // setDefaultCommand(new DriveWithJoystick());
    }
}

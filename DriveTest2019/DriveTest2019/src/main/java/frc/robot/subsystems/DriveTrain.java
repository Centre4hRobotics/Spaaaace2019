
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import frc.robot.RobotConstants;
import frc.robot.commands.drive.DriveWithJoystick;
import frc.robot.Robot;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private DifferentialDrive _drive = null;
    private double lastSpeed;

    public DriveTrain() {
        super();
        this._drive = new DifferentialDrive(new PWMVictorSPX(RobotConstants.DRIVE_MOTOR_LEFT),
                new PWMVictorSPX(RobotConstants.DRIVE_MOTOR_RIGHT));
        lastSpeed = 0.0;
    }

    public void drive(XboxController stick) {
        double speed = stick.getY(Hand.kLeft);
        double steer = stick.getX(Hand.kLeft);

        double slow_speed = stick.getY(Hand.kRight);
        double slow_steer = stick.getX(Hand.kRight);

        if (Math.abs(speed) < 0.07)
            speed = 0;
        if (Math.abs(steer) < 0.07)
            steer = 0;

        if (Math.abs(slow_speed) > 0.3 || Math.abs(slow_steer) > 0.3) {
            speed = slow_speed * RobotConstants.SLOW_SPEED_MULT;
            steer = slow_steer * RobotConstants.SLOW_STEER_MULT;
        }

        lastSpeed = RobotConstants.REDUCE_PERCENT * speed + (1 - RobotConstants.REDUCE_PERCENT) * lastSpeed;
        drive(lastSpeed, steer);
    }

    public void drive(double speed, double steer) {
        //Maybe timed rumbles for game events (not a joke)?
        // ARE YOU READY TO RUMBLE (joke)
        /*
        if (Math.abs(speed) > 0) {
            if (steer > 0) {
                Robot.get().getOI().getBaseJoystick().setRumble(RumbleType.kRightRumble, 1.0);
            } else if (steer < 0) {
                Robot.get().getOI().getBaseJoystick().setRumble(RumbleType.kLeftRumble, 1.0);
            } else {
                Robot.get().getOI().getBaseJoystick().setRumble(RumbleType.kLeftRumble, 1.0);
                Robot.get().getOI().getBaseJoystick().setRumble(RumbleType.kRightRumble, 1.0);
            }

        } else {
            Robot.get().getOI().getBaseJoystick().setRumble(RumbleType.kLeftRumble, 0);
            Robot.get().getOI().getBaseJoystick().setRumble(RumbleType.kRightRumble, 0);

        }*/
        _drive.arcadeDrive(speed, steer, false);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
}

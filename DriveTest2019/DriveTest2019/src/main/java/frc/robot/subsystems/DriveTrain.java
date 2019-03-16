
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.RobotConstants;
import frc.robot.commands.drive.DriveWithJoystick;
import frc.robot.Robot;
//import frc.robot.Robot;
//import edu.wpi.first.wpilibj.GenericHID.RumbleType;

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

    public double reduceSpeed(double speed) {
        double height = Robot.get().getLifter().getHeightInches();
        if (height>RobotConstants.START_REDUCE_SPEED_HEIGHT) {
            double mult = ((height-RobotConstants.START_REDUCE_SPEED_HEIGHT)
            *RobotConstants.REDUCE_SPEED_MULT+RobotConstants.REDUCE_SPEED_CONST);
            if (mult < 0.75) mult = 0.75;
            return speed*mult;
        }
        return speed;
    }

    public void drive(XboxController stick) {
        double speed = stick.getTriggerAxis(Hand.kLeft);
        double steer = 0;

        if (speed<stick.getTriggerAxis(Hand.kRight)) {
            speed = stick.getTriggerAxis(Hand.kRight)*-1;
        }
        
        double override_speed = stick.getY(Hand.kLeft);
        double override_steer = stick.getX(Hand.kLeft);

        if (Math.abs(override_speed) > 0.30 || Math.abs(override_steer) > 0.30) {
            speed = override_speed;
            steer = override_steer;
        }

        if (Math.abs(speed) < 0.15)
            speed = 0;
        if (Math.abs(steer) < 0.15)
            steer = 0;

        if (speed>0.25) steer+=0.2;

        override_speed = stick.getY(Hand.kRight);
        override_steer = stick.getX(Hand.kRight);
        
        if (Math.abs(override_speed) > 0.3 || Math.abs(override_steer) > 0.3) {
            speed = override_speed * RobotConstants.SLOW_SPEED_MULT;
            steer = override_steer * RobotConstants.SLOW_STEER_MULT;
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

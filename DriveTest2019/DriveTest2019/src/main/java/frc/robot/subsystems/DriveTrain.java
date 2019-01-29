
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.DriveWithJoystick;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
    private final DifferentialDrive _drive = new DifferentialDrive(new PWMVictorSPX(1), new PWMVictorSPX(0));
    public static final double SLOW_SPEED_MULT = 0.375;
    public static final double SLOW_STEER_MULT = 0.5;
    public static final double REDUCE_PERCENT = 0.1;
    private double lastSpeed = 0.0;

    public void drive(XboxController stick)
    {
        double speed = stick.getY(Hand.kLeft);
        double steer = stick.getX(Hand.kLeft);

        double slow_speed = stick.getY(Hand.kRight);
        double slow_steer = stick.getX(Hand.kRight);

        if (Math.abs(speed)<0.07) speed = 0;
        if (Math.abs(steer)<0.07) steer = 0;

        if (Math.abs(slow_speed) > 0.3 || Math.abs(slow_steer) > 0.3) {
            speed = slow_speed * SLOW_SPEED_MULT;
            steer = slow_steer * SLOW_STEER_MULT; 
        }
        
        lastSpeed = REDUCE_PERCENT*speed + (1-REDUCE_PERCENT)*lastSpeed;
        drive(lastSpeed, steer);
    }

    public void drive(double speed, double steer) {
        _drive.arcadeDrive(speed, steer, false);
    } 

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
}

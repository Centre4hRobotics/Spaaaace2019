
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
  
    public void drive(XboxController stick)
    {
        double speed = stick.getY(Hand.kLeft);
        double steer = stick.getX(Hand.kLeft);
        if (speed<0.15 && speed>-0.15) speed = 0;
        if (steer<0.15 && steer>-0.15) steer = 0;
        drive(speed, steer);
    }

    public void drive(double speed, double steer) {
        _drive.arcadeDrive(speed, steer, false);
    } 

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
}

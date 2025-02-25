/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

/**
 * An example command. You can replace me with your own command.
 */
public class DriveFullClimber extends Command {
    private double height, backDiff, avg;
    //backDiff-how much different back target height is from front.
    /**Mults is either 1,0.5, or zero for each
     * adjusts is the adjustment value for each motor from -1 to 1
     * dists is the current encoder readings
     * inputs is what is going into setSpeed
     */
    private double[] mults, adjusts, dists, inputs;//, avgs;
    private int dir;

    public DriveFullClimber(double height, double backDiff) {
        // Use requires() here to declare subsystem dependencies
        this.height = height;
        requires(Robot.get().getClimber());
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        dists = new double[4];
        dists[0] = Robot.get().getClimber().getEncoderFL();
        dists[1] = Robot.get().getClimber().getEncoderFR();
        dists[2] = Robot.get().getClimber().getEncoderBL()+backDiff; 
        dists[3] = Robot.get().getClimber().getEncoderBR()+backDiff;
        Robot.get().getOI().getBaseJoystick().setRumble(RumbleType.kRightRumble, 0);
        //Robot.get().getClimber().setPosition(height, 4);
        avg = 0.25*(dists[0]+dists[1]+dists[2]+dists[3]);
        /*avgs = new double[2];
        avgs[0] = 0.5*(dists[0]+dists[1]);
        avgs[1] = 0.5*(dists[2]+dists[3]);*/
        mults = new double[4];
        for (int i = 0; i<4; i++) mults[i] = 1;
        adjusts = new double[4];
        inputs = new double[4];
    }

    @Override
    protected void execute() {
        dists[0] = Robot.get().getClimber().getEncoderFL();
        dists[1] = Robot.get().getClimber().getEncoderFR();
        dists[2] = Robot.get().getClimber().getEncoderBL()+backDiff; 
        dists[3] = Robot.get().getClimber().getEncoderBR()+backDiff;
        
        avg = 0.25*(dists[0]+dists[1]+dists[2]+dists[3]);
        /*avgs[0] = 0.5*(dists[0]+dists[1]);
        avgs[1] = 0.5*(dists[2]+dists[3]);*/
        if (height<avg) dir = 1;
        else dir = -1;
        //pos 0 is fl, pos 1 is fr, pos 2 is bl, pos 3 is br
        for (int i = 0; i<4; i++) {
            if (Math.abs(dists[i]-height)<1) mults[i]=0.5;
            if (Math.abs(dists[i]-height)<0.2) mults[i] = 0;
            adjusts[i] = Math.max(Math.min(dists[i]-avg, 1), -1);
            inputs[i] = mults[i]*(dir*RobotConstants.CLIMBER_BASE_SPEED+RobotConstants.CLIMBER_ADJUST_SPEED*adjusts[i]);
            Robot.get().getNTInst().getTable("Climber Test").getEntry("Mult " + i).setNumber(mults[i]);
            Robot.get().getNTInst().getTable("Climber Test").getEntry("Input " + i).setNumber(inputs[i]);
            Robot.get().getNTInst().getTable("Climber Test").getEntry("Adjust " + i).setNumber(adjusts[i]);
            Robot.get().getClimber().setSpeed(inputs[i], i);
        }
        Robot.get().getNTInst().getTable("Climber Test").getEntry("Dir").setNumber(dir);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if (!Robot.get().getClimber().isClimbMode()) return false;
        for (int i = 0; i<4; i++) {
            if (Math.abs(dists[i]-height)>0.2) return false;
        }
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.get().getClimber().setSpeed(0.0,4);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}

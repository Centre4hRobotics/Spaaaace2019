package frc.robot;

public final class RobotConstants {
    //PWM IDs:
    public static final int DRIVE_MOTOR_LEFT = 0;    
    public static final int DRIVE_MOTOR_RIGHT = 1;  
    public static final int GRIPPER_BALL_MOTOR = 8;
    public static final int GRIPPER_HATCH_MOTOR = 7;
    public static final int CLIMBER_FL_MOTOR = 2;
    public static final int CLIMBER_FR_MOTOR = 3;
    public static final int CLIMBER_BL_MOTOR = 4;
    public static final int CLIMBER_BR_MOTOR = 5;
    public static final int CLIMBER_WHEEL_MOTOR = 6;
    
    //CAN IDs: 
    public static final int LIFTER_MASTER_MOTOR = 1;
    public static final int LIFTER_FOLLOWER_MOTOR = 2;
    public static final int LIFTER_ARM_MOTOR = 3;

    //DriveTrain:
    public static final double SLOW_SPEED_MULT = 0.375;
    public static final double SLOW_STEER_MULT = 0.5;
    public static final double REDUCE_PERCENT = 0.1;

    //Climber:
    public static final double CLIMBER_SPEED_MULT = 0.1337;
    
    //Lifter:
    public static final double ROTATIONS_PER_INCH = 1.196*20/17.625;

    //SetLiftSpeed
    public static final double LIFT_SPEED_MULT = 0.5;

    //LifterArm:
    public static final double DEGREES_PER_ROTATION = 360/212.5;
    public static final double DEGREE_START = 70;
    public static final double ARM_LENGTH = 21.0;

    //SetArmSpeed
    public static final double ARM_SPEED_MULT = 0.5;

    //FindTargets:
    public static final double TARGET_STEER_MULT = 0.6;
    public static final double TARGET_SPEED_MULT = 0.35;

    //FollowCargo:
    public static final double CARGO_STEER_MULT = 0.4;
    public static final double CARGO_SPEED_MULT = 0.35;
    
}
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

    //DriveFront(/Back)Climber
    public static final double CLIMBER_BASE_SPEED = 0.85;
    public static final double CLIMBER_ADJUST_SPEED = 0.1;
    public static final double CLIMBER_DISTANCE_PER_PULSE = 1;
    
    //Lifter:
    public static final double ROTATIONS_PER_INCH = 1.262;//1.196*20/17.625*20/21.5;

    //SetLiftSpeed
    public static final double LIFT_SPEED_MULT = 0.5;

    //MoveLiftSetpoint
    public static final double LIFT_MANUAL_DELTA = 0.2;

    //LifterArm:
    public static final double DEGREES_PER_ROTATION = 360/212.5;
    public static final double DEGREE_START = 71.15;
    public static final double ARM_LENGTH = 21.0;

    //SetArmSpeed
    public static final double ARM_SPEED_MULT = 0.5;

    //MoveArmSetpoint
    public static final double ARM_MANUAL_DELTA = 0.6;

    //FindTargets:
    public static final double TARGET_STEER_MULT = 0.6;
    public static final double TARGET_SPEED_MULT = 0.35;

    //FollowCargo:
    public static final double CARGO_STEER_MULT = 0.4;
    public static final double CARGO_SPEED_MULT = 0.35;
}
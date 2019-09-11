package frc.robot;

public final class RobotConstants {
    //PWM IDs:
    public static final int DRIVE_MOTOR_LEFT = 0;    
    public static final int DRIVE_MOTOR_RIGHT = 1;  
    public static final int GRIPPER_BALL_MOTOR = 8;
    public static final int GRIPPER_HATCH_MOTOR = 7;
    public static final int CLIMBER_WHEEL_MOTOR = 6;
    
    //CAN IDs: 
    public static final int LIFTER_MASTER_MOTOR = 1;
    public static final int LIFTER_FOLLOWER_MOTOR = 2;
    public static final int LIFTER_ARM_MOTOR = 3;
    public static final int CLIMBER_FL_MOTOR = 14;
    public static final int CLIMBER_FR_MOTOR = 5;
    public static final int CLIMBER_BL_MOTOR = 16;
    public static final int CLIMBER_BR_MOTOR = 7;

    //DriveTrain:
    public static final double SLOW_SPEED_MULT = 0.375;
    public static final double SLOW_STEER_MULT = 0.5;
    public static final double REDUCE_PERCENT = 0.1;
    public static final double START_REDUCE_SPEED_HEIGHT = 12.0;
    public static final double REDUCE_SPEED_MULT = -1/40.0;
    public static final double REDUCE_SPEED_CONST = 1.0;

    //Climber: 
    public static final double CLIMBER_SPEED_MULT = .7;
    public static final double CLIMB_WHEEL_MULT = 0.5;
    public static final double CLIMBER_DPP = -0.00001158;
    public static final double CLIMBER_ENCODER_DPP_FL = -0.0000132823;//12.5/(-941100);
    public static final double CLIMBER_ENCODER_DPP_FR = -0.00001348;//12.5/(-927300)
    public static final double CLIMBER_ENCODER_DPP_BL = -0.0000118525;//13.5/(-1139000);
    public static final double CLIMBER_ENCODER_DPP_BR = -0.000013;//guess
    public static final double CLIMBER_KP = 0.05;
    public static final double CLIMBER_INCHES_PER_TICK = 0.0000291618;
    public static final double CLIMBER_BACK_ADJUST = 0.9;

    //DriveFront(/Back)Climber
    public static final double CLIMBER_BASE_SPEED = 0.75;
    public static final double CLIMBER_ADJUST_SPEED = 0.25;
    public static final double CLIMBER_MANUAL_SPEED_MULT = 0.8;
    
    //Lifter:
    public static final double ROTATIONS_PER_INCH = 1.262;//1.196*20/17.625*20/21.5;
    public static final double MAX_LIFT_HEIGHT = 54; //value from testing was 54.6
    public static final double MIN_LIFT_HEIGHT = 0;

    //SetLiftSpeed
    public static final double LIFT_SPEED_MULT = 0.5;

    //MoveLiftSetpoint
    public static final double LIFT_MANUAL_DELTA = -0.25;

    //LifterArm:
    public static final double DEGREES_PER_ROTATION = 360/212.5;
    public static final double DEGREE_START = 0;//71.15;
    public static final double ARM_FLOOR_DEGREE = -80;
    public static final double ARM_LENGTH = 21.0;
    public static final double MIN_ARM_DEGREE = -100;
    public static final double MAX_ARM_DEGREE = 0;

    //SetArmSpeed
    public static final double ARM_SPEED_MULT = 0.5;

    //MoveArmSetpoint
    public static final double ARM_MANUAL_DELTA = 0.6;

    //FindTargets(Straight):
    public static final double TARGET_STEER_MULT = 0.75;
    public static final double TARGET_SPEED_MULT = 1;//0.32;
    public static final double TARGET_AREA = 2.2;
    public static final double TARGET_ANGLE_MULT = 0.13;
    public static final double TARGET_STRAIGHT_SPEED_MULT = 0.035;
    public static final double TARGET_STRAIGHT_STEER_MULT = 0.6;
    public static final double DISTANCE_STRAIGHT_START = 0.175;

    //FollowCargo:
    public static final double CARGO_STEER_MULT = 0.4;
    public static final double CARGO_SPEED_MULT = 0.35;
}
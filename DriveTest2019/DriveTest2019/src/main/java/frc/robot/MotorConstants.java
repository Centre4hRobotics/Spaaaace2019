package frc.robot;

public final class MotorConstants {
    //Motor Constants:
    public static final int DRIVE_MOTOR_RIGHT = 1;
    public static final int DRIVE_MOTOR_LEFT = 0;  
    public static final int LIFTER_MASTER_MOTOR = 2;
    public static final int LIFTER_FOLLOWER_MOTOR = 3;
    public static final int LIFTER_ARM_MOTOR = 4;
    public static final int GRIPPER_BALL_MOTOR = 18;
    public static final int GRIPPER_HATCH_MOTOR = 19;
    public static final int CLIMBER_FRONT_MOTOR = 17;
    public static final int CLIMBER_BACK_MOTOR = 16;

    //DriveTrain:
    public static final double SLOW_SPEED_MULT = 0.375;
    public static final double SLOW_STEER_MULT = 0.5;
    public static final double REDUCE_PERCENT = 0.1;

    //Lifter:
    public static double rotationsPerInch = 1.196*20/17.625;
    public static double LIFT_SPEED_MULT = 0.2;

    //LifterArm:
    public static double degreesPerRotation = 360/212.5;
    public static double degreeStart = 70;
    public static double armLength = 21.0;
    public static double ARM_SPEED_MULT = 0.2;

    //FindTargets:
    public static final double TARGET_STEER_MULT = 0.6;
    public static final double TARGET_SPEED_MULT = 0.35;

    //FollowCargo:
    public static final double CARGO_STEER_MULT = 0.4;
    public static final double CARGO_SPEED_MULT = 0.35;

}
package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

@Config
public class ConstantsTeleoperated {
    //--------------------------------------------------------------------------------------------------------------------//
    // TODO: Shooter
    //--------------------------------------------------------------------------------------------------------------------//
    public static int shootingSpeed = 1400; // 1550

    public static double idlerShootPose = 0.72;
    public static double idlerFreePose = 1.0;
    public static int idlerShootTime = 100; // Ms needed for idler servo to reach shoot position
    public static int idlerReturnTme = 125; // Ms needed for idler servo to reach free position


    //--------------------------------------------------------------------------------------------------------------------//
    // TODO: ShootAngle
    //--------------------------------------------------------------------------------------------------------------------//
    public static double powershotPosition = 0.66;
    public static double shootPosition = 0.562;

    public static double incrementValue = 0.03;
    public static double rightOffset = 0.205; // Offset for the right servo to maintain shooter aligned
    public static double upperLimit = 0.7;
    public static double lowerLimit = 0;


    //--------------------------------------------------------------------------------------------------------------------//
    // TODO: AutomatedShootAngle
    //--------------------------------------------------------------------------------------------------------------------//
    public static double towerX = 50f;
    public static double towerY = 14.76;
    public static final Vector2d towerCoordinates = new Vector2d(towerX, towerY); // 80; 27
    public static double fA = -0.0018; // a shootAngleServoPosition = (a * distance + b) parameter used to calculate shootAngle based on the distance between robot and tower
    public static double fB = 0.76; // b shootAngleServoPosition = (a * distance + b) parameter used to calculate shootAngle based on the distance between robot and tower

    //--------------------------------------------------------------------------------------------------------------------//
    // TODO: Movement
    //--------------------------------------------------------------------------------------------------------------------//
    public static int generalSlowFactor = 1; //slow factor in the normal movement
    public static int onPressSlowFactor = 3; //slow factor applied when right trigger is hold
    public static Vector2d cornerLeftDown = new Vector2d(61.5, 61f);
    public static Vector2d cornerLeftUp = new Vector2d(61.5, -16f);
    public static Vector2d cornerRightDown = new Vector2d(-62.76, 61f);
    public static Vector2d cornerRightUp = new Vector2d(-62.76, -16f);

    //--------------------------------------------------------------------------------------------------------------------//
    // TODO: Wobble 
    //--------------------------------------------------------------------------------------------------------------------//
    public static double grabRightBlocker = 0.28;
    public static double freeRightBlocker = 0.50;
    public static double grabLeftBlocker = 0.85;
    public static double freeLeftBlocker = 0.60;

    public static double leftGrabPosition = 0.82;
    public static double leftFreePosition = 0.00;
    public static double rightGrabPosition = 0.82;
    public static double rightFreePosition = 0.00;

    public static int correctionIncrementValue = 50;

    public static int wobbleUpPose = 0;
    public static int wobbleDownPose = 1480;
    public static int wobbleMidPose = 580;


}

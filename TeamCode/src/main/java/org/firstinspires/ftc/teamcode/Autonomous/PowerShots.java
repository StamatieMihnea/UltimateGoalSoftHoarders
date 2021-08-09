package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.GyroPID;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositonCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

public class PowerShots {

    private static MyMecanumDrive drive;
    private static LinearOpMode opMode;

    public static double shooterAnglePowerShoot = 0.56; //0.67

    public static int firstRotationRED = -3;
    public static int secondRotationRED = -4;

    public static int firstRotationBLUE = 3;
    public static int secondRotationBLUE = 4;

    public static int firstRotation = firstRotationRED;
    public static int secondRotation = secondRotationRED;

    public static int shootingSpeed = 1270; //todo maybe 1260
    public static ColorCase colorCase;
    public static Pose2d powerShotsPose = new Pose2d(42, 33.46,Math.toRadians(196)); //195
    //public static Pose2d powerShotsPose1 = new Pose2d(42, 33.46, Math.toRadians(192));
    //public static Pose2d powerShotsPose2 = new Pose2d(42, 33.46, Math.toRadians(187));

    public static void initialization(MyMecanumDrive drive, LinearOpMode opMode, ColorCase colorCase) {
        PowerShots.opMode = opMode;
        PowerShots.drive = drive;
        PowerShots.colorCase = colorCase;
        if (colorCase == ColorCase.BLUE) {
            firstRotation = firstRotationBLUE;
            secondRotation = secondRotationBLUE;
        } else{
            firstRotation = firstRotationRED;
            secondRotation = secondRotationRED;
        }
        GyroPID.setDrive(drive);
        AutoUtil.shooterAngle(shooterState.POWER_SHOTS);
        AutoUtil.startShooting();
    }

    public static void run() {

        drive.followTrajectory(powerShotsTrajectory(drive.getPoseEstimate()));
        AutoUtil.shoot(true, true);
        GyroPID.rotate(firstRotation, opMode.telemetry, opMode);
        //drive.turn(Math.toRadians(firstRotation));
        //drive.followTrajectory(powerShotsTrajectory2(drive.getPoseEstimate()));
        AutoUtil.shoot(true, true);
        GyroPID.rotate(secondRotation, opMode.telemetry, opMode);
        //drive.turn(Math.toRadians(secondRotation));
        //drive.followTrajectory(powerShotsTrajectory3(drive.getPoseEstimate()));
        //opMode.sleep(1000);
        AutoUtil.shoot(true, true);
        AutoUtil.wallPosition(wallState.VERTICAL);
    }
    public static Trajectory powerShotsTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(powerShotsPose, colorCase))
                //.splineToSplineHeading(powerShotsPose1, Math.toRadians(0))
                //.splineToSplineHeading(powerShotsPose2, Math.toRadians(0))
                .build();
    }
//    public static Trajectory powerShotsTrajectory2(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                //.lineToSplineHeading(powerShotsPose)
//                .splineToSplineHeading(powerShotsPose1, Math.toRadians(0),
//                        MyMecanumDrive.getVelocityConstraint(5, 5, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(2))//, Math.toRadians(0))
//                //.splineToSplineHeading(powerShotsPose2, Math.toRadians(0))
//                .build();
//    }
//
//    public static Trajectory powerShotsTrajectory3(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                //.lineToSplineHeading(powerShotsPose)
//                //.splineToSplineHeading(powerShotsPose1, Math.toRadians(0))
//                .splineToSplineHeading(powerShotsPose2, Math.toRadians(0),
//                        MyMecanumDrive.getVelocityConstraint(5, 5, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(2))//, Math.toRadians(0))
//                .build();
//    }

}

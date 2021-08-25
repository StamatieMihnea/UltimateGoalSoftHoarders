package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.ExteriorLine;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositionCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.grabberPosition;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

public class TrajQuantumExterior extends Trajectories {

    public static ColorCase colorCase;
    public static Pose2d diskCollectPose = new Pose2d(18, 33, Math.toRadians(178)); //10
    public static Pose2d collect4thDiskPose = new Pose2d(3, 32, Math.toRadians(178)); //10
    public static Pose2d backAfter4thDiskPose = new Pose2d(10, 32, Math.toRadians(178)); //10
    public static Pose2d shootPose = new Pose2d(42, 35, Math.toRadians(178));
    public static Pose2d releaseAPose = new Pose2d(-6, 48, Math.toRadians(250));
    public static Pose2d releaseBPose = new Pose2d(-28, 27, Math.toRadians(250));
    public static Pose2d releaseCPose = new Pose2d(-50, 46, Math.toRadians(220));
    public static Pose2d parkPose = new Pose2d(2, 56, Math.toRadians(180));

    public static void initSpecificTraj(ColorCase colorCase) {
        TrajQuantumExterior.colorCase = colorCase;
        if (colorCase == ColorCase.RED) {
            setStartPose(new Pose2d(61.5, 57.87, Math.toRadians(180)), colorCase);
        } else {
            setStartPose(new Pose2d(61.5, 57.87 - 3, Math.toRadians(180)), colorCase);
        }
    }

    public static Trajectory ShootTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(shootPose, colorCase),
                        MyMecanumDrive.getVelocityConstraint(AutoCase.intakeVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        MyMecanumDrive.getAccelerationConstraint(AutoCase.intakeAcc))
                .addTemporalMarker(0.7, () -> {
                    AutoUtil.wallPosition(wallState.VERTICAL);
                })
                .build();
    }

    public static Trajectory diskCollectTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(PositonCaseModifier.correct(diskCollectPose, colorCase),
//                        MyMecanumDrive.getVelocityConstraint(15, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
//                        MyMecanumDrive.getAccelerationConstraint(15))
                .lineToSplineHeading(PositionCaseModifier.correct(diskCollectPose, colorCase))
                //.addTemporalMarker(0, AutoUtil::startIntake)
                .build();
    }//AutoCase.intakeVel   AutoCase.intakeAcc
    //

    public static Trajectory releaseATrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(releaseAPose, colorCase))
                .addTemporalMarker(1.5, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(2.0, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory releaseBTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(releaseBPose, colorCase))
                .addTemporalMarker(1.3, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(1.8, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory releaseCTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(releaseCPose, colorCase))
                .addTemporalMarker(1.5, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(2.0, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory parkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(parkPose, colorCase))
                .addTemporalMarker(0.7, () -> {
                    Wobble.SetGrabberPosition(grabberPosition.GRAB);
                    Wobble.motorArmToPosition(wobblePosition.UP);
                })
                .build();
    }

    public static Trajectory collect4thDisk(Pose2d pose2d){
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(collect4thDiskPose, colorCase))
                .build();
    }

    public static Trajectory backAfter4thDisk(Pose2d pose2d){
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositionCaseModifier.correct(backAfter4thDiskPose, colorCase))
                .build();
    }
}

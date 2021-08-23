package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.ExteriorLine;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full.TrajFull;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositonCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

public class TrajQuantumExterior extends Trajectories {

    public static ColorCase colorCase;
    public static Pose2d diskCollectPose = new Pose2d(10, 32, Math.toRadians(180)); //10
    public static Pose2d shootPose = new Pose2d(42, 33.46, Math.toRadians(180));
    public static Pose2d releaseAPose = new Pose2d(-6, 48, Math.toRadians(250));
    public static Pose2d releaseBPose = new Pose2d(-28, 27, Math.toRadians(250));
    public static Pose2d releaseCPose = new Pose2d(-53, 39, Math.toRadians(250));

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
                .lineToSplineHeading(PositonCaseModifier.correct(shootPose, colorCase))
                .addTemporalMarker(0.7, () -> {
                    AutoUtil.wallPosition(wallState.VERTICAL);
                })
                .build();
    }

    public static Trajectory diskCollectTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(diskCollectPose, colorCase),
                        MyMecanumDrive.getVelocityConstraint(AutoCase.intakeVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        MyMecanumDrive.getAccelerationConstraint(AutoCase.intakeAcc))
                .addTemporalMarker(0, AutoUtil::startIntake)
                .build();
    }

    public static Trajectory releaseATrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(releaseAPose, colorCase))
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
                .lineToSplineHeading(PositonCaseModifier.correct(releaseBPose, colorCase))
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
                .lineToSplineHeading(PositonCaseModifier.correct(releaseCPose, colorCase))
                .addTemporalMarker(1.5, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(2.0, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }
}

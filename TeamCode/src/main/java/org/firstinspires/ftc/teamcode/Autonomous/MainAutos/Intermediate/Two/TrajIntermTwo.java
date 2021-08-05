package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.Two;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.PowerShotsAdrian;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositonCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

public class TrajIntermTwo extends Trajectories {

    public static Pose2d shootPose = new Pose2d(7,52,Math.toRadians(190));//TODO
    public static Pose2d releaseAPose = new Pose2d(-5,52,Math.toRadians(60));//TODO
    public static Pose2d releaseBPose = new Pose2d(-40,52,Math.toRadians(180));//TODO
    public static Pose2d releaseCPose = new Pose2d(-48,52,Math.toRadians(50));//TODO
    public static Pose2d parkPose = new Pose2d(2,56,Math.toRadians(180));//TODO
    public static Pose2d intakePose = new Pose2d(0,0,0);//TODO
    public static ColorCase colorCase;

    public static void initSpecificTraj(ColorCase colorCase){
        TrajIntermOne.colorCase = colorCase;
        setStartPose(new Pose2d(61.5, 33.46,Math.toRadians(180)), colorCase);//TODO
    }

    public static Trajectory intakeTrajectory(Pose2d pose2d){
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d,colorCase))
                .lineToSplineHeading(intakePose)
                .build();
    }

    public static Trajectory releaseATrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d,colorCase))
                .lineToSplineHeading(releaseAPose)
                .addTemporalMarker(0.7, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(1.5, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory releaseBTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d,colorCase))
                .lineToSplineHeading(releaseBPose)
                .addTemporalMarker(0.7, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(1.5, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

    public static Trajectory releaseCTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d,colorCase))
                .lineToSplineHeading(releaseCPose)
                .addTemporalMarker(1.5, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(1.9, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }

}

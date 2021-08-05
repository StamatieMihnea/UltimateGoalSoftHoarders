package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositonCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.grabberPosition;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

public class TrajFull extends Trajectories {

    public static ColorCase colorCase;
    public static Pose2d diskCollectPose = new Pose2d(0, 0, 0);//TODO
    //public static Pose2d shootPose = new Pose2d(0,0,Math.toRadians(180));
    public static Pose2d releaseAPose = new Pose2d(-5,52,Math.toRadians(60));//TODO
    public static Pose2d releaseBPose = new Pose2d(-40,52,Math.toRadians(180));//TODO
    public static Pose2d releaseCPose = new Pose2d(-48,52,Math.toRadians(50));//TODO
    public static Pose2d backAPose = new Pose2d(0,0,Math.toRadians(0));//TODO
    public static Pose2d backBPose = new Pose2d(0,0,Math.toRadians(0));//TODO
    public static Pose2d backCPose = new Pose2d(0,0,Math.toRadians(0));//TODO
    public static Pose2d secWobbleAPose = new Pose2d(0,0,Math.toRadians(0));//TODO
    public static Pose2d secWobbleBPose = new Pose2d(0,0,Math.toRadians(0));//TODO
    public static Pose2d secWobbleCPose = new Pose2d(0,0,Math.toRadians(0));//TODO
    public static Pose2d parkPose = new Pose2d(0,0,Math.toRadians(0));//TODO


    public static void initSpecificTraj(ColorCase colorCase) {
        TrajIntermOne.colorCase = colorCase;
        setStartPose(new Pose2d(0, 0, Math.toRadians(180)), colorCase);//TODO
    }

    public static Trajectory diskCollectTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(diskCollectPose)
                .addTemporalMarker(0.1, AutoUtil::startIntake) //TODO verify this
                .build();
    }

    //    public static Trajectory shootPoseTrajectory(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(shootPose)
//                .build();
//    }

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

    public static Trajectory returnBackA(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
                .lineToSplineHeading(backAPose)
                .build();
    }

    public static Trajectory returnBackB(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
                .lineToSplineHeading(backBPose)
                .build();
    }

    public static Trajectory returnBackC(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
                .lineToSplineHeading(backCPose)
                .build();
    }

    public static Trajectory secondWobbleTrajA(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
                .lineToSplineHeading(secWobbleAPose)
                .build();
    }

    public static Trajectory secondWobbleTrajB(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
                .lineToSplineHeading(secWobbleBPose)
                .build();
    }

    public static Trajectory secondWobbleTrajC(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
                .lineToSplineHeading(secWobbleCPose)
                .build();
    }

    public static Trajectory parkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d,colorCase))
                .lineToSplineHeading(parkPose)
                .addTemporalMarker(0.7, () ->{
                    Wobble.SetGrabberPosition(grabberPosition.GRAB);
                    Wobble.motorArmToPosition(wobblePosition.UP);
                })
                .build();
    }

//    public static Trajectory intakeTrajectory(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(intakePose)
//                .build();
//    }
}

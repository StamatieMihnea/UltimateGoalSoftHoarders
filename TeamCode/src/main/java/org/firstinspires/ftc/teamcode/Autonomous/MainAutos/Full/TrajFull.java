package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.GeneralAutoParameters;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositonCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.grabberPosition;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

public class TrajFull extends Trajectories {

    public static ColorCase colorCase;
    public static Pose2d diskCollectPose = new Pose2d(14, 33.46, Math.toRadians(180));
    //public static Pose2d shootPose = new Pose2d(0,0,Math.toRadians(180));
    public static Pose2d releaseAPose = new Pose2d(-6, 48, Math.toRadians(250));
    public static Pose2d releaseBPose = new Pose2d(-28, 27, Math.toRadians(250));
    public static Pose2d releaseCPose = new Pose2d(-53, 39, Math.toRadians(250));
    public static Pose2d backPose = new Pose2d(41, 20, Math.toRadians(180));
//    public static Pose2d backBPose = new Pose2d(41, 32, Math.toRadians(180));
//    public static Pose2d backCPose = new Pose2d(41, 32, Math.toRadians(180));
    public static Pose2d collectSecondWobblePose = new Pose2d(41, 35, Math.toRadians(180));
    public static Pose2d secWobbleAPose = new Pose2d(-4, 39, Math.toRadians(250));
    public static Pose2d secWobbleBPose = new Pose2d(-26, 19, Math.toRadians(250));
    public static Pose2d secWobbleCPose = new Pose2d(-51, 48, Math.toRadians(250));
    public static Pose2d parkPose = new Pose2d(-1, 30, Math.toRadians(180));

    public static void initSpecificTraj(ColorCase colorCase) {
        TrajFull.colorCase = colorCase;
        setStartPose(new Pose2d(61.5, 33.46, Math.toRadians(180)), colorCase);
    }

    public static Trajectory diskCollectTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(diskCollectPose,
                        MyMecanumDrive.getVelocityConstraint(AutoCase.intakeVel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                        MyMecanumDrive.getAccelerationConstraint(AutoCase.intakeAcc))
                .addTemporalMarker(0.1, AutoUtil::startIntake)
                .build();
    }

    //    public static Trajectory shootPoseTrajectory(Pose2d pose2d) {
//        return drive.trajectoryBuilder(pose2d)
//                .lineToSplineHeading(shootPose)
//                .build();
//    }

    public static Trajectory releaseATrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
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
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
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
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
                .lineToSplineHeading(releaseCPose)
                .addTemporalMarker(1.5, () -> {
                    Wobble.motorArmToPosition(wobblePosition.DOWN);
                })
                .addTemporalMarker(1.9, () -> {
                    Wobble.wobbleRelease();
                })
                .build();
    }
    public static Trajectory returnBack(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
                .lineToSplineHeading(backPose)
                .splineToLinearHeading(collectSecondWobblePose, Math.toRadians(0)) // spline to
                //TODO Domi - fix the issue with A trajectory, arm hitting the 2nd wobble on return - solution, spline back with Spline heading
                //.splineToLinearHeading(backPose, Math.toRadians(10)) // spline to
                //.splineToLinearHeading(collectSecondWobblePose, Math.toRadians(0))
                //Error: User code threw an uncaught exception: PathContinuityViolationException
                .build();
    }
//    public static Trajectory returnBackA(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(backPose)
//                .splineToLinearHeading(collectSecondWobblePose, Math.toRadians(0))
//                .build();
//    }
//
//    public static Trajectory returnBackB(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(backPose)
//                .splineToLinearHeading(collectSecondWobblePose, Math.toRadians(0))
//                .build();
//    }
//
//    public static Trajectory returnBackC(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(backPose)
//                .splineToLinearHeading(collectSecondWobblePose, Math.toRadians(0))
//                .build();
//    }

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
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
                .lineToSplineHeading(parkPose)
                .addTemporalMarker(0.7, () -> {
                    Wobble.SetGrabberPosition(grabberPosition.GRAB);
                    Wobble.motorArmToPosition(wobblePosition.UP);
                })
                .build();
    }

//    public static Trajectory intakeTrajectory(Pose2d pose2d) {
//        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d, colorCase))
//                .lineToSplineHeading(GeneralAutoParameters.intakePose)
//                .build();
//    }


}

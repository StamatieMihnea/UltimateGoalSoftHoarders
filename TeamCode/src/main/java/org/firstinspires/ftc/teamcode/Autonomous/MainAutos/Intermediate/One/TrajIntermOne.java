package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One;

import static org.firstinspires.ftc.teamcode.Autonomous.AutoCase.wobbleMidPosition;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.C;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.NormalizeImuAngle;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositonCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.Utils.Logics.CmToInch;

public class TrajIntermOne extends Trajectories {

    //inch and rad
    public static Pose2d shootPose = new Pose2d(0,0,0);//TODO
    public static Pose2d releaseAPose = new Pose2d(0,0,0);//TODO
    public static Pose2d releaseBPose = new Pose2d(0,0,0);//TODO
    public static Pose2d releaseCPose = new Pose2d(0,0,0);//TODO
    public static Pose2d parkPose = new Pose2d(0,0,0);//TODO
    public static ColorCase colorCase;

    public static void initSpecificTraj(ColorCase colorCase){
        TrajIntermOne.colorCase = colorCase;
        setStartPose(new Pose2d(0,0,0), colorCase);//TODO
    }

    public static Trajectory shootTrajectory(Pose2d pose2d) {

        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d,colorCase))
                .lineToSplineHeading(shootPose)
                .build();
    }

    public static Trajectory releaseATrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d,colorCase))
                .lineToSplineHeading(releaseAPose)
                .build();
    }

    public static Trajectory releaseBTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d,colorCase))
                .lineToSplineHeading(releaseBPose)
                .build();
    }

    public static Trajectory releaseCTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d,colorCase))
                .lineToSplineHeading(releaseCPose)
                .build();
    }

    public static Trajectory parkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(PositonCaseModifier.correct(pose2d,colorCase))
                .lineToSplineHeading(parkPose)
                .build();
    }

}

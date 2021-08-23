package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Worst;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositonCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;

public class TrajWorst extends Trajectories {
    //inch and rad
    public static Pose2d shootPose = new Pose2d(7, 52, Math.toRadians(190));
    public static Pose2d parkPose = new Pose2d(2, 56, Math.toRadians(180));
    public static ColorCase colorCase;

    public static void initSpecificTraj(ColorCase colorCase){
        TrajWorst.colorCase = colorCase;
        setStartPose(new Pose2d(61.5, 57.87,Math.toRadians(180)), colorCase);
    }

    public static Trajectory shootTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(shootPose, colorCase))
                .addTemporalMarker(0.5, () -> {
                    AutoUtil.startShooting();
                })
                .build();
    }

    public static Trajectory ParkTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(PositonCaseModifier.correct(parkPose, colorCase))
                .build();
    }
}

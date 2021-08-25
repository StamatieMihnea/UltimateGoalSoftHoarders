package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.InteriorLine;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full.TrajFull;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;

public class TrajQuantumInterior extends Trajectories {

    public static ColorCase colorCase;
    public static Pose2d diskCollectPose = new Pose2d(10, 32, Math.toRadians(180)); //10
    public static Pose2d shootPose = new Pose2d(42, 33.46, Math.toRadians(180));
    public static Pose2d releaseAPose = new Pose2d(-6, 48, Math.toRadians(250));
    public static Pose2d releaseBPose = new Pose2d(-28, 27, Math.toRadians(250));
    public static Pose2d releaseCPose = new Pose2d(-53, 39, Math.toRadians(250));
    public static Pose2d slideReturnPS = new Pose2d(-53, 39, Math.toRadians(250));
    public static Pose2d backReturnPS = new Pose2d(-53, 39, Math.toRadians(250));


    public static void initSpecificTraj(ColorCase colorCase) {
        TrajFull.colorCase = colorCase;
        if (colorCase==ColorCase.RED) {
            setStartPose(new Pose2d(61.5, 33.46, Math.toRadians(180)), colorCase);
        }
        else {
            setStartPose(new Pose2d(61.5, 33.46 - 3, Math.toRadians(180)), colorCase);
        }
    }





    public static Trajectory returnPowerShots(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(backReturnPS)
                .build();
    }
}

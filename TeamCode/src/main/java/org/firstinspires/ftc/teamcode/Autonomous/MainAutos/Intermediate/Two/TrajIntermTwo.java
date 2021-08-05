package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.Two;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositonCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;

public class TrajIntermTwo extends Trajectories {

    public static Pose2d shootPose = new Pose2d(7,52,Math.toRadians(190));//TODO
    public static Pose2d releaseAPose = new Pose2d(-5,52,Math.toRadians(60));//TODO
    public static Pose2d releaseBPose = new Pose2d(-40,52,Math.toRadians(180));//TODO
    public static Pose2d releaseCPose = new Pose2d(-48,52,Math.toRadians(50));//TODO
    public static Pose2d parkPose = new Pose2d(2,56,Math.toRadians(180));//TODO
    public static ColorCase colorCase;

    public static void initSpecificTraj(ColorCase colorCase){
        TrajIntermOne.colorCase = colorCase;
        setStartPose(new Pose2d(61.5, 33.46,Math.toRadians(180)), colorCase);//TODO
    }

}

package org.firstinspires.ftc.teamcode.RoadRunner.Functionalities;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class PoseStorage {
    public static Pose2d imuAndWobble = new Pose2d(180, 0, 0);
    public static Pose2d currentPose = new Pose2d(61.5, 14.6, Math.toRadians(180));
    //public static Pose2d currentPose = new Pose2d(61.6, 33.136, Math.toRadians(180)); --> New but not working starting pose
}

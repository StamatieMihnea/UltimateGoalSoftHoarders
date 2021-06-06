package org.firstinspires.ftc.teamcode.RoadRunner.Functionalities;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;

public class GoToPoint {

    //unused
    public static void spline(boolean button, double x, double y, double heading, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            Pose2d poseEstimate = drive.getPoseEstimate();
            Trajectory trajectory = drive.trajectoryBuilder(poseEstimate)
                    .splineToSplineHeading(new Pose2d(x, y, heading), 0)
                    .build();

            drive.followTrajectory(trajectory);
        }
    }

    public static void strafe(boolean button, double x, double y, double heading, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            Pose2d poseEstimate = drive.getPoseEstimate();
            Trajectory trajectory = drive.trajectoryBuilder(poseEstimate)
                    .lineToSplineHeading(new Pose2d(x, y, heading))
                    .build();

            drive.followTrajectory(trajectory);
        }
    }

}

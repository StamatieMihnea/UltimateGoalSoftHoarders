package org.firstinspires.ftc.teamcode.TeleOperated;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.NormalizeImuAngle;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

public class Movement {

    private static MyMecanumDrive drive;
    private static Telemetry telemetry;
    public static int slowFactor = 1;

    public static void setDrive(MyMecanumDrive drive) {
        Movement.drive = drive;
    }

    public static void setTelemetry(Telemetry telemetry) {
        Movement.telemetry = telemetry;
    }

    public static void driving(Gamepad gamepad) {
        drive.setWeightedDrivePower(
                new Pose2d(
                        -gamepad.right_stick_y / slowFactor,
                        -gamepad.right_stick_x / slowFactor,
                        -gamepad.left_stick_x / slowFactor
                )
        );
        drive.update();
    }

    public static void localize(boolean update) {
        NormalizeImuAngle.setDrive(drive);
        Pose2d poseEstimate = drive.getPoseEstimate();
        telemetry.addData("x", poseEstimate.getX());
        telemetry.addData("y", poseEstimate.getY());
        telemetry.addData("heading", poseEstimate.getHeading());
        telemetry.addData("heading in degrees", Math.toDegrees(drive.getPoseEstimate().getHeading()));
        if (update) {
            telemetry.update();
        }
    }

    public static void slowMovement(Gamepad gamepad, int factor){
        if(gamepad.right_trigger>0.01){
            slowFactor = factor;
        }else{
            slowFactor = 1;
        }
    }

}
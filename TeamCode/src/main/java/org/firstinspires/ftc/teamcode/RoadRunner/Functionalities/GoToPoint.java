package org.firstinspires.ftc.teamcode.RoadRunner.Functionalities;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.GyroPID;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.NormalizeImuAngle;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Main.Teleoperated;
import org.firstinspires.ftc.teamcode.Main.driveCase;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Wall;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;

public class GoToPoint {
    private static LinearOpMode opMode;
    private static final Vector2d towerCoordinates  = new Vector2d(-65,20);

    public static void init(LinearOpMode opMode, MyMecanumDrive drive) {
        GyroPID.setDrive(drive);
        GoToPoint.opMode = opMode;
    }

    //unused
    public static void spline(boolean button, double x, double y, double heading, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            Pose2d poseEstimate = drive.getPoseEstimate();
            Trajectory trajectory = drive.trajectoryBuilder(poseEstimate)
                    .splineToSplineHeading(new Pose2d(x, y, heading), 0,
                            MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                            MyMecanumDrive.getAccelerationConstraint(35))
                    .addDisplacementMarker(() -> {
                        Teleoperated.currentCase = driveCase.DRIVE;
                    })
                    .build();
            Teleoperated.currentCase = driveCase.AUTO;
            drive.followTrajectoryAsync(trajectory);
        }
    }

    public static void spline(boolean button, Pose2d pose2d, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            Pose2d poseEstimate = drive.getPoseEstimate();
            Trajectory trajectory = drive.trajectoryBuilder(poseEstimate)
                    .splineToSplineHeading(pose2d, 0,
                            MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                            MyMecanumDrive.getAccelerationConstraint(35))
                    .addDisplacementMarker(() -> {
                        Teleoperated.currentCase = driveCase.DRIVE;
                    })
                    .build();
            Teleoperated.currentCase = driveCase.AUTO;
            drive.followTrajectoryAsync(trajectory);
        }
    }

    public static void strafe(boolean button, double x, double y, double heading, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            Pose2d poseEstimate = drive.getPoseEstimate();
            Trajectory trajectory = drive.trajectoryBuilder(poseEstimate)
                    .lineToSplineHeading(new Pose2d(x, y, heading),
                            MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                            MyMecanumDrive.getAccelerationConstraint(35))
                    .addDisplacementMarker(() -> {
                        Teleoperated.currentCase = driveCase.DRIVE;
                    })
                    .build();
            Teleoperated.currentCase = driveCase.AUTO;
            drive.followTrajectoryAsync(trajectory);
        }
    }

    public static void strafe(boolean button, double x, double y, double heading, MyMecanumDrive drive, double accel, double vel, boolean wobble) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            if (wobble) {
                Wobble.motorArmToPosition(true, wobblePosition.UP);
                Wall.wallClose(true);
            }
            Pose2d poseEstimate = drive.getPoseEstimate();
            Trajectory trajectory = drive.trajectoryBuilder(poseEstimate)
                    .lineToSplineHeading(new Pose2d(x, y, heading),
                            MyMecanumDrive.getVelocityConstraint(vel, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                            MyMecanumDrive.getAccelerationConstraint(accel))
                    .addDisplacementMarker(() -> {
                        Teleoperated.currentCase = driveCase.DRIVE;
                    })
                    .build();
            Teleoperated.currentCase = driveCase.AUTO;
            drive.followTrajectoryAsync(trajectory);
        }
    }

    public static void strafe(boolean button, Pose2d pose2d, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            Pose2d poseEstimate = drive.getPoseEstimate();
            Trajectory trajectory = drive.trajectoryBuilder(poseEstimate)
                    .lineToSplineHeading(pose2d,
                            MyMecanumDrive.getVelocityConstraint(60, DriveConstants.MAX_ANG_VEL, DriveConstants.TRACK_WIDTH),
                            MyMecanumDrive.getAccelerationConstraint(35))
                    .build();
            Teleoperated.currentCase = driveCase.AUTO;
            drive.followTrajectoryAsync(trajectory);
        }
    }

    public static void POWERSHOTS(boolean button, MyMecanumDrive drive) {
        OneTap oneTap = new OneTap();
        if (oneTap.onPress(button)) {
            ChangeShootingAngle.changeAngle(true, 0.52);
            ChangeShootingAngle.update();
            opMode.sleep(500);
            GyroPID.rotate(14, opMode.telemetry, opMode);
            AutoUtil.shoot(true, true);
            GyroPID.rotate(7, opMode.telemetry, opMode);
            AutoUtil.shoot(true, true);
            GyroPID.rotate(4, opMode.telemetry, opMode);
            AutoUtil.shoot(true, true);
        }
    }

    public static void HighGoalAutoOrientation (boolean button, MyMecanumDrive drive){
            OneTap oneTap = new OneTap();
            if(oneTap.onPress(button)){
                Pose2d robotPosition = drive.getPoseEstimate();
                double absolutRotationNeeded = Math.atan2(robotPosition.getX()-towerCoordinates.getX(),robotPosition.getY()-towerCoordinates.getY());
                double neededForStraightening = PoseStorage.currentPose.getHeading() - NormalizeImuAngle.convert(Hardware.imu.getAngularOrientation().firstAngle);
                GyroPID.rotate(absolutRotationNeeded+neededForStraightening, opMode.telemetry, opMode);
            }
    }
}

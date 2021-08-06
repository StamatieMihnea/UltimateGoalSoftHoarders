package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.GyroPID;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

@Config
public class PowerShots {

    private static MyMecanumDrive drive;
    private static LinearOpMode opMode;

    public static double shooterAnglePowerShoot = 0.62; //0.67
    public static int firstRotation = -3;
    public static int secondRotation = -4;
    public static int shootingSpeed = 1270;

    public static Pose2d powerShotsPose = new Pose2d(42, 33.46,Math.toRadians(196)); //195

    public static void initialization(MyMecanumDrive drive, LinearOpMode opMode) {
        PowerShots.opMode = opMode;
        PowerShots.drive = drive;
        GyroPID.setDrive(drive);
        AutoUtil.shooterAngle(shooterState.POWER_SHOTS);
        AutoUtil.wallPosition(wallState.VERTICAL);
        AutoUtil.startShooting();
    }

    public static void run() {

        drive.followTrajectory(powerShotsTrajectory(drive.getPoseEstimate()));
        AutoUtil.shoot(true, true);
        GyroPID.rotate(firstRotation, opMode.telemetry, opMode);
        AutoUtil.shoot(true, true);
        GyroPID.rotate(secondRotation, opMode.telemetry, opMode);
        AutoUtil.shoot(true, true);

    }
    public static Trajectory powerShotsTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(powerShotsPose)
                .build();
    }

}

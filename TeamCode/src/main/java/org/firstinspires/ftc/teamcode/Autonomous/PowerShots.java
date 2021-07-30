package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.GyroPID;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

@Config
public class PowerShots {

    public static double shooterAnglePowerShoot = 0.63; //0.67
    public static int shootingSpeed = 1150;

    public static double firstPowerShootPositionY = 37;
    public static double firstPowerShootPositionX = 5;

    public static double distanceBetweenPowerShoots = 20; //20.5
    public static double offsetLastPowerShot = 0.5;

    private static MyMecanumDrive drive;
    private static LinearOpMode opMode;

    public static void initialization(MyMecanumDrive drive, LinearOpMode opMode) {
        PowerShots.opMode = opMode;
        PowerShots.drive = drive;
        GyroPID.setDrive(drive);
        AutoUtil.shooterAngle(shooterState.POWER_SHOTS);
        AutoUtil.startShooting();
    }

    private static void PowerShootDown() {
//        AutoUtil.waitForShooting(drive);
        AutoUtil.shoot(true, true);
    }

    private static void FollowTrajectoryAndShoot(Trajectory trajectory, MyMecanumDrive drive) {

        drive.followTrajectory(trajectory);
        GyroPID.rotate(-2, opMode.telemetry, opMode);
        PowerShootDown();
        GyroPID.rotate(5, opMode.telemetry, opMode);
        PowerShootDown();
        GyroPID.rotate(5, opMode.telemetry, opMode);
        GyroPID.rotate(2, opMode.telemetry, opMode);
        PowerShootDown();
    }

    public static void run() {

       // FollowTrajectoryAndShoot(Trajectories.rightPowerShot(drive.getPoseEstimate()), drive);

       // FollowTrajectoryAndShoot(Trajectories.middlePowerShot(drive.getPoseEstimate()), drive);

        //FollowTrajectoryAndShoot(Trajectories.leftPowerShot(drive.getPoseEstimate()), drive);

        AutoUtil.stopShooting();
        AutoUtil.shooterAngle(shooterState.INTAKE);
    }
}

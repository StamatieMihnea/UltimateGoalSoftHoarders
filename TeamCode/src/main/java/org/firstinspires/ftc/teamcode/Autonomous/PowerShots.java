package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.Utils.Logics.CmToInch;

@Config
public class PowerShots {

    public static double shooterAnglePowerShoot = 0.65; //0.67
    public static int shootingSpeed = 1150;

    public static double firstPowerShootPositionY = 48;
    public static double firstPowerShootPositionX = 5;

    public static double distanceBetweenPowerShoots = 20.5; //20.5
    public static double offsetLastPowerShot = 0.5;

    private static MyMecanumDrive drive;

    public static void initialization(MyMecanumDrive drive) {
        PowerShots.drive = drive;
        AutoUtil.shooterAngle(shooterState.POWER_SHOTS);
        AutoUtil.startShooting();
    }

    private static void PowerShootDown(boolean lastPowerShot) {
        AutoUtil.waitForShooting(drive);
        AutoUtil.shoot(!lastPowerShot, true);
    }

    private static void FollowTrajectoryAndShoot(Trajectory trajectory, MyMecanumDrive drive,boolean lastPowerShot) {

        drive.followTrajectory(trajectory);
        PowerShootDown(lastPowerShot);
    }

    public static void run() {

        FollowTrajectoryAndShoot(Trajectories.rightPowerShot(drive.getPoseEstimate()), drive, false);

        FollowTrajectoryAndShoot(Trajectories.middlePowerShot(drive.getPoseEstimate()), drive, false);

        FollowTrajectoryAndShoot(Trajectories.leftPowerShot(drive.getPoseEstimate()), drive, true);

        AutoUtil.stopShooting();
        AutoUtil.shooterAngle(shooterState.SHOOT);
    }
}

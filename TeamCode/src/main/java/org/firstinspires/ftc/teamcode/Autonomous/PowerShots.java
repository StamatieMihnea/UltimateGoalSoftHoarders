package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

@Config
public class PowerShots {

    public static double shooterAnglePowerShoot = 0.63; //0.67
    public static int shootingSpeed = 1150;

    public static double firstPowerShootPositionY = 42;
    public static double firstPowerShootPositionX = 5;

    public static double distanceBetweenPowerShoots = 20; //20.5
    public static double offsetLastPowerShot = 0.5;

    private static MyMecanumDrive drive;

    public static void initialization(MyMecanumDrive drive) {
        PowerShots.drive = drive;
        AutoUtil.shooterAngle(shooterState.POWER_SHOTS);
        AutoUtil.startShooting();
    }

    private static void PowerShootDown() {
        AutoUtil.waitForShooting(drive);
        AutoUtil.shoot(false, true);
    }

    private static void FollowTrajectoryAndShoot(Trajectory trajectory, MyMecanumDrive drive) {

        drive.followTrajectory(trajectory);
        PowerShootDown();
    }

    public static void run() {

        FollowTrajectoryAndShoot(Trajectories.rightPowerShot(drive.getPoseEstimate()), drive);

        FollowTrajectoryAndShoot(Trajectories.middlePowerShot(drive.getPoseEstimate()), drive);

        FollowTrajectoryAndShoot(Trajectories.leftPowerShot(drive.getPoseEstimate()), drive);

        AutoUtil.stopShooting();
        AutoUtil.shooterAngle(shooterState.INTAKE);
    }
}

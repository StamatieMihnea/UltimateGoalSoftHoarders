package org.firstinspires.ftc.teamcode.Autonomous;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.Two.TrajIntermTwo;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.GyroPID;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.PositonCaseModifier;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

public class PowerShotsTRY {

    private static MyMecanumDrive drive;
    private static LinearOpMode opMode;

    public static Pose2d powerShotsPose = new Pose2d(55, 33.46,Math.toRadians(180));

    //public static ColorCase colorCase = TrajIntermTwo.colorCase;

    public static void initialization(MyMecanumDrive drive, LinearOpMode opMode) {
        PowerShotsTRY.opMode = opMode;
        PowerShotsTRY.drive = drive;
        GyroPID.setDrive(drive);
        AutoUtil.shooterAngle(shooterState.POWER_SHOTS);
        AutoUtil.startShooting();
    }


    public static void run() {
        drive.followTrajectory(powerShotsTrajectory(drive.getPoseEstimate()));

    }
    public static Trajectory powerShotsTrajectory(Pose2d pose2d) {
        return drive.trajectoryBuilder(pose2d)
                .lineToSplineHeading(powerShotsPose)
                .build();
    }

}

package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

public class Initializations {

    public static MyMecanumDrive drive;
    public static Pose2d startPose = new Pose2d(61.5, 14.6, Math.toRadians(180));

    private static void MovementInit(LinearOpMode opMode){
        drive = new MyMecanumDrive(opMode.hardwareMap);
        drive.setPoseEstimate(startPose);
    }

    private static void UtilsInit(LinearOpMode opMode){
        AutoUtil.setOpMode(opMode);
        AutoUtil.initialization();
        AutoUtil.wallPosition(wallState.VERTICAL);
    }

    private static void HardwareInit(LinearOpMode  opMode){
        Hardware.init(opMode.hardwareMap, opMode.telemetry, new DiskAmountDetection.UltimateGoalPipeline(opMode.telemetry, true));
    }

    public static void AutoInit(LinearOpMode opMode){
        HardwareInit(opMode);
        MovementInit(opMode);
        UtilsInit(opMode);
        Trajectories.setDrive(drive);
        Trajectories.setStartPose(startPose);
    }

}

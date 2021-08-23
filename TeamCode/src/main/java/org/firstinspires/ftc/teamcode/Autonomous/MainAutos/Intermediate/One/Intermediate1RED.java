package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.NormalizeAngleGyro;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;

@Autonomous(name = "Intermediate1RED")
public class Intermediate1RED extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        TrajIntermOne.initSpecificTraj(ColorCase.RED);
        Initializations.AutoInit(this, armCase.LEFT, TrajIntermOne.getStartPose());
        waitForStart();
        DiskAmountDetection.stopDetection();
        AutoCase.setAutoScene(AutoScenes.INTERMEDIATE1);


        DetectionCase.autoCase.Shoot();
        DetectionCase.autoCase.releaseFirstWobble();
        AutoCase.Park();
        Pose2d finalPose = Initializations.drive.getPoseEstimate();
        PoseStorage.currentPose = new Pose2d(finalPose.getX(),finalPose.getY(), NormalizeAngleGyro.Normalize(Hardware.imu));
        PoseStorage.colorCase = new Pose2d(1);
    }
}

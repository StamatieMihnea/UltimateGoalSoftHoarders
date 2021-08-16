package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Worst;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;

@Autonomous(name = "WorstBLUE")
public class WorstBLUE extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        TrajWorst.initSpecificTraj(ColorCase.BLUE);
        Initializations.AutoInit(this, armCase.RIGHT, TrajWorst.getStartPose());
        waitForStart();
        DiskAmountDetection.stopDetection();
        DetectionCase.setAutoCase("C", this);
        AutoCase.setAutoScene(AutoScenes.WORST);


        DetectionCase.autoCase.Shoot();
        AutoCase.Park();
    }
}

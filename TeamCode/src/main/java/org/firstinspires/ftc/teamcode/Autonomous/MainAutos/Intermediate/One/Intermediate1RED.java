package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
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

        AutoCase.Shoot();
        DetectionCase.autoCase.releaseFirstWobble();
        AutoCase.Park();

    }
}

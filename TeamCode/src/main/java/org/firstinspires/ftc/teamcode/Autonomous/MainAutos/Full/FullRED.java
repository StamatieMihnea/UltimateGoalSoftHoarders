package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.PowerShots;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;

@Autonomous(name = "FullRED")
public class FullRED extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        TrajIntermOne.initSpecificTraj(ColorCase.RED);
        Initializations.AutoInit(this, armCase.LEFT, TrajIntermOne.getStartPose());
        waitForStart();

        DiskAmountDetection.stopDetection();
        AutoCase.setAutoScene(AutoScenes.FULL);

        PowerShots.run();
        AutoCase.Intake();
        AutoCase.Shoot();
        DetectionCase.autoCase.releaseFirstWobble();
        DetectionCase.autoCase.returnBack();
        DetectionCase.autoCase.releaseSecondWobble();
        AutoCase.Park();



    }

}

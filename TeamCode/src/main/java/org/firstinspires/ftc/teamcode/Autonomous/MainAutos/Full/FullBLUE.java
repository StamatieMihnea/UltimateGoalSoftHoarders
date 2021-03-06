package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.PowerShots;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;

@Autonomous(name = "FullBLUE")
public class FullBLUE extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        TrajFull.initSpecificTraj(ColorCase.BLUE);
        Initializations.AutoInit(this, armCase.LEFT, TrajFull.getStartPose());
        waitForStart();

        DiskAmountDetection.stopDetection();
        AutoCase.setAutoScene(AutoScenes.FULL);
        DetectionCase.setAutoCase("A", this);

        DetectionCase.autoCase.Shoot();
        if (DetectionCase.diskAmount != 0) {
            AutoCase.Intake();
        }

        PowerShots.initialization(Trajectories.getDrive(), this, ColorCase.BLUE);
        PowerShots.run();

        DetectionCase.autoCase.releaseFirstWobble();
        AutoCase.returnBack();
        DetectionCase.autoCase.releaseSecondWobble();
        AutoCase.Park();

    }
}

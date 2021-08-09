package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.Two;

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

@Autonomous(name = "Intermediate2BLUE")
public class Intermediate2BLUE extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException{
        TrajIntermTwo.initSpecificTraj(ColorCase.BLUE);
        Initializations.AutoInit(this, armCase.RIGHT, TrajIntermTwo.getStartPose());
        AutoCase.setAutoScene(AutoScenes.INTERMEDIATE2);
        waitForStart();

        PowerShots.initialization(Trajectories.getDrive(), this, ColorCase.BLUE);
        DiskAmountDetection.stopDetection();
        DetectionCase.setAutoCase("C", this);

        PowerShots.run();
        if (DetectionCase.diskAmount != 0) { //stamatescu
            AutoCase.Intake();
            AutoCase.Shoot();
        }

        DetectionCase.autoCase.releaseFirstWobble();
        AutoCase.Park();

    }
}

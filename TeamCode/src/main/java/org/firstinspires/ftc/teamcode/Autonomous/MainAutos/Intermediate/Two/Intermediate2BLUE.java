package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.Two;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.apache.commons.math3.analysis.function.Pow;
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
    public void runOpMode() throws InterruptedException {

        TrajIntermTwo.initSpecificTraj(ColorCase.BLUE);
        Initializations.AutoInit(this, armCase.RIGHT, TrajIntermTwo.getStartPose());
        waitForStart();

        DiskAmountDetection.stopDetection();
        AutoCase.setAutoScene(AutoScenes.INTERMEDIATE2);
        DetectionCase.setAutoCase("A", this);

        DetectionCase.autoCase.Shoot();
        if (DetectionCase.diskAmount != 0) { //stamatescu
            AutoCase.Intake();
        }

        PowerShots.initialization(Trajectories.getDrive(), this, ColorCase.BLUE);
        PowerShots.run();

        DetectionCase.autoCase.releaseFirstWobble();
        AutoCase.Park();
    }
}

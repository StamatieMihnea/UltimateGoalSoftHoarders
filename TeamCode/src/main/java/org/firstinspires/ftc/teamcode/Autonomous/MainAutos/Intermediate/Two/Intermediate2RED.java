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

@Autonomous(name = "Intermediate2RED")
public class Intermediate2RED extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        TrajIntermTwo.initSpecificTraj(ColorCase.RED);
        Initializations.AutoInit(this, armCase.LEFT, TrajIntermTwo.getStartPose());
        AutoCase.setAutoScene(AutoScenes.INTERMEDIATE2);
        waitForStart();
        PowerShots.initialization(Trajectories.getDrive(),this);
        DiskAmountDetection.stopDetection();
        DetectionCase.setAutoCase("C",this);

        PowerShots.run();
        AutoCase.Intake();
        AutoCase.Shoot();
        DetectionCase.autoCase.releaseFirstWobble();
        AutoCase.Park();
    }
}

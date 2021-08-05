package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.Two;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.PowerShotsAdrian;
import org.firstinspires.ftc.teamcode.Autonomous.PowerShotsTRY;
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
        PowerShotsAdrian.initialization(Trajectories.getDrive(),this);

        DiskAmountDetection.stopDetection();
        PowerShotsTRY.run();
    }
}

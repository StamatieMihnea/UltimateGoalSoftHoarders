package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.PowerShots;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;

@Autonomous(name = "FullRED")
public class FullRED extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        TrajFull.initSpecificTraj(ColorCase.RED);
        Initializations.AutoInit(this, armCase.RIGHT, TrajFull.getStartPose());
        waitForStart();
        //PowerShots.initialization(Trajectories.getDrive(), this, ColorCase.RED);

        DiskAmountDetection.stopDetection();
        AutoCase.setAutoScene(AutoScenes.FULL);
        DetectionCase.setAutoCase("C", this);

        DetectionCase.autoCase.Shoot();
        if (DetectionCase.diskAmount != 0) {
            AutoCase.Intake();
        }
        PowerShots.initialization(Trajectories.getDrive(), this, ColorCase.RED);
        PowerShots.run();

        DetectionCase.autoCase.releaseFirstWobble();
        AutoCase.returnBack();
        DetectionCase.autoCase.releaseSecondWobble();
        AutoCase.Park();

        //        PowerShots.run();
//        if (DetectionCase.diskAmount != 0) { //stamatescu
//            AutoCase.Intake();
//
//            DetectionCase.autoCase.Shoot();
//        }
//        DetectionCase.autoCase.releaseFirstWobble();
//        AutoCase.returnBack();
//        DetectionCase.autoCase.releaseSecondWobble();
//        AutoCase.Park();
    }

}

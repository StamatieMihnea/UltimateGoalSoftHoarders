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
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;
import org.firstinspires.ftc.teamcode.TeleOperated.grabberPosition;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

@Autonomous(name = "FullBLUE")
public class FullBLUE extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        TrajFull.initSpecificTraj(ColorCase.BLUE);
        Initializations.AutoInit(this, armCase.LEFT, TrajFull.getStartPose());
        waitForStart();
        PowerShots.initialization(Trajectories.getDrive(), this, ColorCase.BLUE);

        DiskAmountDetection.stopDetection();
        AutoCase.setAutoScene(AutoScenes.FULL);
        DetectionCase.setAutoCase("C", this);
        PowerShots.run();
        if (DetectionCase.diskAmount != 0) { //stamatescu
            AutoCase.Intake();
            AutoCase.Shoot();
        }
        DetectionCase.autoCase.releaseFirstWobble();
        Wobble.setGrabberCase(armCase.LEFT);
        Wobble.motorArmToPosition(wobblePosition.DOWN);
        sleep(500);
        Wobble.SetGrabberPosition(grabberPosition.FREE);
        AutoCase.returnBack();
        DetectionCase.autoCase.releaseSecondWobble();
        AutoCase.Park();
    }
}

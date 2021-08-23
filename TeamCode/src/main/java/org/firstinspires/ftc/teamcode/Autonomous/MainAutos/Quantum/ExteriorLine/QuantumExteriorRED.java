package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.ExteriorLine;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full.TrajFull;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Worst.TrajWorst;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;
import org.intellij.lang.annotations.JdkConstants;

@Autonomous(name = "QuantumExteriorRED")
public class QuantumExteriorRED extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        TrajQuantumExterior.initSpecificTraj(ColorCase.RED);
        Initializations.AutoInit(this, armCase.RIGHT, TrajQuantumExterior.getStartPose());
        waitForStart();

        DiskAmountDetection.stopDetection();
        AutoCase.setAutoScene(AutoScenes.QuantumExterior);

        DetectionCase.autoCase.Shoot();
        if (DetectionCase.diskAmount != 0) {
            AutoCase.Intake(); //todo also shoot them in high goal
            AutoCase.ShootSecondHighGoal();
        }

//        DetectionCase.autoCase.releaseFirstWobble();
//        AutoCase.Park();

    }
}

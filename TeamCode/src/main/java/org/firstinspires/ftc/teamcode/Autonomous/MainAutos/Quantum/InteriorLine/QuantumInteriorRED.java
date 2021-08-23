package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.InteriorLine;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.Autonomous.AutoScenes;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.ExteriorLine.TrajQuantumExterior;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;


@Autonomous(name = "QuantumInteriorRED")
public class QuantumInteriorRED extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        TrajQuantumInterior.initSpecificTraj(ColorCase.RED);
        Initializations.AutoInit(this, armCase.RIGHT, TrajQuantumInterior.getStartPose());
        waitForStart();

        DiskAmountDetection.stopDetection();
        AutoCase.setAutoScene(AutoScenes.QuantumInterior);

        DetectionCase.autoCase.releaseFirstWobble();

    }
}

package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;
import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.DetectionCase;
import org.firstinspires.ftc.teamcode.Autonomous.PowerShots;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;

@Autonomous(name = "Intermediate1AllyRED")
public class Intermediate1AllyRED extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Initializations.AutoInit(this);
        //Wobble.setGrabberCase(armCase.LEFT);
        waitForStart();

        DiskAmountDetection.stopDetection();
        PowerShots.initialization(drive, this);
        PowerShots.run();
        DetectionCase.autoCase.releaseFirstWobble();

    }
}

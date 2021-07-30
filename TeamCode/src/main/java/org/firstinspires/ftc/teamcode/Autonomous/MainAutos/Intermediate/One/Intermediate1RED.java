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
public class Intermediate1RED extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Initializations.AutoInit(this,armCase.LEFT);
        PowerShots.initialization(drive, this);
        waitForStart();

        DiskAmountDetection.stopDetection();
        PowerShots.run();
        DetectionCase.autoCase.releaseFirstWobble();

    }
}

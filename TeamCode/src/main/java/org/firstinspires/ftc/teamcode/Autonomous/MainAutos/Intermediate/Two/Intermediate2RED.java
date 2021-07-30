package org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Initializations;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.armCase;

public class Intermediate2RED extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Initializations.AutoInit(this,armCase.LEFT);

        waitForStart();

    }
}

package org.firstinspires.ftc.teamcode.Debugs;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;

public class Instruction {
    public static void Commands(Telemetry telemetry, boolean update) {
        telemetry.addLine("\n\n**********GAMEPAD1 --> Stamatie");
        telemetry.addLine("----Movement----");
        telemetry.addLine("             All sticks - driving");
        telemetry.addLine("             Hold right_trigger, then use the sticks to drive slower");
        telemetry.addLine("----Shoot/Intake----");
        telemetry.addLine("             left_bumper -- Shooter Motors");
        telemetry.addLine("             right_bumper -- Intake Motors");
        telemetry.addLine("             dpad_right -- Shoot Position \n\n");


        telemetry.addLine("**********GAMEPAD2 --> Domi");
        telemetry.addLine("----Wobble----");
        telemetry.addLine("             x -- Release/Grab Wobble");
        telemetry.addLine("             dpad_up -- Wobble UP position");
        telemetry.addLine("             dpad_right -- Wobble MIDDLE position");
        telemetry.addLine("             dpad_down -- Wobble DOWN position");
        telemetry.addLine("----Wall----");
        telemetry.addLine("             y -- Wall control");
        telemetry.addLine("----Shoot/Intake----");
        telemetry.addLine("             right_bumper -- Outtake");
        telemetry.addLine("             a -- Shooter's SHOOT position");
        telemetry.addLine("             b -- Shooter's INTAKE position");
        if (update) {
            telemetry.update();
        }

    }
}

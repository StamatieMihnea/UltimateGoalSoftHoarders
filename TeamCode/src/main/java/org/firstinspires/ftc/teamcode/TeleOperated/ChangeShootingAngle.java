package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Devices;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;
import org.jetbrains.annotations.NotNull;

public class ChangeShootingAngle {

    public static final double shootPosition = 0.66;
    public static final double genericPosition = 0.562;
    public static final  double rightOffset = 0.105;

    private static final double incrementValue = 0.03;
    private static final double upperLimit = 0.7;
    private static final double lowerLimit = 0;

    public static double getIncrementValue() {
        return incrementValue;
    }

    public static double getAbsPosition() {
        return absPosition;
    }


    private static double absPosition = genericPosition;
    private static final OneTap up = new OneTap();
    private static final OneTap down = new OneTap();

    public static void AngleControl(double position) {
        Devices.setServoPosition(Hardware.angle_control_right_s, "angle_control_right_s", 1 - position - rightOffset);
        Devices.setServoPosition(Hardware.angle_control_left_s, "angle_control_left_s", position);
    }

    private static void LimitAngle() {
        if (absPosition < lowerLimit) {
            absPosition = lowerLimit;
        }
        if (absPosition > upperLimit) {
            absPosition = upperLimit;
        }
    }

    private static void SequentialIncrement(boolean upB, boolean downB) {
        if (up.onPress(upB)) {
            absPosition += incrementValue;
        }

        if (down.onPress(downB)) {
            absPosition -= incrementValue;
        }

    }

    public static void AngleControl(@NotNull Gamepad gamepad) {
        LimitAngle();
        AngleControl(absPosition);
        generalPose(gamepad.a);

    }

    public static void generalPose(boolean button){
        if(button){
            absPosition = genericPosition;
        }
    }

    public static void ShootingIntakePositions(boolean firstButton, boolean secondButton) {
        if (firstButton) {
            absPosition = shootPosition; //shooting
        } else if (secondButton) {
            absPosition = genericPosition; //intake
        }
    }

}

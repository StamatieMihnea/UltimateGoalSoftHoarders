package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoCommandGroup;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoToPosition;
import org.jetbrains.annotations.NotNull;

public class Wall {
    private static final double left_extendedPosition = 0.955f;
    private static final double left_retractedPosition = 0.0f;
    private static final double left_middlePosition = 0.6f;

    private static final double right_retractedPosition = 1.0f;
    private static final double right_extendedPosition = 0.1f;
    private static final double right_middlePosition = 0.5f;

    private static final ServoCommandGroup leftWall = new ServoCommandGroup(Hardware.wall_left, left_middlePosition, left_extendedPosition);
    private static final ServoCommandGroup rightWall = new ServoCommandGroup(Hardware.wall_right, right_middlePosition, right_extendedPosition);
    private static final ServoToPosition wallServoControlLeft = new ServoToPosition(leftWall);
    private static final ServoToPosition wallServoControlRight = new ServoToPosition(rightWall);

    public static void wallControl(@NotNull Gamepad gamepad) {
        wallServoControlLeft.modifyPosition(gamepad.x);
        wallServoControlRight.modifyPosition(gamepad.b);
        wallClose(gamepad.y);
        wallOpen(gamepad.left_bumper);
    }

    public static void wallClose(boolean activate) {
        if (activate) {
            Hardware.wall_left.setPosition(left_retractedPosition);
            Hardware.wall_right.setPosition(right_retractedPosition);
            wallServoControlLeft.ResetForSecond();
            wallServoControlRight.ResetForSecond();
        }
    }

    public static void wallOpen(boolean activate){
        if (activate) {
            Hardware.wall_left.setPosition(left_extendedPosition);
            Hardware.wall_right.setPosition(right_extendedPosition);
            wallServoControlLeft.ResetForFirst();
            wallServoControlRight.ResetForFirst();
        }
    }


    public static void Vertical() {
        Hardware.wall_right.setPosition(right_middlePosition);
        Hardware.wall_left.setPosition(left_middlePosition);

    }

    public static void initialization() {
        Hardware.wall_left.setPosition(left_retractedPosition);
        Hardware.wall_right.setPosition(right_retractedPosition);
    }
}

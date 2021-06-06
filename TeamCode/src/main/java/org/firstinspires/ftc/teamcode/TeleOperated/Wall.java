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
    private static final double right_extendedPosition = 0.13f;
    private static final double right_middlePosition = 0.5f;

    private static final ServoCommandGroup leftWall = new ServoCommandGroup(Hardware.wall_left, left_extendedPosition, left_middlePosition);
    private static final ServoCommandGroup rightWall = new ServoCommandGroup(Hardware.wall_right, right_extendedPosition, right_middlePosition);
    private static final ServoToPosition wallServoControl = new ServoToPosition(leftWall, rightWall);

    public static void wallControl(@NotNull Gamepad gamepad) {
        wallServoControl.modifyPosition(gamepad.y);
    }

    public static void Vertical(){
        Hardware.wall_right.setPosition(right_middlePosition);
        Hardware.wall_left.setPosition(left_middlePosition);

    }

    public static void initialization() {
        Hardware.wall_left.setPosition(left_retractedPosition);
        Hardware.wall_right.setPosition(right_retractedPosition);
    }
}

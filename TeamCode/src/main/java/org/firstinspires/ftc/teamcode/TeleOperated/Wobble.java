package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoCommandGroup;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoToPosition;

public class Wobble {
    private static final double back_grabPosition = 0.25f;
    private static final double back_freePosition = 0.55f;

    private static final double front_grabPosition = 0.75f;
    private static final double front_freePosition = 0.45f;

    public static int wobbleArmAutoOffset = 500;

    private static final int wobbleUpPose = 20 - wobbleArmAutoOffset;
    private static final int wobbleDownPose = 600 - wobbleArmAutoOffset;
    private static final int wobbleMidPose = 250 - wobbleArmAutoOffset;

    private static final ServoCommandGroup backServo = new ServoCommandGroup(Hardware.grabber_back, back_freePosition, back_grabPosition);
    private static final ServoCommandGroup frontServo = new ServoCommandGroup(Hardware.grabber_front, front_freePosition, front_grabPosition);
    private static final ServoToPosition grabberServos = new ServoToPosition(backServo, frontServo);


    public static void wobbleRelease() {
        Hardware.grabber_front.setPosition(front_freePosition);
        Hardware.grabber_back.setPosition(back_freePosition);
    }

    public static void servoPositions(boolean button) {
        grabberServos.modifyPosition(button);
    }

    public static void motorArm(double up, double down) {
        if (down <= 0.05) Hardware.grabber.setPower(up / 2);
        if (up <= 0.05) Hardware.grabber.setPower(-down / 2);
    }

    public static void initialization() {
        Hardware.grabber.setTargetPosition(0);
        Hardware.grabber.setPower(1);
        Hardware.grabber_front.setPosition(front_grabPosition - 0.1);
        Hardware.grabber_back.setPosition(back_grabPosition + 0.1);
    }

    public static void initializationAuto() {
        Hardware.grabber.setTargetPosition(0);
        Hardware.grabber.setPower(1);
        Hardware.grabber_back.setPosition(back_grabPosition);
        Hardware.grabber_front.setPosition(front_grabPosition);
        //Hardware.grabber_front.setPosition(front_grabPosition - 0.05);
        //Hardware.grabber_back.setPosition(back_grabPosition + 0.05);
    }


    public static void close() {
        Hardware.grabber_front.setPosition(front_grabPosition + 0.1);
        Hardware.grabber_back.setPosition(back_grabPosition - 0.1);
    }

    public static void motorArmToPosition(boolean button, int position) {
        if (button) {
            Hardware.grabber.setTargetPosition(position);
        }

    }

    public static void wobbleControl(Gamepad gamepad) {
        servoPositions(gamepad.x);
        motorArmToPosition(gamepad.dpad_up, wobbleUpPose);
        motorArmToPosition(gamepad.dpad_right, wobbleMidPose);
        motorArmToPosition(gamepad.dpad_down, wobbleDownPose);
    }
}

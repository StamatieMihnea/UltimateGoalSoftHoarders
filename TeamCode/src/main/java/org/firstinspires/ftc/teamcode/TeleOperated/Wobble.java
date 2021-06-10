package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoCommandGroup;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoToPosition;


public class Wobble {
    private static final double back_grabPosition = 0.31f;
    private static final double back_freePosition = 0.67f;

    private static final double front_grabPosition = 0.67f;
    private static final double front_freePosition = 0.3f;

    private static final int correctionIncrementValue = 50;
    private static wobblePosition currentPose = wobblePosition.UP;

    private static int wobbleUpPose = 0;
    private static int wobbleDownPose = 620;
    private static int wobbleMidPose = 250;

    private static final OneTap sumUp = new OneTap();
    private static final OneTap sumDown = new OneTap();

    private static final ServoCommandGroup backServo = new ServoCommandGroup(Hardware.grabber_back, back_grabPosition, back_freePosition);
    private static final ServoCommandGroup frontServo = new ServoCommandGroup(Hardware.grabber_front,front_grabPosition,front_freePosition);
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
        wobbleUpPose = 20;
       wobbleDownPose = 600;
       wobbleMidPose = 250;

        wobbleUpPose -= PoseStorage.imuAndWobble.getY();
        wobbleDownPose -= PoseStorage.imuAndWobble.getY();
        wobbleMidPose -= PoseStorage.imuAndWobble.getY();

        Hardware.grabber.setTargetPosition(0);
        Hardware.grabber.setPower(1);
        Hardware.grabber_front.setPosition(front_grabPosition - 0.1);
        Hardware.grabber_back.setPosition(back_grabPosition + 0.1);
        Hardware.grabber.setTargetPosition(wobbleUpPose);
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
        Hardware.grabber_front.setPosition(front_grabPosition + 0.05);
        Hardware.grabber_back.setPosition(back_grabPosition - 0.05);
    }

    public static void motorArmToPosition(boolean button, wobblePosition position) {
        if (button) {
            currentPose = position;
        }
    }

    public static void motorArmToPosition(boolean button, int position) {
        if (button) {
            Hardware.grabber.setTargetPosition(position);
        }

    }

    private static void correctPostion(Gamepad gamepad) {
        if (sumUp.onPress(gamepad.right_trigger > 0.2)) {
            wobbleUpPose += correctionIncrementValue;
            wobbleDownPose += correctionIncrementValue;
            wobbleMidPose += correctionIncrementValue;
        }
        if (sumDown.onPress(gamepad.left_trigger > 0.2)) {
            wobbleUpPose -= correctionIncrementValue;
            wobbleDownPose -= correctionIncrementValue;
            wobbleMidPose -= correctionIncrementValue;
        }
    }

    private static void positionUpdate() {
        switch (currentPose) {
            case UP:
                Hardware.grabber.setTargetPosition(wobbleUpPose);
                break;
            case MID:
                Hardware.grabber.setTargetPosition(wobbleMidPose);
                break;
            case DOWN:
                Hardware.grabber.setTargetPosition(wobbleDownPose);
                break;
        }
    }

    public static void wobbleArmControl(Gamepad gamepad) {
        motorArmToPosition(gamepad.dpad_up, wobblePosition.UP);
        motorArmToPosition(gamepad.dpad_right, wobblePosition.MID);
        motorArmToPosition(gamepad.dpad_down, wobblePosition.DOWN);
        correctPostion(gamepad);
        positionUpdate();
    }

    public static void wobbleGrabberControl(Gamepad gamepad) {
        servoPositions(gamepad.x);
    }

}

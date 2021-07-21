package org.firstinspires.ftc.teamcode.TeleOperated;

import android.util.Pair;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoCommandGroup;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoToPosition;


public class Wobble {
    //TODO: // RED --> Right
    // BLUE --> Left
    private static int lastLeftGrabberPosition = 0;
    private static int lastRightGrabberPosition = 0;

    private static final double grabRightBlocker = 0.32;
    private static final double freeRightBlocker = 0.50;
    private static final double grabLeftBlocker = 0.82;
    private static final double freeLeftBlocker = 0.60;

    private static final double leftGrabPosition = 0.82f;
    private static final double leftFreePosition = 0f;

    private static final double rightGrabPosition = 0.82f;
    private static final double rightFreePosition = 0f;

    private static final int correctionIncrementValue = 50;
    private static wobblePosition currentPose = wobblePosition.UP;

    private static int wobbleUpPose = 0;
    private static int wobbleDownPose = 620*3;
    private static int wobbleMidPose = 250*3;

    private static final OneTap sumUp = new OneTap();
    private static final OneTap sumDown = new OneTap();

    private static final OneTap blockerTap = new OneTap();
    private static final ServoCommandGroup rightServo = new ServoCommandGroup(Hardware.servo_wobble_right, rightGrabPosition, rightFreePosition);
    private static final ServoCommandGroup leftServo = new ServoCommandGroup(Hardware.servo_wobble_left, leftGrabPosition, leftFreePosition);
    private static final ServoToPosition grabberServoLeft = new ServoToPosition(leftServo);
    private static final ServoToPosition grabberServoRight = new ServoToPosition(rightServo);

    private static armCase grabberCase = armCase.RIGHT;

    public static armCase getGrabberCase() {
        return grabberCase;
    }

    public static void setGrabberCase(armCase grabberCase) {
        Wobble.grabberCase = grabberCase;
        selectArm();
    }

    public static void changeGrabberCase() {
        if (grabberCase == armCase.LEFT) {
            setGrabberCase(armCase.RIGHT);
        } else {
            setGrabberCase(armCase.LEFT);
        }
    }

    public static void selectArm() {

        if (grabberCase == armCase.RIGHT) {
            Hardware.stopper_right.setPosition(freeRightBlocker);
            Hardware.stopper_left.setPosition(grabLeftBlocker);
        } else {
            Hardware.stopper_right.setPosition(grabRightBlocker);
            Hardware.stopper_left.setPosition(freeLeftBlocker);
        }
    }

    public static void SetSArmTargetPosition(int position) {

        if (grabberCase == armCase.LEFT) {
            Hardware.grabber.setTargetPosition(Hardware.grabber.getCurrentPosition() - (position + lastLeftGrabberPosition));
            lastLeftGrabberPosition = -position;
        } else {
            Hardware.grabber.setTargetPosition(Hardware.grabber.getCurrentPosition() + (position - lastRightGrabberPosition));
            lastRightGrabberPosition = position;
        }
    }

    public static void SetGrabberPosition(grabberPosition position) {
        Pair<grabberPosition, armCase> grabberPositionCasePair = new Pair<>(position, grabberCase);
        if (new Pair<>(grabberPosition.FREE, armCase.LEFT).equals(grabberPositionCasePair)) {
            Hardware.servo_wobble_left.setPosition(leftFreePosition);
        } else if (new Pair<>(grabberPosition.FREE, armCase.RIGHT).equals(grabberPositionCasePair)) {
            Hardware.servo_wobble_left.setPosition(rightFreePosition);
        } else if (new Pair<>(grabberPosition.GRAB, armCase.LEFT).equals(grabberPositionCasePair)) {
            Hardware.servo_wobble_left.setPosition(leftGrabPosition);
        } else if (new Pair<>(grabberPosition.GRAB, armCase.RIGHT).equals(grabberPositionCasePair)) {
            Hardware.servo_wobble_left.setPosition(rightGrabPosition);
        }
    }


    public static void wobbleRelease() {
        SetGrabberPosition(grabberPosition.FREE);
    }

    public static void servoPositions(boolean button) {
        if (grabberCase == armCase.LEFT) {
            grabberServoLeft.modifyPosition(button);
        } else {
            grabberServoRight.modifyPosition(button);
        }
    }

    public static void changeGrabberCaseByButton(boolean button) {
        if (blockerTap.onPress(button)) {
            changeGrabberCase();
        }
    }

    public static void motorArm(double up, double down) {
        if (down <= 0.05) Hardware.grabber.setPower(up / 2);
        if (up <= 0.05) Hardware.grabber.setPower(-down / 2);
    }

    public static void initialization() {

        selectArm();
        wobbleUpPose = 0;
        wobbleDownPose = 600;
        wobbleMidPose = 250;

        wobbleUpPose -= PoseStorage.imuAndWobble.getY();
        wobbleDownPose -= PoseStorage.imuAndWobble.getY();
        wobbleMidPose -= PoseStorage.imuAndWobble.getY();

        Hardware.grabber.setTargetPosition(wobbleUpPose);
        Hardware.grabber.setPower(1);
        SetGrabberPosition(grabberPosition.GRAB);

        Hardware.servo_wobble_left.setPosition(leftGrabPosition - 0.1);
        Hardware.servo_wobble_right.setPosition(rightGrabPosition + 0.1);
    }


    public static void initializationAuto() {
        Hardware.grabber.setTargetPosition(0);
        Hardware.grabber.setPower(0.5);
        Hardware.servo_wobble_right.setPosition(rightGrabPosition);
        //Hardware.grabber_front.setPosition(front_grabPosition - 0.05);
        //Hardware.grabber_back.setPosition(back_grabPosition + 0.05);
    }


    public static void close() {
        Hardware.servo_wobble_right.setPosition(rightGrabPosition - 0.05);
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
        if (currentPose == wobblePosition.UP) {
            SetSArmTargetPosition(wobbleUpPose);
        } else if (currentPose == wobblePosition.MID) {
            SetSArmTargetPosition(wobbleMidPose);
        } else {
            SetSArmTargetPosition(wobbleDownPose);
        }
    }

    public static void wobbleArmControl(Gamepad gamepad) {
        motorArmToPosition(gamepad.dpad_up, wobblePosition.UP);
        motorArmToPosition(gamepad.dpad_right, wobblePosition.MID);
        motorArmToPosition(gamepad.dpad_down, wobblePosition.DOWN);
        correctPostion(gamepad);
        if (gamepad.dpad_down || gamepad.dpad_right || gamepad.dpad_up) {
            positionUpdate();
        }
    }

    public static void wobbleGrabberControl(Gamepad gamepad) {
        changeGrabberCaseByButton(gamepad.y);
        servoPositions(gamepad.x);
    }

}

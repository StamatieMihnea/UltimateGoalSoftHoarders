package org.firstinspires.ftc.teamcode.Autonomous.Utils;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.Autonomous.PowerShots;
import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;

import static org.firstinspires.ftc.teamcode.Autonomous.PowerShots.shootingSpeed;
import static org.firstinspires.ftc.teamcode.HardwarePack.HardwareDeclarations.shooter_left;
import static org.firstinspires.ftc.teamcode.HardwarePack.HardwareDeclarations.shooter_right;

@Config
public class AutoUtil {

    private static final double idlerShootPosition = 0.72;
    private static final double idlerFreePosition = 1.0;
    private static final int shootTime = 100;
    private static final int returnTime = 100;

    private static final int speedTolerance = 41;

    public static final double leftWallDown = 1.0;
    public static final double leftWallVertical = 0.6;
    public static final double leftWallInside = 0;

    public static final double rightWallDown = 0;
    public static final double rightWallVertical = 0.5;
    public static final double rightWallInside = 1.0;

    private static LinearOpMode opMode;
    public static DcMotorEx shoot_leftEX;
    public static DcMotorEx shoot_rightEX;

    private final static double P = 36f;
    private final static double I = 0;
    private final static double D = 0;
    private final static double F = 13.45;

    public static void setOpMode(LinearOpMode opMode) {
        AutoUtil.opMode = opMode;
    }

    public static void initialization() {
        shoot_leftEX = (DcMotorEx) (shooter_left);
        shoot_rightEX = (DcMotorEx) (shooter_right);
        shoot_rightEX.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shoot_leftEX.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shoot_rightEX.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(P, I, D, F));
        shoot_leftEX.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(P, I, D, F));
        shoot_leftEX.setPower(1);
        shoot_rightEX.setPower(1);
        shoot_leftEX.setVelocity(0);
        shoot_rightEX.setVelocity(0);
        Hardware.shooter_idler.setPosition(idlerFreePosition);
        Wobble.initializationAuto();

    }

    public static void shooterAngle(shooterState state) {
        switch (state) {
            case SHOOT:
                ChangeShootingAngle.AngleControl(AutoCase.shooterAngleTower);
                break;
            case POWER_SHOTS:
                ChangeShootingAngle.AngleControl(PowerShots.shooterAnglePowerShoot);
                break;
            case INTAKE:
                ChangeShootingAngle.AngleControl(AutoCase.intakeAngle);
        }
    }

    public static void wallPosition(wallState state) {
        switch (state) {
            case DOWN:
                Hardware.wall_left.setPosition(leftWallDown);
                Hardware.wall_right.setPosition(rightWallDown);
                break;
            case INSIDE:
                Hardware.wall_left.setPosition(leftWallInside);
                Hardware.wall_right.setPosition(rightWallInside);
                break;
            case VERTICAL:
                Hardware.wall_left.setPosition(leftWallVertical);
                Hardware.wall_right.setPosition(rightWallVertical);
                break;
        }
    }

    public static void startShooting() {
        shoot_rightEX.setVelocity(shootingSpeed);
        shoot_leftEX.setVelocity(shootingSpeed);
    }

    public static void startShooting(double speed) {
        shoot_rightEX.setVelocity(speed);
        shoot_leftEX.setVelocity(speed);
    }

    public static void shoot(boolean sleepAfterShoot, boolean sleepBeforeShoot) {
        if (sleepBeforeShoot) {
            opMode.sleep(returnTime);
        }
        Hardware.shooter_idler.setPosition(idlerShootPosition);
        opMode.sleep(shootTime);
        Hardware.shooter_idler.setPosition(idlerFreePosition);
        if (sleepAfterShoot) {
            opMode.sleep(returnTime);
        }
    }

    public static void shoot() {
        Hardware.shooter_idler.setPosition(idlerShootPosition);
        opMode.sleep(shootTime);
        Hardware.shooter_idler.setPosition(idlerFreePosition);
    }

    public static void waitForShooting(MyMecanumDrive drive) {
        while (!AutoUtil.ShootingSpeedInLimit()) {
            drive.update();
        }
    }

    public static boolean ShootingSpeedInLimit() {
        boolean okLeft = (shoot_leftEX.getVelocity() <= shootingSpeed + speedTolerance) && (shoot_leftEX.getVelocity() >= shootingSpeed - speedTolerance);
        boolean okRight = (shoot_rightEX.getVelocity() <= shootingSpeed + speedTolerance) && (shoot_rightEX.getVelocity() >= shootingSpeed - speedTolerance);
        return okLeft && okRight;
    }

    public static void startIntake() {
        Hardware.intake.setPower(1);
        Hardware.shooter_booster.setPower(1);
        shoot_leftEX.setVelocity(-500);
        shoot_rightEX.setVelocity(-500);
    }

    public static void stopIntakeMotor() {
        Hardware.intake.setPower(0);

    }

    public static void stopIntakeServo() {
        Hardware.shooter_booster.setPower(0);
        shoot_leftEX.setVelocity(0);
        shoot_rightEX.setVelocity(0);
    }

    public static void shoot3Disks() {
        shoot(true, true);
        shoot(true, true);
        shoot(false, true);
    }

    public static void stopShooting() {
        shoot_leftEX.setVelocity(0);
        shoot_rightEX.setVelocity(0);
    }


}

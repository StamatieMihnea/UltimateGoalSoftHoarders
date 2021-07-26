package org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Main.Teleoperated;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

@Config
public class GyroPID {

/*    private static final double P = 0.025;
    private static final double I = 0.00;
    private static final double D = 0.08;*/

    /* inainte de dominic

    private static final double P = 0.025;
    private static final double I = 0.007;
    private static final double D = 0.08;*/
//
//    private static final double P = 0.01;
//    private static final double I = 0.015;
//    private static final double D = 0.18;
//

    public static MyMecanumDrive drive;
    public static double P = 0.05; //0.33
    public static double I = 0.032;
    public static double D = 0.3; //0.25


    private static final double AdmittedError = 0.15f;
    private static final double AdmittedVelocity = 0.2; //0.15
    private static double target;
    private static final int ControlNumber = 100;
    private static int nr = 0;
    private static double error = 0;
    private static double lastError = 0;
    private static double Sum = 0;
    private static final double ILimit = 0.2; ///0.5

    public static void setDrive(MyMecanumDrive drive) {
        GyroPID.drive = drive;
    }

    public static void Rotate(double power) {
        drive.setWeightedDrivePower(new Pose2d(0, 0, power));
    }

    public static void StartPID(double command) {
        target = command + NormalizeAngleGyro.GetAngle();
        if (target > 360) {
            target -= 360;
        } else if (target < 0) {
            target += 360;
        }
        error = target - NormalizeAngleGyro.GetAngle();
        lastError = error;
        nr = 0;
    }

    public static void RunPID(Telemetry telemetry) {
//         telemetry.addData("velocity: ", error);
//        telemetry.update();
        error = target - NormalizeAngleGyro.GetAngle();
        if (error > 180) {
            error -= 360;
        } else if (error < -180) {
            error += 360;
        }
        Sum += error;
        double IEffect = Sum * I;
        if (IEffect > ILimit) {
            Sum = 0;
            IEffect = ILimit;
        } else if (IEffect < -ILimit) {
            Sum = 0;
            IEffect = -ILimit;
        }
        double errorDifference = error - lastError;
        double output = P * error + D * errorDifference + IEffect;
        if (nr == ControlNumber) {
            output = 0;
        } else if (Math.abs(error) < AdmittedError) {
            nr++;
        }
        Rotate(output);
        lastError = error;
    }

    public static void rotate(double command, Telemetry telemetry, LinearOpMode opMode) {
        StartPID(command);
        while (opMode.opModeIsActive() && !(opMode.isStopRequested()) && (Math.abs(error) > AdmittedError || Math.abs(Hardware.imu.getAngularVelocity().yRotationRate) > AdmittedVelocity)) {
            Teleoperated.drive.update();
            RunPID(telemetry);
        }
        Rotate(0);
    }
}
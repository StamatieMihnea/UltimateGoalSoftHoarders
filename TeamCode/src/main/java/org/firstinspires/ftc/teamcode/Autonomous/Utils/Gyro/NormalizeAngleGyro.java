package org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Main.Teleoperated;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;

public class NormalizeAngleGyro { //Gyro

    private static final double NormalizeValue = 180f;

    public static double GetAngle(MyMecanumDrive drive) {
        // return (Normalize(Hardware.imu));
        return Math.toDegrees(drive.getPoseEstimate().getHeading());
    }

    public static double GetRawAngle(BNO055IMU imu1, BNO055IMU imu2) {
        return (imu1.getAngularOrientation().firstAngle + imu2.getAngularOrientation().firstAngle) / 2;
    }

    public static double Normalize(BNO055IMU imu1, BNO055IMU imu2) {
        return GetRawAngle(imu1, imu2) + NormalizeValue;
    }
    public static double Normalize(BNO055IMU imu1) {
        return imu1.getAngularOrientation().firstAngle + NormalizeValue;
    }

}
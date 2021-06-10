package org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;

public class NormalizeAngleGyro { //Gyro

    private static final double NormalizeValue = 180f;

    public static double GetAngle() {
        return (Normalize(Hardware.imu));
    }

    public static double GetRawAngle(BNO055IMU imu) {
        return imu.getAngularOrientation().firstAngle;
    }

    private static double Normalize(BNO055IMU imu) {
        return GetRawAngle(imu) + NormalizeValue;
    }
}
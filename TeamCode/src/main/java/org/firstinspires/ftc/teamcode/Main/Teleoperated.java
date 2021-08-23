package org.firstinspires.ftc.teamcode.Main;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.ColorCase;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Gyro.NormalizeAngleGyro;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.NormalizeImuAngle;
import org.firstinspires.ftc.teamcode.ConstantsTeleoperated;
import org.firstinspires.ftc.teamcode.Debugs.Debugs;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.GoToPoint;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.MyMecanumDrive;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Intake;
import org.firstinspires.ftc.teamcode.TeleOperated.Movement;
import org.firstinspires.ftc.teamcode.TeleOperated.Shooter;
import org.firstinspires.ftc.teamcode.TeleOperated.Wall;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.distanceSensor;

@TeleOp(name = "TeleOp", group = "TeleOp's")
public class Teleoperated extends LinearOpMode {

    public static MyMecanumDrive drive;
    public static driveCase currentCase = driveCase.DRIVE;
    public static ColorCase colorCase;
    //public static Pose2d shootPose =  new Pose2d(8, 5, Math.toRadians(163));
    public static double wobbleX = 58;


    private void movementInitialization() {
        drive = new MyMecanumDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Movement.setDrive(drive);
        Movement.setTelemetry(telemetry);
        Pose2d startPose = PoseStorage.currentPose;
        drive.setPoseEstimate(startPose);
    }

    private void initialization() {
        Hardware.init(hardwareMap, telemetry);
        Wobble.initialization();
        Wall.initialization();
        Shooter.shooterInitialization(this);
        movementInitialization();
        NormalizeImuAngle.setDrive(drive);
        ChangeShootingAngle.AngleControlInit();
        GoToPoint.init(this, drive);
        telemetry.addData("Pose Stored", PoseStorage.currentPose.toString());
        telemetry.addData("Wobble pose Stored", PoseStorage.imuAndWobble.getY());
        telemetry.update();
    }


    @Override
    public void runOpMode() {
        initialization();
        waitForStart();


        if (PoseStorage.colorCase.getX() == 0) {
            colorCase = ColorCase.BLUE;
        } else {
            colorCase = ColorCase.RED;
        }

        while (!isStopRequested() && opModeIsActive()) {

            Pose2d estimatedPose = drive.getPoseEstimate();
            double estimatedX = estimatedPose.getX();
            double estimatedY = estimatedPose.getY();
            drive.setPoseEstimate(new Pose2d(estimatedX, estimatedY, Math.toRadians(NormalizeAngleGyro.Normalize(Hardware.imu))));

            //WOBBLE
            Wobble.wobbleArmControl(gamepad2);
            Wobble.wobbleGrabberControl(gamepad1);

            //ROAD RUNNER
            // GoToPoint.strafe(gamepad1.dpad_right, 8, 6, NormalizeImuAngle.heading(160, true), drive, 35, 60, false); //Old shoot pose
            //  GoToPoint.strafe(gamepad1.dpad_up, wobbleX, drive.getPoseEstimate().getY(), NormalizeImuAngle.heading(90, true), drive, 40, 70, true); //Old wobble pose
            // GoToPoint.POWERSHOTS(gamepad2.dpad_left, drive); //Old powershots routine

            //MOVEMENT
            if (currentCase == driveCase.DRIVE) {
                Movement.slowMovement(gamepad1, ConstantsTeleoperated.onPressSlowFactor);
                Movement.driving(gamepad1);
            }
            drive.update();

            //SHOOTER
            Shooter.ShooterControl(gamepad1);

            //SHOOT ANGLE
            ChangeShootingAngle.AngleControl(gamepad2);

            //GoToPoint.HighGoalAutoOrientation(gamepad1.back, drive);
            GoToPoint.HighGoalAutoOrientationImu(gamepad1.back, drive);

            //INTAKE
            Intake.IntakeOneSpeed(gamepad1);
            Intake.OutTakeOneSpeed(gamepad2);
            distanceSensor.update();

            //WALL
            Wall.wallControl(gamepad2);

            //DEBUGS
            Debugs.shouldIntakeDebug(telemetry, false);
            Debugs.distanceSensorDebug(telemetry, false);
            Movement.localize(false);
            telemetry.addLine("ANGLE CONTROL POS :" + String.valueOf(Hardware.angle_control_left_s.getPosition()));

            /*Instruction.Commands(telemetry, false);
            telemetry.addData("grabber pos is: ", Hardware.grabber.getCurrentPosition());
            telemetry.addData("IMU RAW: ", Hardware.imu.getAngularOrientation().firstAngle);
            telemetry.addData("IMU NORMALIZED ANGLE", NormalizeImuAngle.convert(Hardware.imu.getAngularOrientation().firstAngle));
            telemetry.addData("RoadRunner Angle", Math.toDegrees(drive.getPoseEstimate().getHeading()));
            telemetry.addData("Heading", Math.toDegrees(NormalizeImuAngle.heading(90, true)));
            telemetry.update();*/


        }

    }
}

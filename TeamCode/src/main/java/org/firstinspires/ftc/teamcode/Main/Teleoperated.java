package org.firstinspires.ftc.teamcode.Main;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Debugs.Debugs;
import org.firstinspires.ftc.teamcode.Debugs.Instruction;
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

    //TODO wobble arm after auto
    //        -without reset encoder
    //        -touch sensor ????????? 
    //        -manual
    //TODO wall on separate buttons
    //TODO wall inside for wobble delivery

    MyMecanumDrive drive;

    public static Pose2d shootPose = new Pose2d(8, 0, Math.toRadians(162));
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
        Shooter.shooterInitialization();
        movementInitialization();
    }


    @Override
    public void runOpMode() {
        initialization();
        waitForStart();


        while (!isStopRequested() && opModeIsActive()) {


            //WOBBLE
            Wobble.wobbleControl(gamepad2);

            //ROAD RUNNER
            GoToPoint.strafe(gamepad1.dpad_right, shootPose,drive);
            GoToPoint.strafe(gamepad1.dpad_up,wobbleX,drive.getPoseEstimate().getY(),Math.toRadians(90),drive);

            //MOVEMENT
            Movement.slowMovement(gamepad1, 3);
            Movement.driving(gamepad1);

            //SHOOTER
            Shooter.ShooterControl(gamepad1);

            //SHOOT ANGLE
            ChangeShootingAngle.AngleControl(gamepad2);

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
            Instruction.Commands(telemetry, true);


        }

    }
}

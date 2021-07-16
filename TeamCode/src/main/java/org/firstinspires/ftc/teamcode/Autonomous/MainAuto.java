package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.DiskAmountDetection;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.NormalizeImuAngle;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;

@Autonomous(name = "Autonomous")
public class MainAuto extends LinearOpMode {

    public static int diskAmount = 0;
    public static AutoCase autoCase;


    @Override
    public void runOpMode() throws InterruptedException {

        Initializations.AutoInit(this);
        waitForStart();


        DiskAmountDetection.stopDetection();

        PowerShots.initialization(drive, this);
        PowerShots.run();


        switch (MainAuto.diskAmount) {
            case 0:
                MainAuto.autoCase = new A(this);
                break;
            case 1:
                MainAuto.autoCase = new B(this);
                break;
            case 4:
                MainAuto.autoCase = new C(this);
                break;
        }
        autoCase = new C(this);

        autoCase.releaseFirstWobble();

        AutoCase.collectSecondWobble(this);


        autoCase.IntakeShoot();
//
//        autoCase.releaseSecondWobble();
//        sleep(50);
//
//
//        PoseStorage.imuAndWobble = new Pose2d(Math.toDegrees(drive.getPoseEstimate().getHeading()), Hardware.grabber.getCurrentPosition(), 0);
//        PoseStorage.currentPose = drive.getPoseEstimate();

//        while (opModeIsActive() && !isStopRequested()) {
//            idle();
//        }

    }
}

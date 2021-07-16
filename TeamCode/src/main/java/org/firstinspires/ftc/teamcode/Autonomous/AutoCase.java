package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.sun.tools.javac.Main;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;

public interface AutoCase {
    
    double intakeX = GeneralAutoParameters.intakeX;
    double intakeY = GeneralAutoParameters.intakeY;
    double intakeVel = GeneralAutoParameters.intakeVel;
    double intakeAcc = GeneralAutoParameters.intakeAcc;

    int wobble2ArmPosition = GeneralAutoParameters.wobble2ArmPosition;
    int wobbleMidPosition = GeneralAutoParameters.wobbleMidPosition;
    int wobbleCollectPosition = GeneralAutoParameters.wobbleCollectPosition;
    int wobbleArmSleep = GeneralAutoParameters.wobbleArmSleep;
    int sleepForGrabbing = GeneralAutoParameters.sleepForGrabbing;

    double wobblePos2X = GeneralAutoParameters.wobblePos2X;
    double wobblePos2Y = GeneralAutoParameters.wobblePos2Y;
    double wobbleVel = GeneralAutoParameters.wobbleVel;
    double wobbleAcc = GeneralAutoParameters.wobbleAcc;

    double shootSpeed = GeneralAutoParameters.shootSpeed;
    double shootPoseX = GeneralAutoParameters.shootPoseX;
    double shootPoseY = GeneralAutoParameters.shootPoseY;
    double shootAngle = GeneralAutoParameters.shootAngle;

    double shooterAngleTower = GeneralAutoParameters.shooterAngleTower;
    double intakeAngle = GeneralAutoParameters.intakeAngle;

    void releaseFirstWobble();

    static void collectSecondWobble(LinearOpMode opMode) {
        Wobble.motorArmToPosition(true, wobbleCollectPosition);
        switch (MainAuto.diskAmount) {
            case 0:
                Trajectories.returnBack = Trajectories.returnBackA(drive.getPoseEstimate());
                break;
            case 1:
                Trajectories.returnBack = Trajectories.returnBackB(drive.getPoseEstimate());
                break;
            case 4:
                Trajectories.returnBack = Trajectories.returnBackC(drive.getPoseEstimate());
               // Trajectories.returnBack1 = Trajectories.returnBackC1(drive.getPoseEstimate());
                break;
        }
        drive.followTrajectory(Trajectories.returnBack);
//        drive.followTrajectory(Trajectories.returnBack1);
//        Trajectories.returnBack2 = Trajectories.returnBackC2(drive.getPoseEstimate());
//        drive.followTrajectory(Trajectories.returnBack2);
        drive.followTrajectory(Trajectories.secondWobbleCollect(drive.getPoseEstimate()));
       // opMode.sleep(sleepForGrabbing);
    }

    void IntakeShoot();

    void releaseSecondWobble();

}

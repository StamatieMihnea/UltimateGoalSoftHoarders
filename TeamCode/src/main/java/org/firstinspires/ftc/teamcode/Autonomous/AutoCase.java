package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full.TrajFull;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.Two.TrajIntermTwo;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Worst.TrajWorst;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;

public abstract class AutoCase {

    public static  AutoScenes autoScene;

    public static AutoScenes getAutoScene() {
        return autoScene;
    }

    public static void setAutoScene(AutoScenes autoScene) {
        AutoCase.autoScene = autoScene;
    }

    public static double intakeX = GeneralAutoParameters.intakeX;
    public static double intakeY = GeneralAutoParameters.intakeY;
    public static double intakeVel = GeneralAutoParameters.intakeVel;
    public static double intakeAcc = GeneralAutoParameters.intakeAcc;

    public static int wobble2ArmPosition = GeneralAutoParameters.wobble2ArmPosition;
    public static int wobbleMidPosition = GeneralAutoParameters.wobbleMidPosition;
    public static int wobbleCollectPosition = GeneralAutoParameters.wobbleCollectPosition;
    public static int wobbleArmSleep = GeneralAutoParameters.wobbleArmSleep;
    public static int sleepForGrabbing = GeneralAutoParameters.sleepForGrabbing;

    public static double wobblePos2X = GeneralAutoParameters.wobblePos2X;
    public static double wobblePos2Y = GeneralAutoParameters.wobblePos2Y;
    public static double wobbleVel = GeneralAutoParameters.wobbleVel;
    public static double wobbleAcc = GeneralAutoParameters.wobbleAcc;

    public static double shootSpeed = GeneralAutoParameters.shootSpeed;
    public static double shootPoseX = GeneralAutoParameters.shootPoseX;
    public static double shootPoseY = GeneralAutoParameters.shootPoseY;
    public static double shootAngle = GeneralAutoParameters.shootAngle;

    public static double shooterAngleTower = GeneralAutoParameters.shooterAngleTower;
    public static double intakeAngle = GeneralAutoParameters.intakeAngle;

    public abstract void releaseFirstWobble();

    public abstract void returnBack();


    //static void collectSecondWobble(LinearOpMode opMode) {

//        Wobble.motorArmToPosition(true, wobbleCollectPosition);
//        switch (DetectionCase.diskAmount) {
//            case 0:
//                Trajectories.returnBack = Trajectories.returnBackA(drive.getPoseEstimate());
//                break;
//            case 1:
//                Trajectories.returnBack = Trajectories.returnBackB(drive.getPoseEstimate());
//                break;
//            case 4:
//                Trajectories.returnBack = Trajectories.returnBackC(drive.getPoseEstimate());
//               // Trajectories.returnBack1 = Trajectories.returnBackC1(drive.getPoseEstimate());
//                break;
//        }
//        drive.followTrajectory(Trajectories.returnBack);
////        drive.followTrajectory(Trajectories.returnBack1);
////        Trajectories.returnBack2 = Trajectories.returnBackC2(drive.getPoseEstimate());
////        drive.followTrajectory(Trajectories.returnBack2);
//        drive.followTrajectory(Trajectories.secondWobbleCollect(drive.getPoseEstimate()));
//       // opMode.sleep(sleepForGrabbing);
   // }

    public static void Intake() {
        switch (autoScene){
            case FULL:
                AutoUtil.startIntake();
                drive.followTrajectory(TrajFull.diskCollectTrajectory(drive.getPoseEstimate()));
                AutoUtil.stopIntakeMotor();
                AutoUtil.stopIntakeServo();
                break;
            case INTERMEDIATE1:

                break;
            case INTERMEDIATE2:
                AutoUtil.startIntake();
                drive.followTrajectory(TrajIntermTwo.diskCollectTrajectory(drive.getPoseEstimate()));
                AutoUtil.stopIntakeMotor();
                AutoUtil.stopIntakeServo();
                //maybe trajectory 2 for the 4th disk
                //drive.followTrajectory(TrajIntermTwo.diskCollect2(drive.getPoseEstimate()));
                //AutoUtil.stopIntakeMotor();
                //AutoUtil.stopIntakeServo();
                break;
            case WORST:

                break;
        }
    }

    public static void Shoot() {
        switch (autoScene){
            case FULL:
                AutoUtil.shooterAngle(shooterState.SHOOT);
                //hopefully it will be in a good enough position in order to shoot
                //drive.followTrajectory(TrajFull.shootPoseTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case INTERMEDIATE1:
                AutoUtil.wallPosition(wallState.VERTICAL);
                AutoUtil.shooterAngle(shooterState.SHOOT);
                drive.followTrajectory(TrajIntermOne.shootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case INTERMEDIATE2:
                AutoUtil.shooterAngle(shooterState.SHOOT);
                //hopefully it will be in a good enough position in order to shoot
                //drive.followTrajectory(TrajIntermTwo.shootPoseTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case WORST:
                AutoUtil.wallPosition(wallState.VERTICAL);
                AutoUtil.shooterAngle(shooterState.SHOOT);
                drive.followTrajectory(TrajWorst.shootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                AutoUtil.wallPosition(wallState.INSIDE);
                break;
        }
    }

    public abstract void releaseSecondWobble();

    public static void Park() {
        switch (autoScene){
            case FULL:

                break;
            case INTERMEDIATE1:
                drive.followTrajectory(TrajIntermOne.parkTrajectory(drive.getPoseEstimate()));
                break;
            case INTERMEDIATE2:
                drive.followTrajectory(TrajIntermTwo.parkTrajectory(drive.getPoseEstimate()));
                break;
            case WORST:
                drive.followTrajectory(TrajWorst.ParkTrajectory(drive.getPoseEstimate()));
                break;
        }

    }

}

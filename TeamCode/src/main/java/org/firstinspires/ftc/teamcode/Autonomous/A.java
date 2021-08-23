package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full.TrajFull;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.Two.TrajIntermTwo;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Quantum.ExteriorLine.TrajQuantumExterior;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Worst.TrajWorst;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.grabberPosition;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;

public class A extends AutoCase {

    public static double firstWobbleX = -70f;
    public static double firstWobbleY = 95f;
    public static double firstWobbleHeading = 180f;

    public static double secondWobbleX = 10f;
    public static double secondWobbleY = 125f;
    public static double secondWobbleHeading = 270f;

    private final LinearOpMode opMode;

    @Override
    public void Shoot(){
        switch (autoScene){
            case INTERMEDIATE1:
                AutoUtil.wallPosition(wallState.VERTICAL);
                AutoUtil.shooterAngle(shooterState.SHOOT);
                drive.followTrajectory(TrajIntermOne.shootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case FULL:
                drive.followTrajectory(TrajFull.goToDiskCollectPoseWithoutIntake(drive.getPoseEstimate()));
                break;
            case INTERMEDIATE2:
                drive.followTrajectory(TrajIntermTwo.goToDiskCollectPoseWithoutIntake(drive.getPoseEstimate()));
                break;
            case WORST:
                AutoUtil.wallPosition(wallState.VERTICAL);
                AutoUtil.shooterAngle(shooterState.SHOOT);
                drive.followTrajectory(TrajWorst.shootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case QuantumExterior:
                AutoUtil.wallPosition(wallState.VERTICAL);
                AutoUtil.shooterAngle(shooterState.SHOOT);
                drive.followTrajectory(TrajQuantumExterior.ShootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case QuantumInterior:
                break;
        }


    }

    @Override
    public void releaseFirstWobble() {
        switch (autoScene){
            case FULL:
                AutoUtil.stopShooting();
                drive.followTrajectory(TrajFull.releaseATrajectory(drive.getPoseEstimate()));
                break;
            case INTERMEDIATE1:
                drive.followTrajectory(TrajIntermOne.releaseATrajectory(drive.getPoseEstimate()));
                //TODO inspect here Wobble.motorArmToPosition(true, wobblePosition.MID);
                break;
            case INTERMEDIATE2:
                AutoUtil.wallPosition(wallState.INSIDE);
                drive.followTrajectory(TrajIntermTwo.releaseATrajectory(drive.getPoseEstimate()));
                //TODO inspect here Wobble.motorArmToPosition(true, wobblePosition.MID);
                //opMode.sleep(wobbleArmSleep);
                //Wobble.setGrabberPosition(grabberposition.free\grabpos.grab);
                //opMode.sleep(wobbleArmSleep);
                break;
            case WORST:
                break;
            case QuantumExterior:
                AutoUtil.stopShooting();
                drive.followTrajectory(TrajQuantumExterior.releaseATrajectory(drive.getPoseEstimate()));
                break;
            case QuantumInterior:
                break;
        }


//        drive.followTrajectory(Trajectories.firstWobbleReleaseA(drive.getPoseEstimate()));
//        Wobble.motorArmToPosition(true, wobbleMidPosition);
//        opMode.sleep(wobbleArmSleep);
//        Wobble.wobbleRelease();
//        opMode.sleep(wobbleArmSleep);
    }

    @Override
    public void releaseSecondWobble() {
        switch (autoScene){
            case FULL:
                drive.followTrajectory(TrajFull.secondWobbleTrajA(drive.getPoseEstimate()));
                Wobble.motorArmToPosition(wobblePosition.DOWN);
                opMode.sleep(500);
                Wobble.wobbleRelease();
                opMode.sleep(800);
                Wobble.motorArmToPosition(wobblePosition.UP);
                opMode.sleep(200);
                Wobble.SetGrabberPosition(grabberPosition.GRAB);
                opMode.sleep(700);
                break;
            case INTERMEDIATE1:
                break;
            case INTERMEDIATE2:
                break;
            case WORST:
                break;
            case QuantumExterior:
                break;
            case QuantumInterior:
                break;

        }

//        Wobble.motorArmToPosition(true, wobble2ArmPosition);
//        Hardware.wall_right.setPosition(AutoUtil.rightWallDown);
//        drive.followTrajectory(Trajectories.secondWobbleReleaseA(drive.getPoseEstimate()));
//        Wobble.wobbleRelease();
    }



    public A(LinearOpMode opMode) {
        this.opMode = opMode;
    }

}

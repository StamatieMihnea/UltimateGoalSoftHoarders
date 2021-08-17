package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Full.TrajFull;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.Two.TrajIntermTwo;
import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Worst.TrajWorst;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoIntakeShoot;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.shooterState;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.wallState;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.TeleOperated.grabberPosition;
import org.firstinspires.ftc.teamcode.TeleOperated.wobblePosition;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;

public class C extends AutoCase {
//
//    public static double firstWobbleX = -157f; // -160f
//    public static double firstWobbleY = 100f;
//    public static double firstWobbleHeading = 180f;
//
//    public static double secondWobbleX = -105f;
//    public static double secondWobbleY = 135f;
//    public static double secondWobbleHeading = 270f;

    private final LinearOpMode opMode;

    @Override
    public void Shoot() {
        switch (autoScene) {
            case INTERMEDIATE1:
                AutoUtil.wallPosition(wallState.VERTICAL);
                AutoUtil.shooterAngle(shooterState.SHOOT);
                drive.followTrajectory(TrajIntermOne.shootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case FULL:
                AutoUtil.shooterAngle(shooterState.SHOOT);
                AutoUtil.startShooting();
                drive.followTrajectory(TrajFull.ShootTrajectory(drive.getPoseEstimate()));
                AutoUtil.shoot3Disks();
                AutoUtil.wallPosition(wallState.INSIDE);
                break;
            case INTERMEDIATE2:
                AutoUtil.shooterAngle(shooterState.SHOOT);
                AutoUtil.startShooting();
                drive.followTrajectory(TrajIntermTwo.ShootTrajectory(drive.getPoseEstimate()));
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

    @Override
    public void releaseFirstWobble() {
        switch (autoScene) {
            case FULL:
                drive.followTrajectory(TrajFull.releaseCTrajectory(drive.getPoseEstimate()));
                break;
            case INTERMEDIATE1:
                drive.followTrajectory(TrajIntermOne.releaseCTrajectory(drive.getPoseEstimate()));
                break;
            case INTERMEDIATE2:
                drive.followTrajectory(TrajIntermTwo.releaseCTrajectory(drive.getPoseEstimate()));
                //opMode.sleep(wobbleArmSleep);
                //Wobble.setGrabberPosition(grabberposition.free\grabpos.grab);
                //opMode.sleep(wobbleArmSleep);
                break;
            case WORST:
                break;
        }
//        drive.followTrajectory(Trajectories.firstWobbleReleaseC(drive.getPoseEstimate()));
//        Wobble.wobbleRelease();
        //opMode.sleep(wobbleArmSleep);
    }

    @Override
    public void releaseSecondWobble() {
        switch (autoScene) {
            case FULL:
                drive.followTrajectory(TrajFull.secondWobbleTrajC(drive.getPoseEstimate()));
                Wobble.motorArmToPosition(wobblePosition.DOWN);
                opMode.sleep(300);
                Wobble.wobbleRelease();
                break;
            case INTERMEDIATE1:

                break;
            case INTERMEDIATE2:

                break;
            case WORST:

                break;
        }
    }


//        Hardware.wall_left.setPosition(AutoUtil.leftWallDown);
//        drive.followTrajectory(Trajectories.secondWobbleReleaseC(drive.getPoseEstimate()));
//        Wobble.wobbleRelease();

    public C(LinearOpMode opMode) {
        this.opMode = opMode;
    }
}

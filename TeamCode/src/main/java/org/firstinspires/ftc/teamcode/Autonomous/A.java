package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.MainAutos.Intermediate.One.TrajIntermOne;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;

@Config
public class A extends AutoCase {

    public static double firstWobbleX = -70f;
    public static double firstWobbleY = 95f;
    public static double firstWobbleHeading = 180f;

    public static double secondWobbleX = 10f;
    public static double secondWobbleY = 125f;
    public static double secondWobbleHeading = 270f;

    private final LinearOpMode opMode;

    @Override
    public void releaseFirstWobble() {
        switch (autoScene){
            case FULL:

                break;
            case INTERMEDIATE1:
                drive.followTrajectory(TrajIntermOne.releaseATrajectory(drive.getPoseEstimate()));
                break;
            case INTERMEDIATE2:

                break;
            case WORST:

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

                break;
            case INTERMEDIATE1:

                break;
            case INTERMEDIATE2:

                break;
            case WORST:

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

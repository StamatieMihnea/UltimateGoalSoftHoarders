package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.RoadRunner.Functionalities.PoseStorage;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;

@Config
public class A implements AutoCase {

    public static double firstWobbleX = -70f;
    public static double firstWobbleY = 95f;
    public static double firstWobbleHeading = 180f;

    public static double secondWobbleX = 10f;
    public static double secondWobbleY = 125f;
    public static double secondWobbleHeading = 270f;

    private final LinearOpMode opMode;

    @Override
    public void releaseFirstWobble() {
        drive.followTrajectory(Trajectories.firstWobbleReleaseA(drive.getPoseEstimate()));
        Wobble.motorArmToPosition(true, wobbleMidPosition);
        opMode.sleep(wobbleArmSleep);
        Wobble.wobbleRelease();
        opMode.sleep(wobbleArmSleep);
    }

    @Override
    public void IntakeShoot() {

    }

    @Override
    public void releaseSecondWobble() {
        Wobble.motorArmToPosition(true, wobble2ArmPosition);
        Hardware.wall_right.setPosition(AutoUtil.rightWallDown);
        drive.followTrajectory(Trajectories.secondWobbleReleaseA(drive.getPoseEstimate()));
        Wobble.wobbleRelease();
    }


    public A(LinearOpMode opMode) {
        this.opMode = opMode;
    }

}

package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoIntakeShoot;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;

@Config
public class C implements AutoCase {

    public static double firstWobbleX = -157f; // -160f
    public static double firstWobbleY = 100f;
    public static double firstWobbleHeading = 180f;

    public static double secondWobbleX = -105f;
    public static double secondWobbleY = 135f;
    public static double secondWobbleHeading = 270f;

    private final LinearOpMode opMode;

    @Override
    public void releaseFirstWobble() {
        drive.followTrajectory(Trajectories.firstWobbleReleaseC(drive.getPoseEstimate()));
        Wobble.wobbleRelease();
        //opMode.sleep(wobbleArmSleep);
    }

    @Override
    public void IntakeShoot() {
        Wobble.motorArmToPosition(true, wobble2ArmPosition); //Prepare for intake
        AutoIntakeShoot.start(opMode);
    }

    @Override
    public void releaseSecondWobble() {
        Hardware.wall_left.setPosition(AutoUtil.leftWallDown);
        drive.followTrajectory(Trajectories.secondWobbleReleaseC(drive.getPoseEstimate()));
        Wobble.wobbleRelease();
    }

    public C(LinearOpMode opMode) {
        this.opMode = opMode;
    }
}

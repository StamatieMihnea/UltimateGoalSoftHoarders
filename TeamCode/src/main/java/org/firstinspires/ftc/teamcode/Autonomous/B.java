package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoIntakeShoot;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.AutoUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Utils.Trajectories;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;

@Config
public class B implements AutoCase {

    public static double firstWobbleX = -120f;
    public static double firstWobbleY = 60f;
    public static double firstWobbleHeading = 200f;

    public static double secondWobbleX = -70f;
    public static double secondWobbleY = 75f;
    public static double secondWobbleHeading = 300f;

    private final LinearOpMode opMode;

    @Override
    public void releaseFirstWobble() {
        drive.followTrajectory(Trajectories.firstWobbleReleaseB(drive.getPoseEstimate()));
        Wobble.wobbleRelease();
        opMode.sleep(wobbleArmSleep);
    }

    @Override
    public void IntakeShoot() {
        Wobble.motorArmToPosition(true, wobble2ArmPosition); //Prepare for intake
        AutoIntakeShoot.start(opMode);
    }

    @Override
    public void releaseSecondWobble() {
        Hardware.wall_left.setPosition(AutoUtil.leftWallDown);
        drive.followTrajectory(Trajectories.secondWobbleReleaseB(drive.getPoseEstimate()));
        Wobble.wobbleRelease();
        Hardware.wall_left.setPosition(AutoUtil.leftWallDown);
    }


    public B(LinearOpMode opMode) {
        this.opMode = opMode;
    }
}

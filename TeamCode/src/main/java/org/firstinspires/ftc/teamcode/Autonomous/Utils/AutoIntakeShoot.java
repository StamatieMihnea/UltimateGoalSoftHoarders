package org.firstinspires.ftc.teamcode.Autonomous.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.AutoCase;

import static org.firstinspires.ftc.teamcode.Autonomous.Initializations.drive;

public class AutoIntakeShoot {
    public static void start(LinearOpMode opMode) {
        AutoUtil.startIntake();
        drive.followTrajectory(Trajectories.diskCollect(drive.getPoseEstimate()));

        drive.followTrajectory(Trajectories.shootPoseTrajectory(drive.getPoseEstimate()));

        AutoUtil.startShooting(AutoCase.shootSpeed);
        opMode.sleep(200);
        AutoUtil.shoot3Disks();
    }
}

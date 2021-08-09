package com.example.trajectoryvisualizer;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;

public class VisualizerTest {
    public static void main(String[] args) {
        Pose2d startPose = new Pose2d(-61.5, -14.6, Math.toRadians(180));
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        Pose2d powerShotsPose1 = new Pose2d(40.5, 33.46, Math.toRadians(196));
        Pose2d powerShotsPose2 = new Pose2d(40.5, 33.46, Math.toRadians(192));
        Pose2d powerShotsPose3 = new Pose2d(40.5, 33.46, Math.toRadians(187));

        MeepMeep mm = new MeepMeep(800)
                // Set field image
                .setBackground(MeepMeep.Background.FIELD_ULTIMATE_GOAL_DARK)
                // Set theme
                .setTheme(new ColorSchemeRedDark())
                // Background opacity from 0-1
                .setBackgroundAlpha(1f)

                // Set constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 40, 10, Math.toRadians(348.4202808227979), 14.88)
                .setStartPose(new Pose2d(-61.5, -14.6, Math.toRadians(180)))
                .followTrajectorySequence(drive ->
                        {
                            return drive.trajectorySequenceBuilder(new Pose2d(-61.5, -33.46, Math.toRadians(180)))
                                    .lineToSplineHeading(powerShotsPose1)
                                    .waitSeconds(0.5)
                                    .splineToSplineHeading(powerShotsPose2, Math.toRadians(0))
                                    .waitSeconds(0.5)
                                    .splineToSplineHeading(powerShotsPose3, Math.toRadians(0))
                                    .waitSeconds(0.5)
                                    //Error: User code threw an uncaught exception: PathContinuityViolationException
                                    //.lineToSplineHeading(new Pose2d(10, 36, java.lang.Math.toRadians(180)))
                                    //.splineToConstantHeading(new Vector2d(38, 21), java.lang.Math.toRadians(20))

                                    //.waitSeconds(0.2)


//                                    .lineToConstantHeading(new Vector2d(-39, -40))
//                                    .lineToLinearHeading(new Pose2d(-20, -35, Math.toRadians(200)))

                                    //.strafeTo(new Vector2d(CmToInch.convert(0), CmToInch.convert(-12)))
                                    // .strafeLeft(100)
                                    .build();
                        }


                ).start();


    }
}

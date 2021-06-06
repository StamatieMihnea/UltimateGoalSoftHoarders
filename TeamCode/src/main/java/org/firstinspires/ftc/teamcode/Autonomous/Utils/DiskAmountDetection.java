package org.firstinspires.ftc.teamcode.Autonomous.Utils;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.MainAuto;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;


public class DiskAmountDetection {

    public static double pointDiff = 0f;

    public static void stopDetection() {
        Hardware.cvCamera.stopStreaming();
    }


    public static int getDiskAmount(double redAmount) {
        if (redAmount < 123) {
            return 0;
        } else if (redAmount < 128) {
            return 1;
        } else {
            return 4;
        }
    }
    // 0 -- 112-111

    // 1 -- 115

    // 4 -- 123 - 124

    public static class UltimateGoalPipeline extends OpenCvPipeline {

        Telemetry telemetry;
        boolean update;

        public UltimateGoalPipeline(Telemetry telemetry, boolean update) {
            this.telemetry = telemetry;
            this.update = update;
        }

        final Scalar BLUE = new Scalar(0, 0, 255);

        @Override
        public Mat processFrame(Mat input) {

            Mat RedMat = new Mat();
            Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(input, RedMat, 1);
            //Imgproc.cvtColor(input,input,Imgproc.COLOR_RGB2GRAY );
            //Imgproc.Canny(input, input, 60, 60*3);
            //Core.extractChannel(input, RedMat, 1);

            int n = RedMat.width(), m = RedMat.height();
            //    telemetry.addData("mat size is: ", n + " " + m);
            // Point firstPoint = new Point(n / 2 - 20 + pointDiff, m / 2 - 10 + pointDiff),
            //       secondPoint = new Point(n / 2 + 20 + pointDiff, m / 2 + 30 + pointDiff);
            Point firstPoint = new Point(n / 2 - 35, m / 2 + 40), secondPoint = new Point(n / 2, m / 2);
            Mat finalMat = RedMat.submat(new Rect(firstPoint, secondPoint));


            Imgproc.rectangle(
                    RedMat,
                    firstPoint,
                    secondPoint,
                    BLUE,
                    1
            );
            double redAmount = 0f;

            redAmount = (int) Core.mean(finalMat).val[0];

            MainAuto.diskAmount = getDiskAmount(redAmount);

            telemetry.addData("The red amount is:", redAmount);
            telemetry.addData("The number of disks is:", MainAuto.diskAmount);
            if (update) {
                telemetry.update();
            }

            return RedMat;
        }
    }
}
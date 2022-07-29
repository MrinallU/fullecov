package org.firstinspires.ftc.teamcode.WebCam.Pipelines;

import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvPipeline;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class rectangle_thresholder_pipeline extends OpenCvPipeline {
  Telemetry telemetry;
  private String out;
  public Scalar lower = new Scalar(0, 0, 0);
  public Scalar upper = new Scalar(255, 255, 255);

  private boolean saveImg=false;
  private Mat hsvMat = new Mat();
  private Mat binaryMat = new Mat();
  private Mat maskedInputMat = new Mat();

  private Point topLeft1 = new Point(10, 0), bottomRight1 = new Point(40, 20);

  public rectangle_thresholder_pipeline(Telemetry telemetry) {
    this.telemetry = telemetry; saveImg=false;
  }

  public rectangle_thresholder_pipeline() {saveImg=false;}
  public rectangle_thresholder_pipeline(boolean s) {saveImg=s;}

  @Override
  public Mat processFrame(Mat input) {
    Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);
    Core.inRange(hsvMat, lower, upper, binaryMat);
    maskedInputMat.release();
    Core.bitwise_and(input, input, maskedInputMat, binaryMat);

    double w1 = 0, w2 = 0;
    // process the pixel value for each rectangle  (255 = W, 0 = B)
    for (int i = (int) topLeft1.x; i <= bottomRight1.x; i++) {
      for (int j = (int) topLeft1.y; j <= bottomRight1.y; j++) {
        if (binaryMat.get(i, j)[0] == 255) {
          w1++;
        }
      }
    }

    if (w1 > w2) {
      telemetry.addLine("rect#1");
      out = "1";
    } else if (w1 < w2) {
      telemetry.addLine("rect#2");
      out = "2";
    } else {
      telemetry.addLine("BOTH");
      out = "3";
    }

    telemetry.update();

    if(saveImg) {
      //saveMatToDisk(input, "rect_manual_img");
      saveImg=false;
    }

    return binaryMat;
  }

  public String getOut() {
    return out;
  }
}

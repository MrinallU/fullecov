package org.firstinspires.ftc.teamcode.WebCam.Pipelines.Helpers;

public class VisionObject {
  public int left, right, top, bottom, centerX, centerY;
  public double sx = 1, sy, yintx = 1, yinty, inchX, inchY;

  public VisionObject(int l, int r, int b, int t) {
    left = l;
    right = r;
    bottom = b;
    top = t;
    centerX = (left + right) / 2;
    centerY = (top + bottom) / 2;
    inchX =
        (sx * centerX)
            + yintx; // drag horizontally and record (centerX , inches from the camera horizontally)
    inchY =
        (sy * centerY)
            + yinty; // drag vertically and record (centerY, inches from the camera vertically)
    // input into linear regression calc to get slope and y int for each curve.
  }
}

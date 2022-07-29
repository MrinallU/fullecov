package org.firstinspires.ftc.teamcode.WebCam;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.WebCam.Pipelines.Helpers.VisionObject;

import java.util.ArrayList;

@TeleOp(name = "Ecov", group = "robot")
public class Test_teleop extends LinearOpMode {
  Camera camera = new Camera(hardwareMap);

  @Override
  public void runOpMode() throws InterruptedException {
    camera.switchToFFPipleine();
    telemetry.addLine("Status: Initialized");
    waitForStart();

    while(opModeIsActive()){
      ArrayList<VisionObject> objects = camera.getObjects();
      if (objects.size() > 0) {
        VisionObject o = objects.get(0);
        telemetry.addLine("Pixel coords (x,y) " + o.centerX + " " + o.centerY);
        telemetry.addLine("Inch coords (x,y) " + o.inchX + " " + o.inchY);
      }
    }
  }
}

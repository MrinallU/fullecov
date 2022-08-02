package org.firstinspires.ftc.teamcode.WebCam;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.WebCam.Pipelines.Helpers.VisionObject;

import java.util.ArrayList;
import java.util.Collections;

@TeleOp(name = "Ecov", group = "robot")
public class Test_teleop extends LinearOpMode {

  @Override
  public void runOpMode() throws InterruptedException {
    Camera camera = new Camera(hardwareMap);
    camera.switchToFFPipleine();
    telemetry.addLine("Status: Initialized");
    telemetry.update();
    waitForStart();

    while(opModeIsActive()){
      ArrayList<VisionObject> objects = camera.getObjects();
      try {
        if (objects.size() > 0) {
          Collections.sort(objects);
          VisionObject o = objects.get(objects.size()-1);
          if(o!=null) {
            telemetry.addLine("Pixel coords (x,y) " + o.centerX + " " + o.centerY);
            telemetry.addLine("Inch coords (x,y) " + o.inchX + " " + o.inchY);
          }
        }
      }catch (Exception e){
        telemetry.addLine("No Object Found");
      }
      telemetry.update();
    }
  }
}

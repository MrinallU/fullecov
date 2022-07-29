 package org.firstinspires.ftc.teamcode.WebCam;

 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
 import com.qualcomm.robotcore.hardware.HardwareMap;

 import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
 import org.firstinspires.ftc.teamcode.WebCam.Pipelines.Helpers.VisionObject;
 import org.firstinspires.ftc.teamcode.WebCam.Pipelines.auto_floodfill_detection;
 import org.firstinspires.ftc.teamcode.WebCam.Pipelines.rectangle_thresholder_pipeline;
 import org.openftc.easyopencv.OpenCvCamera;
 import org.openftc.easyopencv.OpenCvCameraFactory;
 import org.openftc.easyopencv.OpenCvCameraRotation;
 import org.openftc.easyopencv.OpenCvWebcam;

 import java.util.ArrayList;

 @TeleOp
 public class Camera {
    private OpenCvWebcam webcam;
    private HardwareMap hardwareMap;
    private rectangle_thresholder_pipeline p1;
    private auto_floodfill_detection p2;

    public Camera(HardwareMap hw){
        p1 = new rectangle_thresholder_pipeline();
        p2 = new auto_floodfill_detection();

        this.hardwareMap=hw;
        int cameraMonitorViewId =
 hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id",
 hardwareMap.appContext.getPackageName());

        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class,
 "camera"), cameraMonitorViewId);
        webcam.setPipeline(p1);
        webcam.setMillisecondsPermissionTimeout(2500);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()  {
            @Override
            public void onOpened() {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
  }

  public void switchToFFPipleine() {
    webcam.setPipeline(p2);
  }

  public ArrayList<VisionObject> getObjects() {
    return p2.objs;
  }

  public void stop() {
    webcam.stopStreaming();
  }
}

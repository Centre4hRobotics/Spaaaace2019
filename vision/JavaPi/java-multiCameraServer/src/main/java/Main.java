
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.vision.VisionPipeline;
import edu.wpi.first.vision.VisionThread;

import org.opencv.core.Mat;
import org.opencv.core.*;
import org.opencv.imgproc.*;

/*
   JSON format:
   {
       "team": <team number>,
       "ntmode": <"client" or "server", "client" if unspecified>
       "cameras": [
           {
               "name": <camera name>
               "path": <path, e.g. "/dev/video0">
               "pixel format": <"MJPEG", "YUYV", etc>   // optional
               "width": <video mode width>              // optional
               "height": <video mode height>            // optional
               "fps": <video mode fps>                  // optional
               "brightness": <percentage brightness>    // optional
               "white balance": <"auto", "hold", value> // optional
               "exposure": <"auto", "hold", value>      // optional
               "properties": [                          // optional
                   {
                       "name": <property name>
                       "value": <property value>
                   }
               ],
               "stream": {                              // optional
                   "properties": [
                       {
                           "name": <stream property name>
                           "value": <stream property value>
                       }
                   ]
               }
           }
       ]
   }
 */

public final class Main {
  private static String configFile = "/boot/frc.json";

  @SuppressWarnings("MemberName")
  public static class CameraConfig {
    public String name;
    public String path;
    public JsonObject config;
    public JsonElement streamConfig;
  }

  public static int team;
  public static boolean server;
  public static List<CameraConfig> cameraConfigs = new ArrayList<>();

  private Main() {
  }

  /**
   * Report parse error.
   */
  public static void parseError(String str) {
    System.err.println("config error in '" + configFile + "': " + str);
  }

  /**
   * Read single camera configuration.
   */
  public static boolean readCameraConfig(JsonObject config) {
    CameraConfig cam = new CameraConfig();

    // name
    JsonElement nameElement = config.get("name");
    if (nameElement == null) {
      parseError("could not read camera name");
      return false;
    }
    cam.name = nameElement.getAsString();

    // path
    JsonElement pathElement = config.get("path");
    if (pathElement == null) {
      parseError("camera '" + cam.name + "': could not read path");
      return false;
    }
    cam.path = pathElement.getAsString();

    // stream properties
    cam.streamConfig = config.get("stream");

    cam.config = config;

    cameraConfigs.add(cam);
    return true;
  }

  /**
   * Read configuration file.
   */
  @SuppressWarnings("PMD.CyclomaticComplexity")
  public static boolean readConfig() {
    // parse file
    JsonElement top;
    try {
      top = new JsonParser().parse(Files.newBufferedReader(Paths.get(configFile)));
    } catch (IOException ex) {
      System.err.println("could not open '" + configFile + "': " + ex);
      return false;
    }

    // top level must be an object
    if (!top.isJsonObject()) {
      parseError("must be JSON object");
      return false;
    }
    JsonObject obj = top.getAsJsonObject();

    // team number
    JsonElement teamElement = obj.get("team");
    if (teamElement == null) {
      parseError("could not read team number");
      return false;
    }
    team = teamElement.getAsInt();

    // ntmode (optional)
    if (obj.has("ntmode")) {
      String str = obj.get("ntmode").getAsString();
      if ("client".equalsIgnoreCase(str)) {
        server = false;
      } else if ("server".equalsIgnoreCase(str)) {
        server = true;
      } else {
        parseError("could not understand ntmode value '" + str + "'");
      }
    }

    // cameras
    JsonElement camerasElement = obj.get("cameras");
    if (camerasElement == null) {
      parseError("could not read cameras");
      return false;
    }
    JsonArray cameras = camerasElement.getAsJsonArray();
    for (JsonElement camera : cameras) {
      if (!readCameraConfig(camera.getAsJsonObject())) {
        return false;
      }
    }

    return true;
  }

  /**
   * Start running the camera.
   */
  public static VideoSource startCamera(CameraConfig config) {
    System.out.println("Starting camera '" + config.name + "' on " + config.path);
    CameraServer inst = CameraServer.getInstance();
    UsbCamera camera = new UsbCamera(config.name, config.path);
    MjpegServer server = inst.startAutomaticCapture(camera);

    Gson gson = new GsonBuilder().create();

    camera.setConfigJson(gson.toJson(config.config));
    camera.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);

    if (config.streamConfig != null) {
      server.setConfigJson(gson.toJson(config.streamConfig));
    }

    return camera;
  }

  /**
   * Example pipeline.
   */
  public static class CargoPipeline implements VisionPipeline {
    public int val;
    private GripPipelineCargoII pipelineCargo = new GripPipelineCargoII();
    private NetworkTableInstance ntinst = null;

    public static final double PIXELS_ACROSS = 320;
    public static final double TARGET_AREA_CARGO = 60000;

    public CargoPipeline(NetworkTableInstance ntinst) {
      this.ntinst = ntinst;
    }

    public void publishCargo (ArrayList<MatOfPoint> contours) {
      int foundContour = 0;
      double xCenter = PIXELS_ACROSS/2, area = 0.0;
      
      if (contours.size() > 0) {
        foundContour = 1;
        double aMax = 0.0;
        int index = 0;
        Moments k = null;
        for (int i = 0; i<contours.size(); i++) {
          k = Imgproc.moments(contours.get(i));
          if (k.get_m00()>aMax) {
            aMax = k.get_m00();
            index = i;
          }
        }
        Moments m = Imgproc.moments(contours.get(index));
        xCenter = m.get_m10()/m.get_m00();
        area = m.get_m00();
      }
      ntinst.getTable("Datatable").getEntry("Found Contour").setNumber(foundContour);
      ntinst.getTable("Datatable").getEntry("XCenter").setNumber(xCenter);
      ntinst.getTable("Datatable").getEntry("Area").setNumber(area);
      ntinst.getTable("Datatable").getEntry("Steer").setNumber(2*xCenter/PIXELS_ACROSS-1.0);
      double speed = 1-area/TARGET_AREA_CARGO;
      if(speed < 0) speed = 0;
      if (area == 0) speed = 0;
      ntinst.getTable("Datatable").getEntry("Speed").setNumber(speed);
    }

    @Override
    public void process(Mat mat) {
      val += 1;
      //System.out.println("Process begin");
      pipelineCargo.process(mat);
      
      ntinst.getTable("TestTable").getEntry("iteration cargo").setNumber(val);
      publishCargo(pipelineCargo.filterContoursOutput());
      /*
      System.out.println("Drawing Line");
      Imgproc.line(mat, new Point(160, 1), new Point(160, 239), new Scalar(0, 255, 0), 3);
      System.out.println("Line Drawn");
      */
    }

    /*public ArrayList<MatOfPoint> filterContoursOutputCargo() {
      return pipelineCargo.filterContoursOutput();
    }

    public ArrayList<MatOfPoint> filterContoursOutputTarget() {
      return pipelineTarget.filterContoursOutput();
    }*/
  }

  public static class TargetPipeline implements VisionPipeline {
    public int val;
    private GripPipelineTarget pipelineTarget = new GripPipelineTarget();
    private NetworkTableInstance ntinst = null;

    public static final double PIXELS_ACROSS = 320;
    public static final double TARGET_AREA_TARGET = 3000;

    public TargetPipeline(NetworkTableInstance ntinst) {
      this.ntinst = ntinst;
    }

    public void publishTargets (ArrayList<MatOfPoint> contours) {
      int foundContour = contours.size();
      double xCenter = PIXELS_ACROSS/2, area1 = 0.0, area2 = 0.0, xCenter1 = 0.0, xCenter2 = 0.0;
      if (foundContour == 1) {
        area1 = Imgproc.moments(contours.get(0)).get_m00();
      } else if (foundContour > 1) {
        int index1 = 0, index2 = 0;
        Moments k = null;
        for (int i = 0; i<contours.size(); i++) {
          k=Imgproc.moments(contours.get(i));
          if (k.get_m00()>area1) {
            area2 = area1;
            area1 = k.get_m00();
            index2 = index1;
            index1 = i;
          } else if (k.get_m00()>area2) {
            area2 = k.get_m00();
            index2 = i;
          }
        }
        Moments m1 = Imgproc.moments(contours.get(index1)), m2 = Imgproc.moments(contours.get(index2));
        xCenter1 = m1.get_m10();
        xCenter2 = m2.get_m10();
        xCenter = (xCenter1/area1+xCenter2/area2)*0.5;
      }
      ntinst.getTable("Vision Targets").getEntry("Contours Found").setNumber(foundContour);
      ntinst.getTable("Vision Targets").getEntry("XCenter").setNumber(xCenter);
      ntinst.getTable("Vision Targets").getEntry("XCenter1").setNumber(xCenter1/area1);
      ntinst.getTable("Vision Targets").getEntry("XCenter2").setNumber(xCenter2/area2);
      ntinst.getTable("Vision Targets").getEntry("Area1").setNumber(area1);
      ntinst.getTable("Vision Targets").getEntry("Area2").setNumber(area2);
      ntinst.getTable("Vision Targets").getEntry("Steer").setNumber(2*xCenter/PIXELS_ACROSS-1.0);
      double speed = 1-area1/TARGET_AREA_TARGET;
      if(speed < -1) speed = -1;
      if (area1 == 0 || area2 == 0) speed = 0;
      ntinst.getTable("Vision Targets").getEntry("Speed").setNumber(speed);
    }

    @Override
    public void process(Mat mat) {
      val += 1;
      //System.out.println("Process begin");
      pipelineTarget.process(mat);
      ntinst.getTable("TestTable").getEntry("iteration target").setNumber(val);
      publishTargets(pipelineTarget.filterContoursOutput());      /*
      System.out.println("Drawing Line");
      Imgproc.line(mat, new Point(160, 1), new Point(160, 239), new Scalar(0, 255, 0), 3);
      System.out.println("Line Drawn");
      */
    }

    /*public ArrayList<MatOfPoint> filterContoursOutputCargo() {
      return pipelineCargo.filterContoursOutput();
    }

    public ArrayList<MatOfPoint> filterContoursOutputTarget() {
      return pipelineTarget.filterContoursOutput();
    }*/
  }

  /**
   * Main.
   */
  public static void main(String... args) {
    if (args.length > 0) {
      configFile = args[0];
    }

    // read configuration
    if (!readConfig()) {
      return;
    }
    System.out.println("Team 4027 Vision Code Starting.");
    // start NetworkTables
    NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
    if (server) {
      System.out.println("Setting up NetworkTables server");
      ntinst.startServer();
    } else {
      System.out.println("Setting up NetworkTables client for team " + team);
      ntinst.startClientTeam(team);
    }

    // start cameras
    List<VideoSource> cameras = new ArrayList<>();
    for (CameraConfig cameraConfig : cameraConfigs) {
      cameras.add(startCamera(cameraConfig));
    }

    // start image processing on camera 0 if present
    if (cameras.size() >= 1) {
      VisionThread visionThreadTarget = new VisionThread(cameras.get(0),
              new TargetPipeline(ntinst), pipeline -> {});
        // do something with pipeline results
      //});
      // something like this for GRIP:
      VisionThread visionThreadCargo = new VisionThread(cameras.get(1),
              new CargoPipeline(ntinst), pipeline -> {
       /*pipelineCargo.filterContoursOutputCargo();
       pipelineTarget.filterContoursOutputTarget();*/
      });
    
      visionThreadCargo.start();
      visionThreadTarget.start();
    }

    // loop forever
    for (;;) {
      try {
        Thread.sleep(10000);
      } catch (InterruptedException ex) {
        return;
      }
    }
  }
}

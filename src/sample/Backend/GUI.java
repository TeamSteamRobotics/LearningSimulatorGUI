package sample.Backend;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application {
    
    public static Group root = new Group();

    private static String simState = "Disabled";
    private static AnimationTimer simLoop;

    private static ParallelCamera sceneCamera;

    public static Scene currentScene = new Scene(root, 1000, 800, Color.BEIGE);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Robot Simulation");
        primaryStage.setScene(currentScene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();

        currentScene.setCamera(sceneCamera);

        DriverStation.launch();

        simInit();
        simLoop();
    }

    private void simInit() {
        GUIRobot.createRobot();
        Environment.createEnviornment();
    }

    private int counter;
    private void simLoop() {

        //Timer runs constantly

        simLoop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long frameTime) {
                if (frameTime - lastUpdate >= (50_000_000)) {
                    /*Server.updateXYRot();
                    GUIRobot.updateRobot((int) Server.getX(), (int) Server.getY(), (int) Server.getRot());*/
                		GUIRobot.updateRobot(5, (int) 5, (int) 90);
                		
                    counter++;


                    lastUpdate = frameTime;
                }
            }
        };
    }

    public static void enable() {
        if (simState.equals("Enabled"))
            return;
        else {
            simState = "Enabled";
            simLoop.start();
        }

    }

    public static void disable() {
        if (simState.equals("Disabled"))
            return;
        else {
            System.out.println("Stopped");
            simState = "Disabled";
            simLoop.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

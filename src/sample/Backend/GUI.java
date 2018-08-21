package sample.Backend;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application {
    
    public static Group root = new Group();

    private static String simState = "Disabled";
    private static AnimationTimer simLoop;

    public static Scene currentScene = new Scene(root, 1000, 800, Color.BEIGE);

    double x;
    double y;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Robot Simulation");
        primaryStage.setScene(currentScene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();

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


        currentScene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                x = event.getSceneX();
                y = event.getSceneY();
            }
        });
        //Timer runs constantly

        simLoop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long frameTime) {
                if (frameTime - lastUpdate >= (50_000_000)) {
                    //System.out.println("Running");
                    GUIRobot.updateRobot((int) x, (int) y, 90);
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

package sample.Backend;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Contains, creates, or calls to create any GUI related thing. Contains the main loop that runs while the robot is
 * enabled.
 */
public class GUI extends Application {

    // The root node for the simulation scene
    public static Group root = new Group();

    // The robot can be either "Disabled" or "Enabled"
    private static String simState = "Disabled";
    private static AnimationTimer simLoop;

    // The camera that is set to follow the robot
    private static ParallelCamera sceneCamera = new ParallelCamera();

    // The scene for the simulation
    public static Scene currentScene = new Scene(root, 1000, 800, Color.BEIGE);

    /**
     * Is called whenever the main method is ran. Sets up the simulation window, calls for the driver station to start,
     * then calls the init methods.
     * @param primaryStage The stage that is set to the simulation window
     */
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

    /**
     * Calls the necessary init methods for the robot, the environment and the server
     */
    private void simInit() {
        GUIRobot.createRobot();
        Environment.createEnviornment();
        Server.tryConnect();
        Input.userInputInit();
    }

    /**
     * The main simulation loop that runs at all times that the robot is enabled. Gets coords from the server, then
     * updates the robot and camera accordingly
     */
    private void simLoop() {

        //Timer runs constantly
        simLoop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long frameTime) {
                if (frameTime - lastUpdate >= (50_000_000)) {
                    if (Server.connected) {

                        Server.updateXYRot();
                        GUIRobot.updateRobot(Server.getX(), Server.getY(), Server.getRot());
                        Server.sendRobotState(Input.getLeftWheelDirection(), Input.getRightWheelDirection(), DriverStation.robotState);

                        updateCamera();
                        System.out.println(DriverStation.robotState);
                    }
                    else {
                        Server.tryConnect();
                    }


                    lastUpdate = frameTime;
                }
            }
        };
    }

    /**
     * Starts the simLoop from a stopped state. Called from the operationModeGroup in DriverStation.java
     */
    public static void enable() {
        if (simState.equals("Enabled"))
            return;
        else {
            simState = "Enabled";
            simLoop.start();
        }
    }

    /**
     * Stop the simLoop from a started state. Called from the operationModeGroup in DriverStation.java
     */
    public static void disable() {
        if (simState.equals("Disabled"))
            return;
        else {
            System.out.println("Stopped");
            simState = "Disabled";
            simLoop.stop();
        }
    }

    /**
     * Updates the XY coords of the camera to keep centered on the robot
     */
    private static void updateCamera() {
        sceneCamera.setLayoutX(GUIRobot.frontOfRobot.getX() - (currentScene.getWidth() / 2));
        sceneCamera.setLayoutY(GUIRobot.frontOfRobot.getY() - (currentScene.getHeight() / 2));
    }

    /**
     * The method that is ran after clicking the run button. Starts the javaFX application by calling launch(args).
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

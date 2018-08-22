package sample.Backend;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Contains the creation and the updates of the robot within the simulation. Used by GUI.java
 */
public class GUIRobot {

    // The robot node and its image
    public static ImageView robot;
    private static Image robotImage;

    // The point that is always updated to be the relative front center of the robot
    public static Point2D frontOfRobot;

    // The amount of pixels that the robot moves towards its destination each tick. (10 is a good balance)
    private static int robotSpeed = 10;

    // xDestination and yDestination are set to the point to which the robot is traveling.
    private static double xDestination;
    private static double yDestination;

    // Is set to the rotation that the robot is trying to achieve.
    private static double rotDestination;

    /**
     * Imports the robot's image and creates the robot from it. Centers the robot then adds it to the root node to be
     * drawn.
     */
    public static void createRobot() {
        try {
            robotImage = new Image(new FileInputStream("res/robot.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        robot = new ImageView(robotImage);
        robot.setLayoutX((GUI.currentScene.getWidth() / 2) - (100));
        robot.setLayoutY((GUI.currentScene.getHeight() / 2) - (100));
        GUI.root.getChildren().addAll(robot);
    }

    /**
     * Updates the robot according to x y and rot values acquired from the server by moving the robot towards xDirection
     * and yDirection.
     * @param x The x change from the robot's current position
     * @param y The y change from the robot's current position
     * @param rot The rotation change from the robot's current position
     */
    public static void updateRobot(double x, double y, double rot) {
        frontOfRobot = robot.localToParent(100, 0);

        if (frontOfRobot.getX() != x) 
        		xDestination = frontOfRobot.getX() + x;
        if (frontOfRobot.getX() != x) 
    			yDestination = frontOfRobot.getY() + y;
        if (robot.getRotate() != rot) {
        		rotDestination = robot.getRotate() + rot;
        }
    
        // X position
        if(frontOfRobot.getX() != xDestination) {
            if (frontOfRobot.getX() < xDestination)
                robot.setLayoutX(robot.getLayoutX() + robotSpeed);
            else
                robot.setLayoutX(robot.getLayoutX() - robotSpeed);
        }

        // Y position
        if(frontOfRobot.getY() != yDestination) {
            if (frontOfRobot.getY() < yDestination)
                robot.setLayoutY(robot.getLayoutY() + robotSpeed);
            else
                robot.setLayoutY(robot.getLayoutY() - robotSpeed);
        }

        // Rotation angle
        if(robot.getRotate() != rotDestination) {
        		if (robot.getRotate() < rotDestination)
        			robot.setRotate(robot.getRotate() + robotSpeed);
        		else
        			robot.setRotate(robot.getRotate() - robotSpeed);
        }
    }
}

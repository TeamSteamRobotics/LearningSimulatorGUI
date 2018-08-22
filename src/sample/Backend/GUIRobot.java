package sample.Backend;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GUIRobot {

    public static ImageView robot;
    private static Image robotImage;

    private static Point2D frontOfRobot;

    private static int robotSpeed = 10;
    
    static double xDestination;
    static double yDestination;
    static double rotDestination;

    public static void createRobot() {
        try {
            robotImage = new Image(new FileInputStream("/Users/10200126/eclipse-workspace/LearningGUI/LearningSimulatorGUI/res/robot.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        robot = new ImageView(robotImage);
        robot.setLayoutX((GUI.currentScene.getWidth() / 2) - (100));
        robot.setLayoutY((GUI.currentScene.getHeight() / 2) - (100));
        GUI.root.getChildren().addAll(robot);
    }

    public static void updateRobot(int x, int y, int rot) {
        frontOfRobot = robot.localToParent(100, 0);

        if (frontOfRobot.getX() != x) 
        		xDestination = frontOfRobot.getX() + x;
        if (frontOfRobot.getX() != x) 
    			yDestination = frontOfRobot.getY() + y;
        if (robot.getRotate() != rot) {
        		rotDestination = robot.getRotate() + rot;
        }
    
        
        if(frontOfRobot.getX() != xDestination) {
            if (frontOfRobot.getX() < xDestination)
                robot.setLayoutX(robot.getLayoutX() + robotSpeed);
            else
                robot.setLayoutX(robot.getLayoutX() - robotSpeed);
        }

        if(frontOfRobot.getY() != yDestination) {
            if (frontOfRobot.getY() < yDestination)
                robot.setLayoutY(robot.getLayoutY() + robotSpeed);
            else
                robot.setLayoutY(robot.getLayoutY() - robotSpeed);
        }
        
        if(robot.getRotate() != rotDestination) {
        		if (robot.getRotate() < rotDestination)
        			robot.setRotate(robot.getRotate() + robotSpeed);
        		else
        			robot.setRotate(robot.getRotate() - robotSpeed);
        }

    }

}

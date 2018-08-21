package sample.Backend;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GUIRobot {

    public static ImageView robot;
    private static Image robotImage;

    private static Point2D frontOfRobot;
    private static Line pathingLine = new Line(125, 125, 125, 25);
    private static boolean correcting = true;

    public static void createRobot() {
        try {
            robotImage = new Image(new FileInputStream("res\\robot.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        robot = new ImageView(robotImage);
        robot.setLayoutX((GUI.currentScene.getWidth() / 2) - (100));
        robot.setLayoutY((GUI.currentScene.getHeight() / 2) - (100));
        GUI.root.getChildren().addAll(robot, pathingLine);
    }

    public static void updateRobot(int x, int y, int rot) {
        frontOfRobot = robot.localToParent(100, 100);
        robot.rotateProperty().addListener(o -> {
            pathingLine.setStartX(frontOfRobot.getX());
            pathingLine.setStartY(frontOfRobot.getY());
            pathingLine.setEndX(x);
            pathingLine.setEndY(y);
        });

        if (robot.getLayoutX() != x || robot.getLayoutY() != y) {
            if (x > robot.getLayoutX())
                robot.setLayoutX(robot.getLayoutX() + 5);
            else
                robot.setLayoutX(robot.getLayoutX() - 5);

            if (y > robot.getLayoutY())
                robot.setLayoutY(robot.getLayoutY() + 5);
            else
                robot.setLayoutY(robot.getLayoutY() - 5);
        }

        if ((getRobotAngle() - 2 <= getDestinationAngle()) && (getRobotAngle() + 2 >= getDestinationAngle()) || correcting) {
            if (getDestinationAngle() > getRobotAngle())
                robot.setRotate(robot.getRotate() + 3);
            else
                robot.setRotate(robot.getRotate() - 3);
        }
        else
            correcting = false;

        getDestinationAngle();
        getRobotAngle();

    }

    private static int getDestinationAngle() {
        double xDiff = pathingLine.getEndX() - frontOfRobot.getX();
        double yDiff = pathingLine.getEndY() - frontOfRobot.getY();
        double angle = Math.toDegrees(Math.atan2(yDiff, xDiff));
        angle = (int) Math.round(((angle) * 100.0) / 100) + 90;
        angle = angle < 0 ? angle + 360 : angle;
        return (int) angle;

    }
    private static int getRobotAngle() {
        double xx = robot.getLocalToSceneTransform().getMxx();
        double xy = robot.getLocalToSceneTransform().getMxy();
        double angle = Math.toDegrees(Math.atan2(-xy, xx));
        angle = angle < 0 ? angle + 360 : angle;
        return (int) Math.round(angle * 100.0) / 100;
    }
}

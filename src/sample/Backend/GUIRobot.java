package sample.Backend;

import javafx.animation.*;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GUIRobot {

    public static ImageView robot;
    private static Image robotImage;

    private static Point2D frontOfRobot;

    static Path path;

    private static int robotSpeed = 1;

    public static void createRobot() {
        try {
            robotImage = new Image(new FileInputStream("res\\robot.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        path = new Path();
        path.getElements().add(new MoveTo(200, 200));
        path.getElements().add(new CubicCurveTo(200, 200, 200, 200, 200, 200));

        robot = new ImageView(robotImage);
        robot.setLayoutX((GUI.currentScene.getWidth() / 2) - (100));
        robot.setLayoutY((GUI.currentScene.getHeight() / 2) - (100));
        GUI.root.getChildren().addAll(robot, path);
    }

    public static void updateRobot(int x, int y, int rot) {
        /*frontOfRobot = robot.localToParent(100, 0);

        double xDestination = frontOfRobot.getX() + x;
        double yDestination = frontOfRobot.getY() + y;


        if(frontOfRobot.getX() != xDestination) {
            if ((robot.getLayoutX() + 100) < xDestination)
                robot.setLayoutX(robot.getLayoutX() + robotSpeed);
            else
                robot.setLayoutX(robot.getLayoutX() - robotSpeed);
        }

        if(frontOfRobot.getY() != yDestination) {
            if (robot.getLayoutY() < yDestination)
                robot.setLayoutY(robot.getLayoutY() + robotSpeed);
            else
                robot.setLayoutY(robot.getLayoutY() - robotSpeed);
        }
*/
        final PathTransition pathTransition = new PathTransition();

        pathTransition.setDuration(Duration.seconds(8.0));
        pathTransition.setDelay(Duration.seconds(0.5));
        pathTransition.setPath(path);
        pathTransition.setNode(robot);
        pathTransition.setCycleCount(500);
        pathTransition.setOrientation(PathTransition.OrientationType.
                ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        /*RotateTransition rotateTransition = new RotateTransition(Duration.seconds(5), robot);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();*/
    }

}

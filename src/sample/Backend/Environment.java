package sample.Backend;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Creates any necessary in-simulation objects that are around the robot. Used by GUI.java
 */
public class Environment {

    /**
     * Creates any environment objects, then adds them to the root node to be drawn.
     */
    public static void createEnviornment() {
        Rectangle rock = new Rectangle(150, 150, 50, 50);
        rock.setFill(Color.GRAY);

        GUI.root.getChildren().addAll(rock);
    }
}

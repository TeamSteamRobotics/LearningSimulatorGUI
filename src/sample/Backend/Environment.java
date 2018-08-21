package sample.Backend;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Environment {

    public static void createEnviornment() {
        Rectangle rock = new Rectangle(150, 150, 50, 50);
        rock.setFill(Color.GRAY);
        GUI.root.getChildren().add(rock);
    }
}

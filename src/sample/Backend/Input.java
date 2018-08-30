package sample.Backend;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

class Input {

    private static int leftWheelDirection = 0;
    private static int rightWheelDirection = 0;

    static int getLeftWheelDirection() {
        return leftWheelDirection;
    }

    static int getRightWheelDirection() {
        return rightWheelDirection;
    }

    public static void userInputInit() {
        GUI.currentScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            KeyCode keyPressed = key.getCode();
            switch (keyPressed) {
                //WASD controls
                case W:
                    leftWheelDirection = 1;
                    rightWheelDirection = 1;
                    break;
                case A:
                    leftWheelDirection = -1;
                    rightWheelDirection = 1;
                    break;
                case S:
                    leftWheelDirection = -1;
                    rightWheelDirection = -1;
                    break;
                case D:
                    leftWheelDirection = 1;
                    rightWheelDirection = -1;
                    break;
            }
        });
    }
}

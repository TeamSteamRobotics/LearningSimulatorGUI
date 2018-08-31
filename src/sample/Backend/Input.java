package sample.Backend;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

class Input {

    private static int joystickX = 0;
    private static int joystickY = 0;

    static int getJoystickX() {
        return joystickX;
    }

    static int getJoystickY() {
        return joystickY;
    }

    public static void userInputInit() {
        GUI.currentScene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            KeyCode keyPressed = key.getCode();
            switch (keyPressed) {
                //WASD controls
                case W:
                    joystickX = 0;
                    joystickY = 1;
                    break;
                case A:
                    joystickX = -1;
                    joystickY = 0;
                    break;
                case S:
                    joystickX = 0;
                    joystickY = -1;
                    break;
                case D:
                    joystickX = 1;
                    joystickY = 0;
                    break;
            }
        });
    }
}

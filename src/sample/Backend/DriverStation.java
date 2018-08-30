package sample.Backend;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Creates and manages the driver station window that is started along side the robot simulation. Is used to disable/enable
 * the robot at any time. Also contains diagnostics information, network information, and robot information.
 */
class DriverStation {

    //The state that the robot is in. Either teleOp, autonomous, or disabled
    static String robotState = "teleOp";

    // The root node that every group is added to: to be drawn
    private static Group driverStationRoot = new Group();

    // The scene that the driver station sets its window to
    private static Scene driverStationScene = new Scene(driverStationRoot, 500, 200, Color.BLACK);

    // The groups of buttons that all represent different tabs within the driver station
    private static Group menuSelectionGroup = new Group();
    private static Group operationModeGroup = new Group();
    private static Group diagnosticsModeGroup = new Group();
    private static Group setupModeGroup = new Group();
    private static Group usbDeviceModeGroup = new Group();
    private static Group powerModeGroup = new Group();

    /**
     * Creates the driver station window and all the buttons within. Menu changes and updates are handled by EventHandlers
     * on buttons that change menus. Each group of buttons contains the button nodes themselves, the toggle group that
     * they are all apart of (to only allow the selection of one button at a time), and any needed EventHandlers.
     */
    public static void launch() {
        Stage driverStationWindow = new Stage();
        driverStationWindow.setTitle("Driver Station 1.0");
        driverStationWindow.setScene(driverStationScene);
        driverStationWindow.show();


        //MENU SELECTION BUTTONS
        ToggleGroup driverStationMenus = new ToggleGroup();
            ToggleButton operationModeButton = new ToggleButton("O");
            operationModeButton.setLayoutY(25);
            operationModeButton.setToggleGroup(driverStationMenus);

            operationModeButton.setOnAction(event -> {
                if (driverStationRoot.getChildren().get(1) != operationModeGroup)
                    replaceGroup(operationModeGroup);
            });

            ToggleButton diagnosticsModeButton = new ToggleButton("D");
            diagnosticsModeButton.setLayoutY(50);
            diagnosticsModeButton.setToggleGroup(driverStationMenus);

            diagnosticsModeButton.setOnAction(event -> {
                if (driverStationRoot.getChildren().get(1) != diagnosticsModeGroup)
                    replaceGroup(diagnosticsModeGroup);
            });

            ToggleButton setupModeButton = new ToggleButton("S");
            setupModeButton.setLayoutY(75);
            setupModeButton.setToggleGroup(driverStationMenus);

            setupModeButton.setOnAction(event -> {
                if (driverStationRoot.getChildren().get(1) != setupModeGroup)
                    replaceGroup(setupModeGroup);
            });

            ToggleButton usbDevicesModeButton = new ToggleButton("U");
            usbDevicesModeButton.setLayoutY(100);
            usbDevicesModeButton.setToggleGroup(driverStationMenus);

            usbDevicesModeButton.setOnAction(event -> {
                if (driverStationRoot.getChildren().get(1) != usbDeviceModeGroup)
                    replaceGroup(usbDeviceModeGroup);
            });

            ToggleButton powerModeButton = new ToggleButton("P");
            powerModeButton.setLayoutY(125);
            powerModeButton.setToggleGroup(driverStationMenus);

            powerModeButton.setOnAction(event -> {
                if (driverStationRoot.getChildren().get(1) != powerModeGroup)
                    replaceGroup(powerModeGroup);
            });


        menuSelectionGroup.getChildren().addAll(operationModeButton, diagnosticsModeButton, setupModeButton, usbDevicesModeButton, powerModeButton);

        //OPERATION MODE GROUP
        ToggleGroup robotModeSelection = new ToggleGroup();
            ToggleButton teleSelection = new ToggleButton("TeleOperated");
            teleSelection.setToggleGroup(robotModeSelection);
            teleSelection.setLayoutX(50);
            teleSelection.setSelected(true);

            teleSelection.setOnAction(event -> robotState = "teleOp");

            ToggleButton autonSelection = new ToggleButton("Autonomous");
            autonSelection.setLayoutX(150);
            autonSelection.setToggleGroup(robotModeSelection);

            autonSelection.setOnAction(event -> robotState = "autonomous");

            ToggleButton practiceSelection = new ToggleButton("Practice");
            practiceSelection.setLayoutX(250);
            practiceSelection.setToggleGroup(robotModeSelection);

        //TODO: Implement practice mode
        practiceSelection.setOnAction(event -> robotState = "teleOp");

            ToggleButton testSelection = new ToggleButton("Test");
            testSelection.setLayoutX(350);
            testSelection.setToggleGroup(robotModeSelection);

        //TODO: Implement test mode
        testSelection.setOnAction(event -> robotState = "teleOp");

        //ENABLE/DISABLE BUTTONS
        ToggleGroup robotEnableSelection = new ToggleGroup();
            ToggleButton disableButton = new ToggleButton("DISABLE");
            disableButton.setSelected(true);
            disableButton.setToggleGroup(robotEnableSelection);
            disableButton.relocate(200, 50);
            disableButton.setMinWidth(150);
            disableButton.setMaxWidth(150);
            disableButton.setMinHeight(100);
            disableButton.setMaxHeight(100);

            ToggleButton enableButton = new ToggleButton("ENABLE");
            enableButton.setToggleGroup(robotEnableSelection);
            enableButton.relocate(50, 50);
            enableButton.setMinWidth(150);
            enableButton.setMaxWidth(150);
            enableButton.setMinHeight(100);
            enableButton.setMaxHeight(100);

            enableButton.setOnAction(event -> {
                if (enableButton.isSelected())
                    GUI.enable();
                else {
                    disableButton.setSelected(true);
                    GUI.disable();
                }
            });

            disableButton.setOnAction(event -> {
                if (disableButton.isSelected())
                    GUI.disable();
                else {
                    enableButton.setSelected(true);
                    GUI.enable();
                }
            });


        operationModeGroup.getChildren().addAll(enableButton, disableButton, teleSelection, autonSelection, practiceSelection, testSelection);

        replaceGroup(operationModeGroup);


    }

    /**
     * Handles the switching of the button groups. Clears the old group and adds the new one in it's place.Called mostly
     * from button EventHandlers.
     * @param newGroup The new group for the root node to display.
     */
    private static void replaceGroup(Group newGroup) {
        driverStationRoot.getChildren().clear();
        driverStationRoot.getChildren().addAll(menuSelectionGroup, newGroup);
    }
}

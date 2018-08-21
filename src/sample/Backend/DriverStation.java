package sample.Backend;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class DriverStation {
    public static Group driverStationRoot = new Group();


    public static Scene driverStationScene = new Scene(driverStationRoot, 500, 200, Color.BLACK);

    static Group menuSelectionGroup = new Group();
    static Group operationModeGroup = new Group();
    static Group diagnosticsModeGroup = new Group();
    static Group setupModeGroup = new Group();
    static Group usbDeviceModeGroup = new Group();
    static Group powerModeGroup = new Group();



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

            operationModeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (driverStationRoot.getChildren().get(1) != operationModeGroup)
                        replaceGroup(operationModeGroup);
                }
            });

            ToggleButton diagnosticsModeButton = new ToggleButton("D");
            diagnosticsModeButton.setLayoutY(50);
            diagnosticsModeButton.setToggleGroup(driverStationMenus);

            diagnosticsModeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (driverStationRoot.getChildren().get(1) != diagnosticsModeGroup)
                        replaceGroup(diagnosticsModeGroup);
                }
            });

            ToggleButton setupModeButton = new ToggleButton("S");
            setupModeButton.setLayoutY(75);
            setupModeButton.setToggleGroup(driverStationMenus);

            setupModeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (driverStationRoot.getChildren().get(1) != setupModeGroup)
                        replaceGroup(setupModeGroup);
                }
            });

            ToggleButton usbDevicesModeButton = new ToggleButton("U");
            usbDevicesModeButton.setLayoutY(100);
            usbDevicesModeButton.setToggleGroup(driverStationMenus);

            usbDevicesModeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (driverStationRoot.getChildren().get(1) != usbDeviceModeGroup)
                        replaceGroup(usbDeviceModeGroup);
                }
            });

            ToggleButton powerModeButton = new ToggleButton("P");
            powerModeButton.setLayoutY(125);
            powerModeButton.setToggleGroup(driverStationMenus);

            powerModeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (driverStationRoot.getChildren().get(1) != powerModeGroup)
                        replaceGroup(powerModeGroup);
                }
            });


        menuSelectionGroup.getChildren().addAll(operationModeButton, diagnosticsModeButton, setupModeButton, usbDevicesModeButton, powerModeButton);

        //OPERATION MODE GROUP
        ToggleGroup robotModeSelection = new ToggleGroup();
            ToggleButton teleSelection = new ToggleButton("TeleOperated");
            teleSelection.setToggleGroup(robotModeSelection);
            teleSelection.setLayoutX(50);
            teleSelection.setSelected(true);

            ToggleButton autonSelection = new ToggleButton("Autonomous");
            autonSelection.setLayoutX(150);
            autonSelection.setToggleGroup(robotModeSelection);

            ToggleButton practiceSelection = new ToggleButton("Practice");
            practiceSelection.setLayoutX(250);
            practiceSelection.setToggleGroup(robotModeSelection);

            ToggleButton testSelection = new ToggleButton("Test");
            testSelection.setLayoutX(350);
            testSelection.setToggleGroup(robotModeSelection);

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

            enableButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (enableButton.isSelected())
                        GUI.enable();
                    else {
                        disableButton.setSelected(true);
                        GUI.disable();
                    }
                }
            });

            disableButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (disableButton.isSelected())
                        GUI.disable();
                    else {
                        enableButton.setSelected(true);
                        GUI.enable();
                    }
                }
            });


        operationModeGroup.getChildren().addAll(enableButton, disableButton, teleSelection, autonSelection, practiceSelection, testSelection);

        replaceGroup(operationModeGroup);


    }

    private static void replaceGroup(Group newGroup) {
        driverStationRoot.getChildren().clear();
        driverStationRoot.getChildren().addAll(menuSelectionGroup, newGroup);
    }
}

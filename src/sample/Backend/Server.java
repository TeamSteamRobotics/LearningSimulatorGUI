package sample.Backend;

import javafx.scene.control.ToggleButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Handles the connect to and handling of the server (LearningSimulator). Expects the server to be running before the
 * GUI is ran. Parses input and outputs it to the main loop.
 */
public class Server {

    static boolean connected = false;
	
	//Variables for connecting to the server
	static Socket client;
    static PrintWriter out;
    static BufferedReader in;
    
    //Input variables
    static String[] values = {"0", "0", "0"};
    static double x = 0;
    static double y = 0;
    static double rot = 0;

    /**
     * Tries to connect to the LearningSimulator server, then sets up the input and output streams.
     */
    public static void tryConnect() {
        try {
            client = new Socket("localhost", 3333);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            System.out.println("Connected to server");
            connected = true;
        } 
        catch (IOException ex) { 
        		System.out.println(ex + "ion not found. Trying to connect :/ ......");
        }
    }

    /**
     * Updates the x, y, and rotation values that are streaming from the server.
     */
    public static void updateXYRot() {
	    	try {
                if (in.ready()) {
                    values = in.readLine().split(",");
                    x = Double.parseDouble(values[0]);
                    y = Double.parseDouble(values[1]);
                    rot = Double.parseDouble(values[2]);
                    System.out.println("X: " + x + " Y: " + y + " Rot: " + rot);
                }
	        }
	    	catch (IOException ex) {
	    	    //GUI.disable();
                connected = false;
	    		System.out.println("IOExecption in updateXYRot");
	    	}
    }

    public static void sendRobotState(int leftWheelDirection, int rightWheelDirection, String robotState) {
        out.println(leftWheelDirection + "," + rightWheelDirection + "," + robotState );
    }

    /**
     * Returns the x value from the server
     * @return The x value from the server
     */
	public static double getX() {
		return x;
	}
	
	/**
     * Returns the y value from the server
     * @return The y value from the server
     */
	public static double getY() {
		return y;
	}
	
	/**
     * Returns the rot value from the server
     * @return The rot value from the server
     */
	public static double getRot() {
		return rot;
	}
}

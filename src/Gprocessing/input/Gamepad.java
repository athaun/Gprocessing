package Gprocessing.input;

import Gprocessing.util.Engine;
import imgui.ImGui;

import static org.lwjgl.glfw.GLFW.*;

// https://github.com/LWJGL/lwjgl3-wiki/wiki/2.6.3-Input-handling-with-GLFW#joystick-input

public class Gamepad {

    public static int LEFT_STICK_HORIZONTAL = 0;
    public static int LEFT_STICK_VERTICAL = 1;
    public static int LEFT_TRIGGER = 2;
    public static int RIGHT_TRIGGER = 5;
    public static int RIGHT_STICK_HORIZONTAL = 3;
    public static int RIGHT_STICK_VERITCAL = 4;

    public static int A = 0;
    public static int B = 1;
    public static int X = 2;
    public static int Y = 3;
    public static int LEFT_SHOULDER = 4;
    public static int RIGHT_SHOULDER = 5;
    public static int BACK = 6;
    public static int START = 7;
    public static int XBOX = 8;
    public static int LEFT_STICK = 9;
    public static int RIGHT_STICK = 10;
    public static int D_UP = 11;
    public static int D_RIGHT = 12;
    public static int D_DOWN = 13;
    public static int D_LEFT = 14;

    /**
     * @return Returns an int with the number of connected controllers.
     */
    public static int controllersAvailable () {
        int total = 0;
        for (int i = 0; i < 10; i ++) {
            if (glfwJoystickPresent(i)) {
                total ++;
            }
        }
        return total;
    }

    /**
     * @param controllerId An int representing the ID of a gamepad or controller
     * @param button An int representing the button to be checked.
     * @return Returns a boolean true if the button is pressed, otherwise, it returns false.
     */
    public static boolean buttonPressed (int controllerId, int button) {
        try {
            return glfwGetJoystickButtons(controllerId).get(button) == 1;
        } catch (NullPointerException e) {
            Engine.println("[ERROR] No Controller Attached on " + controllerId + ".");
            return false;
        }
    }

    /**
     * @param controllerId An int representing the ID of a gamepad or controller
     * @param axis An int representing the axis to be checked.
     * @return Returns a float representing the directional state of the axis.
     */
    public static float axis (int controllerId, int axis) {
        try {
            float x = glfwGetJoystickAxes(controllerId).get(axis);
            if (Math.abs(x) < 0.1) {
                x = 0;
            }
            return x;
        } catch (NullPointerException e) {
            Engine.println("[ERROR] No Controller Attached on " + controllerId + ".");
            return 0;
        }
    }

    /**
     * @param i ID of button to be tested.
     */
    private static void testBtn(int i) {
        ImGui.text("Button " + i + " = " + buttonPressed(0, i));
    }

    /**
     * @param i ID of axis to be tested
     */
    private static void testAxis(int i) {
        ImGui.text("Axis " + i + " = " + axis(0, i));
    }

    /**
     * Lists the state of every button according to default xbox controller map on controller 0.
     */
    public static void gui() {
        ImGui.text(GLFW_GAMEPAD_AXIS_LAST + " Indices");
        testAxis(LEFT_STICK_HORIZONTAL);
        testAxis(LEFT_STICK_VERTICAL);
        testAxis(LEFT_TRIGGER);
        testAxis(RIGHT_STICK_HORIZONTAL);
        testAxis(RIGHT_STICK_VERITCAL);
        testAxis(RIGHT_TRIGGER);

        ImGui.newLine();

        ImGui.text(GLFW_GAMEPAD_BUTTON_LAST + " Indices");
        testBtn(A);
        testBtn(B);
        testBtn(X);
        testBtn(Y);
        testBtn(LEFT_SHOULDER);
        testBtn(RIGHT_SHOULDER);
        testBtn(BACK);
        testBtn(START);
        testBtn(LEFT_STICK);
        testBtn(RIGHT_STICK);
        testBtn(D_UP);
        testBtn(D_RIGHT);
        testBtn(D_DOWN);
        testBtn(D_LEFT);

    }

}

package Gprocessing.input;

import static org.lwjgl.glfw.GLFW.*;

import imgui.ImGui;

// https://github.com/LWJGL/lwjgl3-wiki/wiki/2.6.3-Input-handling-with-GLFW#joystick-input

public class Gamepad {

    public static int LEFT_STICK_HORIZONTAL = 0;
    public static int LEFT_STICK_VERTICAL = 1;
    public static int LEFT_TRIGGER = 2;
    // Right trigger doesn't workkk? why is there no index for left trigger
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
    public static int LEFT_STICK = 9;
    public static int RIGHT_STICK = 10;
    public static int D_UP = 11;
    public static int D_RIGHT = 12;
    public static int D_DOWN = 13;
    public static int D_LEFT = 14;

    public static boolean buttonPressed (int b) {
        return glfwGetJoystickButtons(GLFW_JOYSTICK_1).get(b) == 1;
    }

    public static float axis (int a) {
        return glfwGetJoystickAxes(GLFW_JOYSTICK_1).get(a);
    }

    private static void testBtn(int i) {
        ImGui.text("Button " + i + " = " + buttonPressed(i));
    }

    private static void testAxis(int i) {
        ImGui.text("Axis " + i + " = " + axis(i));
    }

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

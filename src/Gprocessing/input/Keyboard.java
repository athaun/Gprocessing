package Gprocessing.input;

import Gprocessing.graphics.Window;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard {

	public static int SPACE = 32; // Space Bar
	
	public static int W_KEY = 87; // WASD
	public static int A_KEY = 65;
	public static int S_KEY = 83;
	public static int D_KEY = 68;
	
	public static int UP_ARROW = 38; // should work
	public static int LEFT_ARROW = 37;
	public static int DOWN_ARROW = 40;
	public static int RIGHT_ARROW = 39;


	public static boolean keyIsPressed(int keyName) {
		return glfwGetKey(Window.window, keyName) == GLFW_PRESS;
	}

	private static boolean returnBoolPressed;
	public static boolean keyPressed(int keyName) {
		glfwSetKeyCallback(Window.window, new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if (key == keyName && action == GLFW_PRESS) returnBoolPressed = true;
			}
		});

		if (returnBoolPressed) {
			returnBoolPressed = false;
			return true;
		} else {
			return false;
		}
	}

	private static boolean returnBoolReleased;
	public static boolean keyReleased(int keyName) {
		glfwSetKeyCallback(Window.window, new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if (key == keyName && action == GLFW_RELEASE) returnBoolReleased = true;
			}
		});

		if (returnBoolReleased) {
			returnBoolReleased = false;
			return true;
		} else {
			return false;
		}
	}

}
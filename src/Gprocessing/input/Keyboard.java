package Gprocessing.input;

import Gprocessing.graphics.Window;
import imgui.ImGuiIO;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Keyboard {

	public static int SPACE = 32; // Space Bar
	
	public static int W_KEY = 87; // WASD
	public static int A_KEY = 65;
	public static int S_KEY = 83;
	public static int D_KEY = 68;
	
	public static int UP_ARROW = 38;
	public static int LEFT_ARROW = 37;
	public static int DOWN_ARROW = 40;
	public static int RIGHT_ARROW = 39;
	
	static ImGuiIO io = Gprocessing.ImGui.ImGuiLayer.io;

	/**
	 * @param keyCode key-code representing the key to be checked.
	 * @return Returns true if the key is being pressed, otherwise returns false.
	 */
	public static boolean keyIsPressed(int keyCode) {
		return glfwGetKey(Window.window, keyCode) == GLFW_PRESS;
	}

	private static boolean returnBoolPressed;
	/**
	 * Returns true if a key is was just pressed, then returns false until the key is released and pressed again.
	 * @param keyCode key-code representing the key to be checked.
	 * @return Returns true if the key was just pressed, otherwise returns false.
	 */
	public static boolean keyPressed(int keyCode) {
		glfwSetKeyCallback(Window.window, (w, key, scancode, action, mods) -> {
			if (action == GLFW_PRESS) {
				io.setKeysDown(key, true);
			} else if (action == GLFW_RELEASE) {
				io.setKeysDown(key, false);
			}

			io.setKeyCtrl(io.getKeysDown(GLFW_KEY_LEFT_CONTROL) || io.getKeysDown(GLFW_KEY_RIGHT_CONTROL));
			io.setKeyShift(io.getKeysDown(GLFW_KEY_LEFT_SHIFT) || io.getKeysDown(GLFW_KEY_RIGHT_SHIFT));
			io.setKeyAlt(io.getKeysDown(GLFW_KEY_LEFT_ALT) || io.getKeysDown(GLFW_KEY_RIGHT_ALT));
			io.setKeySuper(io.getKeysDown(GLFW_KEY_LEFT_SUPER) || io.getKeysDown(GLFW_KEY_RIGHT_SUPER));

			if (key == keyCode && action == GLFW_PRESS) returnBoolPressed = true;
		});

		if (returnBoolPressed) {
			returnBoolPressed = false;
			return true;
		}
		return false;
	}

	private static boolean returnBoolReleased;
	/**
	 * Returns true if a key was just released.
	 * @param keyCode key-code representing the key to be checked.
	 * @return Returns true if the key was just released, otherwise returns false.
	 */
	public static boolean keyReleased(int keyCode) {
		glfwSetKeyCallback(Window.window, new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if (key == keyCode && action == GLFW_RELEASE) returnBoolReleased = true;
			}
		});

		if (returnBoolReleased) {
			returnBoolReleased = false;
			return true;
		}
		return false;
	}

}
package Gprocessing.input;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_ALT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SUPER;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_ALT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_CONTROL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT_SUPER;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

import org.lwjgl.glfw.GLFWKeyCallback;

import Gprocessing.graphics.Window;
import imgui.ImGuiIO;

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
	
	static ImGuiIO io = Gprocessing.ImGui.ImGuiLayer.io;

	public static boolean keyIsPressed(int keyName) {
		return glfwGetKey(Window.window, keyName) == GLFW_PRESS;
	}

	private static boolean returnBoolPressed;
	public static boolean keyPressed(int keyName) {
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

			if (key == keyName && action == GLFW_PRESS) returnBoolPressed = true;
		});

		if (returnBoolPressed) {
			returnBoolPressed = false;
			return true;
		}
		return false;
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
		}
		return false;
	}

}
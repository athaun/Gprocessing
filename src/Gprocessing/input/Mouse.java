package Gprocessing.input;

import Gprocessing.graphics.Window;
import Gprocessing.physics.Vector2;
import imgui.ImGui;
import imgui.ImGuiIO;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class Mouse {

	public static Vector2 mouse;
	public static long mouseX = 0;
	public static long mouseY = 0;
	public static Vector2 pmouse;
	public static long pmouseX = 0;
	public static long pmouseY = 0;
	public static Vector2 mouseScroll;
	public static double scrollX = 0;
	public static double scrollY = 0;
	public static boolean mouseButton[] = new boolean[3];
	public static boolean mouseDragged;

	private static int _button;
	private static int _action;

	static ImGuiIO io = Gprocessing.ImGui.ImGuiLayer.io;
	
	public static void pollMouseButtons() {
		glfwSetMouseButtonCallback(Window.window, (w, button, action, mods) -> {
			
			// ImGui Input
			final boolean[] mouseDown = new boolean[5];

			mouseDown[0] = button == GLFW_MOUSE_BUTTON_1 && action != GLFW_RELEASE;
			mouseDown[1] = button == GLFW_MOUSE_BUTTON_2 && action != GLFW_RELEASE;
			mouseDown[2] = button == GLFW_MOUSE_BUTTON_3 && action != GLFW_RELEASE;
			mouseDown[3] = button == GLFW_MOUSE_BUTTON_4 && action != GLFW_RELEASE;
			mouseDown[4] = button == GLFW_MOUSE_BUTTON_5 && action != GLFW_RELEASE;

			io.setMouseDown(mouseDown);

			if (!io.getWantCaptureMouse() && mouseDown[1]) {
				ImGui.setWindowFocus(null);
			}
			
			// Gprocessing input
			_button = button;
			_action = action;
		});

		if (_action == GLFW_PRESS) {
			if (_button < mouseButton.length)
				mouseButton[_button] = true;
		} else if (_action == GLFW_RELEASE) {
			if (_button < mouseButton.length) {
				mouseButton[_button] = false;
				mouseDragged = false;
			}
		}
	}	

	public static void pollMouseScroll() {
		glfwSetScrollCallback(Window.window, (w, xOffset, yOffset) -> {
			io.setMouseWheelH(io.getMouseWheelH() + (float) xOffset);
			io.setMouseWheel(io.getMouseWheel() + (float) yOffset);
			
			scrollX = xOffset;
			scrollY = yOffset;
			mouseScroll = new Vector2(scrollX, scrollY);
		});
	}
	
	public static void update() {
		
		pollMouseButtons();
		pollMouseScroll();
		
		DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

		glfwGetCursorPos(Window.window, x, y);
		x.rewind();
		y.rewind();

		long pmouseX = mouseX;
		long pmouseY = mouseY;
		pmouse = new Vector2(pmouseX, pmouseY);

		mouseX = (long) x.get();
		mouseY = (long) y.get();
		mouse = new Vector2(mouseX, mouseY);
		
		if (mouseX != pmouseX || mouseY != pmouseY) {
			mouseDragged = mouseButton[0] || mouseButton[1] || mouseButton[2]; 
		}
	}
	
	public static boolean mouseButtonDown (int button) {
		if (button < mouseButton.length) {
			return mouseButton[button];
		}
		return false;
	}
	
	public static void clearMouseInput () {
		scrollX = 0;
		scrollY = 0;
		mouseScroll = new Vector2(scrollX, scrollY);
		pmouseX = mouseX;
		pmouseY = mouseY;
		pmouse = new Vector2(pmouseX, pmouseY);
	}

	public static void printMouseLocation() {
		Gprocessing.util.Engine.println("mouseX: " + mouseX + "\tmouseY: " + mouseY);
	};

}

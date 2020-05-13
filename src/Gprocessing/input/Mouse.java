package Gprocessing.input;

import static org.lwjgl.glfw.GLFW.*;
import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;

import Gprocessing.Window;
import Gprocessing.physics.Vector2;

public class Mouse {

	public static Vector2 mouse;
	public static long mouseX = 0;
	public static long mouseY = 0;

	public static double[] update() {
		glfwPollEvents();

		DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

		glfwGetCursorPos(Window.window, x, y);
		x.rewind();
		y.rewind();

		double tempX = mouseX;
		double tempY = mouseY;

		mouseX = (long) x.get();
		mouseY = (long) y.get();
		
		mouse = new Vector2(mouseX, mouseY);

		return new double[] { mouseX - tempX, mouseY - tempY };
	}
	
	public static void printMouseLocation () {
		Gprocessing.Engine.println("mouseX: " + mouseX + "\tmouseY: " + mouseY);
	};

}

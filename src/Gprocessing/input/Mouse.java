package Gprocessing.input;

import static org.lwjgl.glfw.GLFW.*;
import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;

public class Mouse {

	public static long mouseX = 0;
	public static long mouseY = 0;

	public static double[] update(long window) {
		glfwPollEvents();

		DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

		glfwGetCursorPos(window, x, y);
		x.rewind();
		y.rewind();

		double tempX = mouseX;
		double tempY = mouseY;

		mouseX = (long) x.get();
		mouseY = (long) y.get();

		return new double[] { mouseX - tempX, mouseY - tempY };
	}
	
	public static void printMouseLocation () {
		Gprocessing.Engine.println("mouseX: " + mouseX + "\tmouseY: " + mouseY);
	};

}

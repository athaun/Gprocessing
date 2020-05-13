package Gprocessing;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.glfw.GLFWVidMode;
import Gprocessing.input.*;

public class Window {

	public long frameCount = 0;

	String title;
	public static long window;
	private GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

	static int width;
	static int height;

	Window(int pwidth, int pheight, String ptitle) {

		width = pwidth;
		height = pheight;
		title = ptitle;

		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		window = glfwCreateWindow(width, height, title, 0, 0);

		if (window == 0)
			throw new IllegalStateException("[FATAL] Failed to create window.");

		glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
	}

	void getFPS() {
		frameCount++;
		glfwSetWindowTitle(window, "GProcessing @ " + Math.round((frameCount / (Engine.millis() / 1000))) + " FPS");
//		Engine.println(Math.round((frameCount / (Engine.millis() / 1000))) + " FPS");
	}
	
	public void setTitle (String title) {
		glfwSetWindowTitle(window, title);
	}

	void showWindow() {
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();

		glOrtho(0, width, height, 0, -1, 1);
		
		Graphics.background(0);
		Graphics.fill(255);

		while (!glfwWindowShouldClose(window)) {
			
			Mouse.update();
			
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);

			Main.update();

			glfwSwapBuffers(window);
			getFPS();
		}

		glfwTerminate();
	};
}
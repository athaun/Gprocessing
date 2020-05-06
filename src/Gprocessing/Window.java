package Gprocessing;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Window {

	public long frameCount = 0;

	String title;
	long window;
	private GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

	int width;
	int height;

	Window(int pwidth, int pheight, String ptitle) {

		width = pwidth;
		height = pheight;
		title = ptitle;

		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		window = glfwCreateWindow(width, height, title, 0, 0);

		if (window == 0) {
			throw new IllegalStateException("[FATAL] Failed to create window.");
		}

		glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
	}

	void getFPS() {
		glfwSetWindowTitle(window,"GProcessing @ " + Math.round((frameCount / (Engine.millis() / 1000))) + " FPS");
//		Engine.println(Math.round((frameCount / (Engine.millis() / 1000))) + " FPS");
	}

	void showWindow() {
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();

		glOrtho(0, width, height, 0, -1, 1);
		glColor4f(0, 120, 176, 0);

		while (!glfwWindowShouldClose(window)) {
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);

			Main.draw();
			getFPS();

			glfwSwapBuffers(window);
			frameCount++;
		}

		glfwTerminate();
	};
}
package Gprocessing.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.glfw.GLFWVidMode;
import Gprocessing.Main;
import Gprocessing.input.*;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;

import static Gprocessing.util.Engine.*;

public class Window {

	public long frameCount = 0;

	String title;
	public static long window;
	private GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

	public static int width;
	public static int height;

	public double dt = 0; // deltaTime
	
	static Shader defaultShader;
	
	public static Main main = new Main();
	
	public static Scene currentScene = main;

	public Window(int pwidth, int pheight, String ptitle) {
		
		width = pwidth;
		height = pheight;
		title = ptitle;

		// Configure GLFW
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

		// Create window
		window = glfwCreateWindow(width, height, title, 0, 0);

		if (window == 0)
			throw new IllegalStateException("[FATAL] Failed to create window.");

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);

		// Enable V-Sync
		glfwSwapInterval(1);
		
		glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
		
	}

	void getFPS() {
		frameCount++;
		glfwSetWindowTitle(window, "GProcessing @ " + Math.round((frameCount / (Engine.millis() / 1000))) + " FPS");
	}

	public void setTitle(String title) {
		glfwSetWindowTitle(window, title);
	}

	public void showWindow() {
		
		/** 
		 * Main game loop
		 */
		glfwShowWindow(window);
		GL.createCapabilities();

		double frameBeginTime = millis()/1000;
		double frameEndTime = millis()/1000;
		
		currentScene.loadEngineResources();
		
		currentScene.awake();
		
		currentScene.startGameObjects();
		
		while (!glfwWindowShouldClose(window)) {

			// poll GLFW for input events
			Mouse.update();
			glfwPollEvents();
			
			glClear(GL_COLOR_BUFFER_BIT);

			currentScene.update();
			currentScene.updateGameObjects();
			
			glfwSwapBuffers(window);
			getFPS();

			frameEndTime = millis()/1000;
			dt = frameEndTime - frameBeginTime;
			Engine.deltaTime = dt;
			frameBeginTime = frameEndTime;
		}

		glfwDestroyWindow(window);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	};
}
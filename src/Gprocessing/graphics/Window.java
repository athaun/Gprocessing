package Gprocessing.graphics;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowTitle;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;

import Gprocessing.editor.EditorGui;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import Gprocessing.Main;
import Gprocessing.ImGui.ImGuiLayer;
import Gprocessing.breakout.Breakout;
import Gprocessing.input.Mouse;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;

import java.util.ArrayList;

public class Window {
	
	// Define and set the current scene
	public static Main main = new Main();
	public static Breakout bScene = new Breakout();

	// public static ArrayList<Scene> scenes = new ArrayList<Scene>();
	
	public static Scene currentScene = main;

	// Window Variables	
	public long frameCount = 0;

	String title;
	public static long window;
	private GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
	
	private ImGuiLayer imguiLayer;

	public static int width;
	public static int height;

	private double dt = 0; // deltaTime (accessible from Engine.deltaTime)
	
	static Shader defaultShader;

	public static EditorGui editor;

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

		glfwSetWindowSizeCallback(window, (w, newWidth, newHeight) -> {
			Window.setWidth(newWidth);
			Window.setHeight(newHeight);
			currentScene.camera.adjustProjection();
		});
		
		// Make the OpenGL context current
		glfwMakeContextCurrent(window);

		// Enable V-Sync
		glfwSwapInterval(1);
		
		// Center the window
		glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);

		if (Engine.showEditor) {
			editor = new EditorGui();
		}

	}

	void getFPS() {
		frameCount++;
		glfwSetWindowTitle(window, title + " @ " + Math.round((frameCount / (Engine.millis() / 1000))) + " FPS");
	}

	public void setTitle(String title) {
		glfwSetWindowTitle(window, title);
	}

	public void showWindow() {
		
		/*
		 * Main game loop
		 */
		glfwShowWindow(window);
		GL.createCapabilities();
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);
		
		double frameBeginTime = (float)glfwGetTime();
		double frameEndTime = (float)glfwGetTime();
		
		imguiLayer = new ImGuiLayer(window);
		imguiLayer.initImGui();

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

			imguiLayer.update((float) dt, currentScene);
			
			glfwSwapBuffers(window);
			getFPS();

			frameEndTime = (float)glfwGetTime();
			dt = frameEndTime - frameBeginTime;
			Engine.deltaTime = dt;
			frameBeginTime = frameEndTime;
		}

		glfwDestroyWindow(window);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	};
	
	private static void setHeight(int newHeight) {
		height = newHeight;		
	}

	private static void setWidth(int newWidth) {
		width = newWidth;
	}
	
	public static int getWidth () {
		return width;
	}
	
	public static int getHeight () {
		return height;
	}
}
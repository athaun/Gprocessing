package Gprocessing.graphics;

import Gprocessing.ImGui.ImGuiLayer;
import Gprocessing.Main;
import Gprocessing.breakout.Breakout;
import Gprocessing.civ.CivScene;
import Gprocessing.editor.EditorGui;
import Gprocessing.input.Mouse;
import Gprocessing.porfolioManager.PortfolioManager;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;
import Gprocessing.chickenCoup.ChickenCoup;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {
	
	// Define and set the current scene
	public static Scene main = new Main();
	public static Scene bScene = new Breakout();
	public static Scene cc = new ChickenCoup();
	public static Scene civ = new CivScene();
	public static Scene pf = new PortfolioManager();

	// public static ArrayList<Scene> scenes = new ArrayList<Scene>();
	
	public static Scene currentScene = civ;

	// Window Variables	
	public long frameCount = 0;

	String title;
	public static long window;
	private GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
	
	private ImGuiLayer imguiLayer;

	public static int width;
	public static int height;

	private float dt = 0; // deltaTime (accessible from Engine.deltaTime)
	
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
//			currentScene.camera.adjustProjection();
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


		// https://www.youtube.com/watch?v=h4u-wrGdx_U

		glEnable(GL_BLEND);
//		glBlendFunc(GL_ONE, GL_SRC_ALPHA);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);


		float frameBeginTime = (float)glfwGetTime();
		float frameEndTime = (float)glfwGetTime();
		
		imguiLayer = new ImGuiLayer(window);
		imguiLayer.initImGui();

		currentScene.loadEngineResources();
		
		currentScene.awake();
		
		currentScene.startGameObjects();

		while (!glfwWindowShouldClose(window)) {
			Engine.deltaTime = dt;
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
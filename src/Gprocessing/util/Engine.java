package Gprocessing.util;

import Gprocessing.graphics.Window;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.glfwInit;

public class Engine {

	/**
	 * Engine contains static core methods that Gprocessing projects may access
	 * also initializes the window and game loop
	 */

	private static long startMillis = System.currentTimeMillis();

	public static Window w;

	public static boolean running = true;

	public static float deltaTime = 0;

	public static boolean showEditor = false;

	/**
	 * Start the engine, and initialize GLFW.
	 * @param windowWidth Width of the window to be created
	 * @param windowHeight Height of the window to be created
	 * @param windowTitle Title of the window to be created
	 * @param se Boolean to show the editor or not
	 */
	public static void init(int windowWidth, int windowHeight, String windowTitle, boolean se) {

		showEditor = se;

		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit())
			throw new IllegalStateException("[FATAL] Failed to initialize GLFW.");

		w = new Window(windowWidth, windowHeight, windowTitle);

		w.showWindow();
	}

	/**
	 * Start the engine, and initialize GLFW.
	 * @param windowWidth Width of the window to be created
	 * @param windowHeight Height of the window to be created
	 * @param windowTitle Title of the window to be created
	 */
	public static void init(int windowWidth, int windowHeight, String windowTitle) {

		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit())
			throw new IllegalStateException("[FATAL] Failed to initialize GLFW.");

		w = new Window(windowWidth, windowHeight, windowTitle);

		w.showWindow();
	}

	/**
	 * @return Returns the number of milliseconds since the engine started.
	 */
	public static double millis() {
		return System.currentTimeMillis() - startMillis;
	}

	/**
	 * Wraps around System.out.println.
	 * @param s String to print
	 */
	public static void println(String s) {
		System.out.println(s);
	}

	/**
	 * Wraps around System.out.println.
	 * @param i int to print
	 */
	public static void println (int i) {
		System.out.println(i);
	}

	/**
	 * Wraps around System.out.println.
	 * @param f float to print
	 */
	public static void println (float f) {
		System.out.println(f);
	}

	/**
	 * println with prefixed "[ERROR] "
	 * @param s String to print
	 */
	public static void printError (String s) {
		System.out.println("[ERROR] " + s);
	}

	/**
	 * println with prefixed "[WARNING] "
	 * @param s String to print
	 */
	public static void printWarning (String s) {
		System.out.println("[WARNING] " + s);
	}

	/**
	 * println with prefixed "[INFO] "
	 * @param s String to print
	 */
	public static void printInfo (String s) {
		System.out.println("[INFO] " + s);
	}

}

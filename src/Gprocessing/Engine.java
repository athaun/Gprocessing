package Gprocessing;

import static org.lwjgl.glfw.GLFW.glfwInit;

public class Engine {

	private static long startMillis = System.currentTimeMillis();

	static Window w;

	public static boolean running = true;

	static void init(int windowWidth, int windowHeight, String windowTitle) {
		if (!glfwInit())
			throw new IllegalStateException("[FATAL] Failed to initialize GLFW.");

		w = new Window(windowWidth, windowHeight, windowTitle); // 1920, 1500

		Main.awake();
		
		w.showWindow();

	}

	static float map(float value, float start1, float stop1, float start2, float stop2) {
		return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
	}

	static double millis() {
		return System.currentTimeMillis() - startMillis;
	}

	static float random(float min, float max) {
		return map((float) Math.random(), 0, 1, min, max);
	}

	static int randomInt(float min, float max) {
		return (int) map((float) Math.random(), 0, 1, min, max);
	}

	static float round(float x) {
		// This is people who are too lazy to even import Math (like me).
		return Math.round(x);
	}

	static void println(String s) {
		System.out.println(s);
	}

}

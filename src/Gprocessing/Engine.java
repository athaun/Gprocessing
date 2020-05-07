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
	
	static float dist (float x1, float y1, float x2, float y2) {
		return (float) Math.hypot(x1 - x2, y1 - y2);
	}
	
	static boolean inCircle (float inX, float inY, float circleX, float circleY, float radius) {
		return dist(inX, inY, circleX, circleY) < radius*2;
	}
	
	static boolean inRect (float inX, float inY, float rectX, float rectY, float rectWidth, float rectHeight) {
		return inX >= rectX &&
			   inX <= (rectY + rectWidth) && 
			   inY >= rectY &&
			   inY <= (rectY + rectHeight);
	}
	
	
	
	// 

	public static void println(String s) {
		System.out.println(s);
	}

}

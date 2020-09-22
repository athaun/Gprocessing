package Gprocessing.util;

import static org.lwjgl.glfw.GLFW.glfwInit;

import org.lwjgl.glfw.GLFWErrorCallback;

import Gprocessing.graphics.Window;
import Gprocessing.physics.RectangleBounds;
import Gprocessing.physics.Transform;
import Gprocessing.physics.Vector2;

public class Engine {

	/**
	 * Engine contains static core methods that Gprocessing projects may access also
	 * initializes the window and game loop
	 */

	private static long startMillis = System.currentTimeMillis();

	public static Window w;

	public static String projectionMode = "processing"; // "processing" or "Gprocessing"

	public static boolean running = true;

	public static double deltaTime = 0;

	public static void init(int windowWidth, int windowHeight, String windowTitle) {

		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit())
			throw new IllegalStateException("[FATAL] Failed to initialize GLFW.");

		w = new Window(windowWidth, windowHeight, windowTitle);

		w.showWindow();

	}

	public static float map(float value, float start1, float stop1, float start2, float stop2) {
		return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
	}

	public static double millis() {
		return System.currentTimeMillis() - startMillis;
	}

	public static float random(float min, float max) {
		return map((float) Math.random(), 0, 1, min, max);
	}

	public static int randomInt(int min, int max) {
		return (int) map((float) Math.random(), 0, 1, min, max);
	}

	public static float round(float x) {
		// This is for people who are too lazy to even import Math (like me).
		return Math.round(x);
	}

	public static float dist(float x1, float y1, float x2, float y2) {
		return (float) Math.hypot(x1 - x2, y1 - y2);
	}

	public static float dist(Vector2 pos1, Vector2 pos2) {
		return (float) Math.hypot(pos1.x - pos2.x, pos1.y - pos2.y);
	}

	public static boolean inCircle(float inX, float inY, float circleX, float circleY, float radius) {
		return dist(inX, inY, circleX, circleY) < radius * 2;
	}

	public static boolean inCircle(Vector2 in, float circleX, float circleY, float radius) {
		return dist(in.x, in.y, circleX, circleY) < radius * 2;
	}

	public static boolean inCircle(Vector2 in, Vector2 circle, float radius) {
		return dist(in.x, in.y, circle.x, circle.y) < radius * 2;
	}

	public static boolean inRect(float inX, float inY, float rectX, float rectY, float rectWidth, float rectHeight) {
		return inX >= rectX && inX <= (rectY + rectWidth) && inY >= rectY && inY <= (rectY + rectHeight);
	}

	public static boolean inRect(float inX, float inY, RectangleBounds r) {
		return inX >= r.x && inX <= (r.y + r.width) && inY >= r.y && inY <= (r.y + r.height);
	}

	public static boolean inRect(Vector2 in, float rectX, float rectY, float rectWidth, float rectHeight) {
		return in.x >= rectX && in.x <= (rectY + rectWidth) && in.y >= rectY && in.y <= (rectY + rectHeight);
	}

	public static boolean inRect(Vector2 in, RectangleBounds r) {
		return in.x >= r.x && in.x <= (r.y + r.width) && in.y >= r.y && in.y <= (r.y + r.height);
	}

	public static boolean rectInRect(Transform t1, Transform t2) {
		return t1.position.x < t2.scale.x && t1.scale.x > t2.position.x && t1.position.y < t2.scale.y
				&& t1.scale.y > t2.position.y;
	}

	public static int constrain (int value, int min, int max) {
		return Math.min(Math.max(value, min), max);
	}

	public static float constrain (float value, float min, float max) {
		return (value > max) ? max : (value < min ? min: value);
	}

	public static void println(String s) {
		System.out.println(s);
	}

}

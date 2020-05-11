package Gprocessing;

import static org.lwjgl.opengl.GL11.*;
import static Gprocessing.Engine.*;
import Gprocessing.graphics.Color;
import Gprocessing.physics.Rectangle;

public class Graphics {
	// Background
	public static void background(float r, float g, float b, float a) {
		// map the values from 0.0-255.0 to 0.0-1.0
		r = map(r, 0, 255, 0, 1);
		g = map(g, 0, 255, 0, 1);
		b = map(b, 0, 255, 0, 1);

		glClearColor(r, g, b, 1);
	}
	public static void background(float r, float g, float b) {
		background(r, g, b, 255);
	}
	public static void background(float rgb) {
		background(rgb, rgb, rgb, 255);
	}
	public static void background(Color color) {
		background(color.r, color.g, color.b, color.a);
	}

	// Fill
	public static void fill(float r, float g, float b, float a) {
		// map the values from 0.0-255.0 to 0.0-1.0
		r = map(r, 0, 255, 0, 1);
		g = map(g, 0, 255, 0, 1);
		b = map(b, 0, 255, 0, 1);
		a = map(a, 0, 255, 0, 1);

		glColor4f(r, g, b, a);
	}
	public static void fill(float r, float g, float b) {
		fill(r, g, b, 255);
	}
	public static void fill(float rgb) {
		fill(rgb, rgb, rgb, 255);
	}
	public static void fill(Color color) {
		fill(color.r, color.g, color.b, color.a);
	}

	// Shape Primitives
	public static void rect(float x, float y, float w, float h) {
		glBegin(GL_POLYGON);
		glVertex2f(x, y);
		glVertex2f(x + w, y);
		glVertex2f(x + w, y + h);
		glVertex2f(x, y + h);
		glEnd();
	}
	public static void rect(Rectangle r) {
		glBegin(GL_POLYGON);
		glVertex2f(r.x, r.y);
		glVertex2f(r.x + r.width, r.y);
		glVertex2f(r.x + r.width, r.y + r.height);
		glVertex2f(r.x, r.y + r.height);
		glEnd();
	}
	public static void line(float x1, float y1, float x2, float y2) {
		glBegin(GL_LINES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glEnd();
	}
	public static void triangle(float x1, float y1, float x2, float y2, float x3, float y3) {
		glBegin(GL_TRIANGLE_STRIP);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glVertex2f(x3, y3);
		glEnd();
	}
	public static void circle(float cx, float cy, float r, int segments) {
		// Credit to Farah Nazifa on Stack Overflow
		glBegin(GL_POLYGON);
		for (int ii = 0; ii < segments; ii++) {
			float theta = 2.0f * 3.1415926f * ii / segments;// get the current angle
			float x = (float) ((float) r * Math.cos(theta));// calculate the x component
			float y = (float) ((float) r * Math.sin(theta));// calculate the y component
			glVertex2f(x + cx, y + cy);// output vertex
		}
		glEnd();
	}
}

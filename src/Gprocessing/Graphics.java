package Gprocessing;

import static org.lwjgl.opengl.GL11.*;
import static Gprocessing.Engine.*;

public class Graphics {
	
	public static void background (float r, float g, float b) {
		// map the values from 0.0-255.0 to 0.0-1.0
		r = map(r, 0, 255, 0, 1);
		g = map(g, 0, 255, 0, 1);
		b = map(b, 0, 255, 0, 1);
		
		glClearColor(r, g, b, 1);
	}
	
	public static void fill (float r, float g, float b) {
		// map the values from 0.0-255.0 to 0.0-1.0
		r = map(r, 0, 255, 0, 1);
		g = map(g, 0, 255, 0, 1);
		b = map(b, 0, 255, 0, 1);
		
		glColor4f(r, g, b, 1);
	}
	
	public static void fillAlpha (float r, float g, float b, float a) {
		// map the values from 0.0-255.0 to 0.0-1.0
		r = map(r, 0, 255, 0, 1);
		g = map(g, 0, 255, 0, 1);
		b = map(b, 0, 255, 0, 1);
		a = map(a, 0, 255, 0, 1);
		
		glColor4f(r, g, b, a);
	}

	public static void rect(float x, float y, float w, float h) {
		glBegin(GL_POLYGON);
		glVertex2f(x, y);
		glVertex2f(x + w, y);
		glVertex2f(x + w, y + h);
		glVertex2f(x, y + h);
		glEnd();
	}
}

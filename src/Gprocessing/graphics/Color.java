package Gprocessing.graphics;
import org.joml.Vector4f;
import Gprocessing.util.Engine;

public class Color {
	
	public static Color RED = new Color(255, 0, 0, 255);
	public static Color GREEN = new Color(0, 255, 0, 255);
	public static Color BLUE = new Color(0, 0, 255, 255);
	public static Color WHITE = new Color(255, 255, 255, 255);
	public static Color BLACK = new Color(0, 0, 0, 255);
	
	public float r;
	public float g;
	public float b;
	public float a;
	
	public Color (float pr, float pg, float pb, float pa) {
		r = pr;
		g = pg;
		b = pb;
		a = pa;
	}
	
	Color color () {
		return this;
	}

	public Vector4f toVec4f() {
		return new Vector4f(r, g, b, a);
	}
	
	private float m (float p) {
		// I am too lazy to type this three times...
		return Engine.map(p, 0, 255, 0, 1);
	}
	
	public Vector4f toNormalizedVec4f () {
		return new Vector4f(m(r), m(g), m(b), m(a));
	}
}

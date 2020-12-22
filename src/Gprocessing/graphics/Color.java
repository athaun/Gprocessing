package Gprocessing.graphics;

import Gprocessing.physics.Transform;
import Gprocessing.util.Engine;
import Gprocessing.util.Utils;
import org.joml.Vector4f;

public class Color {

	public static Color WHITE = new Color(255, 255, 255, 255);
	public static Color BLACK = new Color(0, 0, 0, 255);
	public static Color RED = new Color(255, 0, 0, 255);
	public static Color DARK_RED = new Color(127, 0, 0);
	public static Color GREEN = new Color(0, 255, 0, 255);
	public static Color BLUE = new Color(0, 0, 255, 255);
	public static Color DARK_BLUE = new Color(0, 0, 127);
	public static Color DIRTY_BLUE = new Color(0, 127, 127);
	public static Color PINK = new Color(255, 0, 255);
	public static Color CYAN = new Color(0, 255, 255);
	public static Color YELLOW = new Color(255, 255, 0);
	public static Color PURPLE = new Color(127, 0, 127);

	public static Color[] LIST = {WHITE, BLACK, RED, GREEN, BLUE, PINK, CYAN, YELLOW, DARK_RED, PURPLE, DARK_BLUE, DIRTY_BLUE};

	public float r;
	public float g;
	public float b;
	public float a;

	public Color(float pr, float pg, float pb, float pa) {
		r = pr;
		g = pg;
		b = pb;
		a = pa;
	}

	public static Color randomColor() {
		return LIST[Utils.randomInt(2, LIST.length - 1)];
	}

	public Color(float pr, float pg, float pb) {
		this(pr, pg, pb, 255);
	}

	public Color(float c) {
		this(c, c, c, 255);
	}

	
	Color color() {
		return this;
	}

	public Vector4f toVec4f() {
		return new Vector4f(r, g, b, a);
	}

	private float m(float p) {
		// I am too lazy to type this three times...
		return Utils.map(p, 0, 255, 0, 1);
	}

	public Vector4f toNormalizedVec4f() {
		return new Vector4f(m(r), m(g), m(b), m(a));
	}

	public Color fromNormalized() {
		return new Color(Utils.map(r, 0, 1, 0, 255), Utils.map(g, 0, 1, 0, 255), Utils.map(b, 0, 1, 0, 255),
				Utils.map(a, 0, 1, 0, 255));
	}

	@Override
	public boolean equals (Object c) {
		if (c == null) {
			return false;
		} if (!(c instanceof Color)) {
			return false;
		}
		Color otherColor = (Color)c;
		return otherColor.r == this.r && otherColor.g == this.g && otherColor.b == this.b && otherColor.a == this.a;
	}

	public void setValue(String type, float value) {
		switch (type) {
		case "r":
			r = value;
			break;
		case "g":
			g = value;
			break;
		case "b":
			b = value;
			break;
		case "a":
			a = value;
			break;
		}
		Engine.println("Called " + value);
	}
}

package Gprocessing.physics;

public class Vector2 {
	
	public float x;
	public float y;
	
	Vector2 (float px, float py) {
		x = px;
		y = py;
	}
	
	public void add (float x2, float y2) {
		x += x2;
		y += y2;
	}
	
	public void print () {
		System.out.println("X: " + x + ", Y: " + y);
	}
}

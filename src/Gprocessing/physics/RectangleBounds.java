package Gprocessing.physics;

public class RectangleBounds {

	public float x;
	public float y;
	public float width;
	public float height;

	public RectangleBounds(float px, float py, float pw, float ph) {
		x = px;
		y = py;
		width = pw;
		height = ph;
	}

	public RectangleBounds Rectangle() {
		return this;
	}
}

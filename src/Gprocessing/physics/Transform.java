package Gprocessing.physics;

import org.joml.Vector2f;

public class Transform {
	public Vector2f position;
	public Vector2f scale;
	
	public Transform () {
		init(new Vector2f(), new Vector2f());
	}
	
	public Transform (Vector2f position) {
		init(position, new Vector2f());
	}
	
	public Transform (Vector2f position, Vector2f scale) {
		init(position, scale);
	}
	
	public Transform (float x, float y, float w, float h) {
		init(new Vector2f(x, y), new Vector2f(w, h));
	}
	
	private void init (Vector2f position, Vector2f scale) {
		this.position = position;
		this.scale = scale;
	}
}

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
	
	public Transform copy () {
		return new Transform(new Vector2f(this.position), new Vector2f(this.scale));
	}
	
	public void copy (Transform to) {
		to.position.set(this.position);
		to.scale.set(this.scale);
	}
	
	@Override
	public boolean equals (Object o) {
		if (o == null) {
			return false;
		} if (!(o instanceof Transform)) {
			return false;
		} 
		Transform t = (Transform)o;
		return t.position.equals(this.position) && t.scale.equals(this.scale);
	}
	
	// Getters and Setters
	
	public float getX () {
		return this.position.x;
	}
	
	public void setX (float x) {
		this.position.x = x;
	}
	
	public void addX (float x) {
		this.position.x += x;
	}
	
	public float getY () {
		return this.position.y;
	}
	
	public void setY (float y) {
		this.position.y = y;
	}
	
	public void addY (float y) {
		this.position.y += y;
	}
	
	public float getWidth () {
		return this.scale.x;
	}
	
	public void setWidth (float w) {
		this.scale.x = w;
	}
	
	public void addWidth (float w) {
		this.scale.x += w;
	}
	
	public float getHeight () {
		return this.scale.y;
	}
	
	public void setHeight (float h) {
		this.scale.y = h;
	}
	
	public void addHeight (float h) {
		this.scale.y += h;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getScale() {
		return scale;
	}

	public void setScale(Vector2f scale) {
		this.scale = scale;
	}
	
	
	
	
	
	
	
}

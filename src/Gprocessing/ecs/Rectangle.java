package Gprocessing.ecs;

import org.joml.Vector4f;

import Gprocessing.graphics.Color;
import Gprocessing.physics.Transform;

public class Rectangle extends Component  {

	SpriteRenderer rectangleSprite;
	private Vector4f color;
	private Transform lastTransform;
	
	public Rectangle (Vector4f c) {
		rectangleSprite = new SpriteRenderer();
		rectangleSprite.setColor(c);
		this.color = c;
	}
	
	public Rectangle (Color c) {
		this(c.toNormalizedVec4f());
	}
	
	public Rectangle (float r, float g, float b) {
		this(new Color(r, g, b, 255));
	}
	
	@Override
	public void start() {
		gameObject.addComponent(rectangleSprite);
	}
	
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}
	
	public void setColor(Color c) {
		gameObject.getComponent(SpriteRenderer.class).setColor(c.toNormalizedVec4f());
	}

}

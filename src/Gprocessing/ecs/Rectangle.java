package Gprocessing.ecs;

import Gprocessing.graphics.Color;
import Gprocessing.physics.Transform;
import org.joml.Vector4f;

public class Rectangle extends Component  {

	SpriteRenderer rectangleSprite;
	private Vector4f color;
	private Transform lastTransform;
	
	private Rectangle (Vector4f c) {
		rectangleSprite = new SpriteRenderer(c);
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

	public void setAlpha (float a) {
		gameObject.getComponent(SpriteRenderer.class).setAlpha(a);
	}

	public void setColor(Color c) {
		gameObject.getComponent(SpriteRenderer.class).setColor(c);
	}
	
	public Color getColor () {
		return gameObject.getComponent(SpriteRenderer.class).getColor();
	}

}

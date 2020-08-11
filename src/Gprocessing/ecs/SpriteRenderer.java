package Gprocessing.ecs;

import org.joml.Vector2f;
import org.joml.Vector4f;
import Gprocessing.graphics.Color;
import static Gprocessing.graphics.Color.*;
import Gprocessing.graphics.Texture;

public class SpriteRenderer extends Component {

	private Vector4f color;
	private Sprite sprite;

	public SpriteRenderer(Vector4f color) {
		this.setColor(color);
		this.sprite = new Sprite(null);
	}

	public SpriteRenderer(Color color) {
		this.setColor(color.toVec4f());
		this.sprite = new Sprite(null);
	}

	public SpriteRenderer(Sprite sprite) {
		this.sprite = sprite;
		this.color = WHITE.toNormalizedVec4f();
	}
	
	public void useGlobalFill() {

	}

	@Override
	public void start() {}

	@Override
	public void update(float dt) {}
	
	public Texture getTexture () {
		return sprite.getTexture();
	}
	
	public Vector2f[] getTexCoords() {
		return sprite.getTextureCoordinates();
	}

	public Vector4f getColor() {
		return this.color;
	}

	public void setColor(Vector4f color) {
		this.color = color;
	}
}

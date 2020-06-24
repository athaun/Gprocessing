package Gprocessing.ecs;

import org.joml.Vector2f;
import org.joml.Vector4f;
import Gprocessing.ecs.Component;
import Gprocessing.graphics.Color;
import Gprocessing.graphics.Texture;

public class SpriteRenderer extends Component {

	private Vector4f color;
	private Vector2f[] textureCoordinates = {
		new Vector2f(1, 1),
		new Vector2f(0, 1),
		new Vector2f(0, 0),
		new Vector2f(1, 0)
	};
	private Texture texture;

	public SpriteRenderer(Vector4f color) {
		this.setColor(color);
		this.texture = null;
	}

	public SpriteRenderer(Color color) {
		this.setColor(color.toVec4f());
		this.texture = null;
	}

	public SpriteRenderer(Texture texture) {
		this.texture = texture;
		this.setColor(new Vector4f(1, 1, 1, 1));
	}

	public void useGlobalFill() {

	}

	@Override
	public void start() {
	}

	@Override
	public void update(float dt) {

	}
	
	public Texture getTexture () {
		return this.texture;
	}
	
	public Vector2f[] getTexCoords() {
		return textureCoordinates;
	}

	public Vector4f getColor() {
		return this.color;
	}

	public void setColor(Vector4f color) {
		this.color = color;
	}
}

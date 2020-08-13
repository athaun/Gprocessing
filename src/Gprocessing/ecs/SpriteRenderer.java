package Gprocessing.ecs;

import org.joml.Vector2f;
import org.joml.Vector4f;
import Gprocessing.graphics.Color;
import static Gprocessing.graphics.Color.*;
import Gprocessing.graphics.Texture;
import Gprocessing.physics.Transform;

public class SpriteRenderer extends Component {

	private Vector4f color = new Color(255, 100, 100, 255).toNormalizedVec4f();
	private Sprite sprite;
	
	private Transform lastTransform;
	private boolean isDirty = false; // Dirty flag, tells renderer to redraw if object components have changed

	public SpriteRenderer(Vector4f color) {
		this.setColor(color);
		this.sprite = new Sprite(null);
		this.isDirty = true;
	}

	public SpriteRenderer(Color color) {
		this.setColor(color.toVec4f());
		this.sprite = new Sprite(null);
		this.isDirty = true;
	}

	public SpriteRenderer(Sprite sprite) {
		this.sprite = sprite;
		this.color = WHITE.toNormalizedVec4f();
		this.isDirty = true;
	}
	
	public void useGlobalFill() {

	}

	@Override
	public void start() {
		this.lastTransform = gameObject.transform.copy();
	}

	@Override
	public void update(float dt) {
		if (!this.lastTransform.equals(this.gameObject.transform)) {
			this.gameObject.transform.copy(this.lastTransform);
			isDirty = true;
		}
	}
	
	public Texture getTexture () {
		return sprite.getTexture();
	}
	
	public Vector2f[] getTexCoords() {
		return sprite.getTextureCoordinates();
	}

	public Vector4f getColor() {
		return color;
	}

	public void setColor(Vector4f c) {
		if (!color.equals(c)) {
			this.color = c;
			isDirty = true;
		}
	}
	
	public void setColor (Color color) {
		if (!this.color.equals(color.toNormalizedVec4f())) {
			this.color = color.toNormalizedVec4f();
			isDirty = true;
		}
	}
	
	public void setSprite (Sprite sprite) {
		this.sprite = sprite;
		isDirty = true;
	}

	public boolean isDirty () {
		return isDirty;
	}
	
	public void setClean () {
		isDirty = false;
	}
}

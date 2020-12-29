package Gprocessing.ecs;

import Gprocessing.graphics.Color;
import Gprocessing.graphics.Texture;
import Gprocessing.physics.Transform;
import Gprocessing.util.Assets;
import Gprocessing.util.Utils;
import imgui.ImGui;
import org.joml.Vector2f;
import org.joml.Vector4f;

import static Gprocessing.graphics.Color.WHITE;

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
		// ^ Check .toNormalizedVec4f ^
		this.sprite = new Sprite(null);
		this.isDirty = true;
	}

	public SpriteRenderer(Sprite sprite) {
		this.sprite = sprite;
		this.color = WHITE.toNormalizedVec4f();
		this.isDirty = true;
	}
	
	public SpriteRenderer(String path) {
		this.sprite = new Sprite(Assets.getTexture(path));
		this.color = WHITE.toNormalizedVec4f();
		this.isDirty = true;
	}

	@Override
	public void ImGuiEditorWidget() {
		if (ImGui.collapsingHeader("SpriteRenderer")) {
			ImGui.text("Color");
			ImGui.beginChild("##cp", 0, 300);
			float[] imColor = { color.x, color.y, color.z, color.w };
			if (ImGui.colorPicker4("Color Picker", imColor)) {
				this.color.set(imColor[0], imColor[1], imColor[2], imColor[3]);
				this.isDirty = true;
			}
			ImGui.endChild();
			ImGui.newLine();
			if (this.sprite.getTexture() != null) {
				ImGui.text("Texture");
				ImGui.imageButton(this.sprite.getTexture().getTextureID(), 100, 100, 1, 1, 0, 0);
				ImGui.text("Path: " + this.sprite.getTexture().getFilePath());
			} else {
				ImGui.text("Texture not assigned.");
			}
		}
	}

	public void useGlobalFill() {

	}

	@Override
	public void start() {
		this.lastTransform = gameObject.getTransform().copy();
	}

	@Override
	public void update(float dt) {
		if (!this.lastTransform.equals(this.gameObject.getTransform())) {
			this.gameObject.getTransform().copy(this.lastTransform);
			isDirty = true;
		}
	}

	public Texture getTexture() {
		return sprite.getTexture();
	}

	public Vector2f[] getTexCoords() {
		return sprite.getTextureCoordinates();
	}

	public Vector4f getColorVector() {
		return color;
	}

	public Color getColor() {
		return new Color(color.x, color.y, color.z, color.w);
	}

	public void setColor(Vector4f c) {
		if (!color.equals(c)) {
			this.color = c;
			isDirty = true;
		}
	}

	public void setColor(Color color) {
		if (!this.color.equals(color.toNormalizedVec4f())) {
			this.color = color.toNormalizedVec4f();
			isDirty = true;
		}
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
		isDirty = true;
	}

	public void setAlpha (float a) {
		color.w = Utils.map(a, 0, 255, 0, 1);
	}

	public boolean isDirty() {
		return isDirty;
	}

	public void setClean() {
		isDirty = false;
	}
}

package Gprocessing;

import static Gprocessing.graphics.Graphics.background;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Sprite;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.Color;
import Gprocessing.graphics.Window;
import Gprocessing.physics.Transform;
import Gprocessing.util.Assets;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;
import imgui.ImGui;

public class Editor extends Scene {

	GameObject redBox;
	
	Sprite s;
	SpriteRenderer sr;
	
	public void awake() {		
		camera = new Camera();
		if (levelLoaded) {
//			Engine.println("Awake return call:");
//			for (int i = 0; i < gameObjects.size(); i ++) {
//				Engine.println("GameObject " + i + " Components: " + gameObjects.get(i).components);
//			}
			return;
		}
		redBox = new GameObject("redBox", new Transform(600, 200, 50, 50), 1);
		
		s = new Sprite();
		s.setTexture(Assets.getTexture("src/assets/images/pepper.png"));
		
		sr = new SpriteRenderer();
		sr.setColor(new Color(255, 0, 0, 255).toNormalizedVec4f());
		sr.setSprite(s);
		
		redBox.addComponent(sr);
		
		this.activeGameObject = redBox;
	}

	public void update() {
		background(50, 50, 50);
	}
	
	public void imgui () {
		ImGui.begin("Statistics");
		ImGui.text("FPS: " + Math.round((Window.frameCount / (Engine.millis() / 1000))));
		ImGui.text("GameObjects in scene: " + gameObjects.size());
		for (int i = 0; i < gameObjects.size(); i ++) {
			ImGui.text("GameObject " + i + " Components: " + gameObjects.get(i).components);
		}
		ImGui.end();
	}
}

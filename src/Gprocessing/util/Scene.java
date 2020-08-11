package Gprocessing.util;

import static Gprocessing.util.Engine.deltaTime;
import static Gprocessing.util.Engine.init;
import java.util.ArrayList;
import java.util.List;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.primitives.Rectangle;
import Gprocessing.graphics.renderer.Renderer;
import Gprocessing.ecs.GameObject;

public abstract class Scene {
	
	private Renderer renderer = new Renderer();
	public Camera camera;
	private boolean isRunning = false;
	static protected List<GameObject> gameObjects = new ArrayList<>();
	
	public static void main(String[] args) {
		init(1600, 900, "Gprocessing");		
	}
	
	public void awake () {
		camera = new Camera();
	}
	
	public void update() {

	}
	
	// These methods shouldn't be overridden
	public void startGameObjects () {
		Rectangle.addRectangleGameObjects();
		for (GameObject gameObject : gameObjects) {
			gameObject.start();
			this.renderer.add(gameObject);
		}
		isRunning = true;
	}
	
	public static void addGameObjectToScene (GameObject gameObject) {
//		if (!isRunning) {
//			gameObjects.add(gameObject);
//		} else {
//			gameObjects.add(gameObject);
//			gameObject.start();
//			this.renderer.add(gameObject);
//		}
		gameObjects.add(gameObject);
		gameObject.start();
	}
	
	public Camera camera () {
		return this.camera;		
	}
	
	public void updateGameObjects () {
		for (GameObject go : this.gameObjects) {
			go.update((float) deltaTime);
		}
		
		this.renderer.render();
	}
	
	public void loadEngineResources () {
		Assets.getShader("src/assets/shaders/default.glsl");
	}
}

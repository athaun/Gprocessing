package Gprocessing.util;

import static Gprocessing.util.Engine.deltaTime;
import static Gprocessing.util.Engine.init;

import java.util.ArrayList;
import java.util.List;

import Gprocessing.ecs.GameObject;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.renderer.Renderer;
import imgui.ImGui;


public abstract class Scene {
	
	private Renderer renderer = new Renderer();
	public Camera camera;
	private boolean isRunning = false;
	static protected ArrayList<GameObject> gameObjects = new ArrayList<>();
	protected GameObject activeGameObject = null;
	
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
		for (GameObject gameObject : gameObjects) {
			gameObject.start();
			this.renderer.add(gameObject);
		}
		isRunning = true;
	}

	public ArrayList getGameObjects () {
		return gameObjects;
	}
	
	public static void addGameObjectToScene (GameObject gameObject) {
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

	public void imgui () {
		
	}
}

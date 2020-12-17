package Gprocessing.util;

import Gprocessing.ecs.GameObject;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.renderer.Renderer;

import java.util.ArrayList;

import static Gprocessing.util.Engine.deltaTime;
import static Gprocessing.util.Engine.init;


public abstract class Scene {

	private Renderer renderer = new Renderer();
	public Camera camera;
	private boolean isRunning = false;
	static protected ArrayList<GameObject> gameObjects = new ArrayList<>();
	protected GameObject activeGameObject = null;

	/**
	 * @param args
	 * Entry point to start the application
	 */
	public static void main(String[] args) {
		init(1600, 900, "Gprocessing");		
	}

	/**
	 * Runs only once on startup, useful for initializing gameObjects or for first time setup.
	 */
	public void awake () {
		camera = new Camera();
	}

	/**
	 * This method is called every frame, and can be used to update objects.
	 */
	public void update() {}

	/**
	 * This method is called from the ImGui layer, allowing the user to easily create imGui interfaces.
	 * The user is not restricted to using this method for GUIs, but it is encouraged.
	 */
	public void imgui () {}
	
	// The following methods shouldn't be overridden.
	/**
	 * Loops through all gameobjects already in the scene and calls their start methods.
	 */
	public void startGameObjects() {
		for (GameObject gameObject : gameObjects) {
			gameObject.start();
			this.renderer.add(gameObject);
		}
		isRunning = true;
	}

	/**
	 * @return Returns an ArrayList of gameObjects in the scene.
	 */
	public ArrayList getGameObjects () {
		return gameObjects;
	}

	/**
	 * @param gameObject GameObject to be added.
	 * Add a new gameObject to the scene and immediately call its start method.
	 */
	public static void addGameObjectToScene (GameObject gameObject) {
		gameObjects.add(gameObject);
		gameObject.start();
	}

	/**
	 * @return Returns the scene's instance of Camera
	 */
	public Camera camera () {
		return this.camera;		
	}

	/**
	 * Loops through all the gameObjects in the scene and calls their update methods.
	 */
	public void updateGameObjects () {
		for (GameObject go : this.gameObjects) {
			go.update((float) deltaTime);
		}
		
		this.renderer.render();
	}

	/**
	 * Loads the shader.
	 */
	public void loadEngineResources () {
		Assets.getShader("src/assets/shaders/default.glsl");
	}
}

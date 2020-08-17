package Gprocessing.util;

import static Gprocessing.util.Engine.deltaTime;
import static Gprocessing.util.Engine.init;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Gprocessing.ecs.Component;
import Gprocessing.ecs.ComponentDeserializer;
import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.GameObjectDeserializer;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.renderer.Renderer;
import imgui.ImGui;

public abstract class Scene {

	private Renderer renderer = new Renderer();
	public Camera camera;
	private boolean isRunning = false;
	static protected List<GameObject> gameObjects = new ArrayList<>();
	protected GameObject activeGameObject = null;
	protected boolean levelLoaded = false;

	public static void main(String[] args) {
		init(1600, 900, "Gprocessing");
	}

	public void awake() {
		camera = new Camera();
	}

	public void update() {

	}

	// These methods shouldn't be overridden
	public void startGameObjects() {
		for (GameObject gameObject : gameObjects) {
			gameObject.start();
			this.renderer.add(gameObject);
		}
		isRunning = true;
	}

	public static void addGameObjectToScene(GameObject gameObject) {
		Engine.println("Added GameObject " + gameObject.components);
		gameObjects.add(gameObject);
		gameObject.start();
	}

	public Camera camera() {
		return this.camera;
	}

	public void updateGameObjects() {
		for (GameObject go : Scene.gameObjects) {
			go.update((float) deltaTime);
		}

		this.renderer.render();
	}

	public void loadEngineResources() {
		Assets.getShader("src/assets/shaders/default.glsl");
	}

	public void sceneImgui() {
		if (activeGameObject != null) {
			ImGui.begin("Inspector");
			activeGameObject.imgui();
			ImGui.end();
		}

		imgui();
	}

	public void imgui() {

	}

	// Export and load data from Json using Gson

	public void loadJson () {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.registerTypeAdapter(Component.class, new ComponentDeserializer())
				.registerTypeAdapter(GameObject.class, new GameObjectDeserializer()).create();

		String inFile = "";
		try {
			inFile = new String(Files.readAllBytes(Paths.get("src/assets/saveData/level.json")));
		} catch (Exception e) {
			Engine.println("[INFO] No saveFile for this scene was found.");
			// e.printStackTrace();
		}
		
		if (!inFile.equals("")) {
			
			GameObject[] objs = gson.fromJson(inFile, GameObject[].class);
			
			Engine.println("GameObjects loaded from filesystem: " + objs.length);
			
			for (int i = 0; i < objs.length; i ++) {
				Engine.println("GameObject " + i + " components: " + objs[i].components);
				addGameObjectToScene(objs[i]);
			}
			this.levelLoaded = true;
		}
	}
		
	public void saveExit() {
		Gson gson = new GsonBuilder().setPrettyPrinting()
				.registerTypeAdapter(Component.class, new ComponentDeserializer())
				.registerTypeAdapter(GameObject.class, new GameObjectDeserializer()).create();
		
		try {
			FileWriter writer = new FileWriter("src/assets/saveData/level.json");
			writer.write(gson.toJson(Scene.gameObjects));
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

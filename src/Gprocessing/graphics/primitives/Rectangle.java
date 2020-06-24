package Gprocessing.graphics.primitives;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector4f;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Window;
import Gprocessing.physics.Transform;

public class Rectangle {
	
	private static List<GameObject> rectangles = new ArrayList<>();
	private static int index = -1;
	
	public static void addRectangleGameObjects () {
		for (GameObject rect : rectangles) {
			Window.currentScene.addGameObjectToScene(rect);
		}
	}
	
	public Rectangle () {
		index ++;
		rectangles.add(new GameObject("Rect", new Transform(new Vector2f(100, 100), new Vector2f(100, 100))));
		rectangles.get(index).addComponent(new SpriteRenderer(new Vector4f(1, 0, 0, 1)));
	}
	
	public void setPosition (float x, float y, float w, float h) {
		rectangles.get(index).setTransform(new Transform(new Vector2f(x, y), new Vector2f(w, h)));
		// This is setting the value correctly, it just isn't visually updating... :(
	}
	
	public void setColor (float r, float g, float b, float a) {
		rectangles.get(index).getComponent(SpriteRenderer.class).setColor(new Vector4f(r, g, b, a));
	}
}

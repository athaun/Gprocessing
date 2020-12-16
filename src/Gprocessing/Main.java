/** 
 * 
 * Copyright (C) 2020, Asher Haun
 * Released under the MIT license.
 * 
 * TODO:
 * - Change transform to component
 * - game controllers
 * - dynamic Z-indexing
 *
 * - basic physics (gravity collisions)
 * - fonts
 * - native scene management
 * - networking library
 * - headless mode
 * - primitives
 * - vertex shapes
 * - push/pop matrices
 * - primitives
 * - lerp
 * - animation?
 * - Scene Saving
 * 
 * 	Note when adding a new scene, remember to set it in Window.java
 */

package Gprocessing;

import static Gprocessing.graphics.Graphics.background;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_1;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickButtons;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Rectangle;
import Gprocessing.ecs.Sprite;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.Color;
import Gprocessing.graphics.Window;
import Gprocessing.input.Gamepad;
import Gprocessing.physics.Transform;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;
import imgui.ImGui;

import java.util.ArrayList;

public class Main extends Scene {
		
	public static void main (String[] args) {
		Engine.init(1920, 1080, "Hello World!", true);
	}

	GameObject test = new GameObject("Pepper Sprite", new Transform(100, 100, 140, 140), 10);

	ArrayList<GameObject> testList = new ArrayList<GameObject>();

	public void awake() {		
		camera = new Camera();
		test.addComponent(new SpriteRenderer("src/assets/images/pepper.png"));

		for (int i = 0; i < 10; i ++) {
			testList.add(new GameObject("Rectangle " + i, new Transform(Engine.random(0, Window.getWidth()), Engine.random(0, Window.getHeight()), 40, 40), i));
			testList.get(i).addComponent(new Rectangle(new Color(255, 0, 0)));
		}
	}

	public void update() {
		background(50, 50, 50);
	}

	public void imgui () {
		Gamepad.gui();
	}
}
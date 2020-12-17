/*
 * 
 * Copyright (C) 2020, Asher Haun
 * Released under the MIT license.
 * 
 * TODO:
 * - Change transform to component
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

 * Javadoc style
 *
 * @author Name
 * @param if applicable
 * @return type and description if not void
 */

package Gprocessing;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Camera;
import Gprocessing.physics.Transform;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;

import static Gprocessing.graphics.Graphics.background;

public class Main extends Scene {
		
	public static void main (String[] args) {
		Engine.init(1920, 1080, "Hello World!", true);
	}

	GameObject pepper = new GameObject("Pepper", new Transform(100, 100, 140, 140), 10);

	public void awake() {		
		camera = new Camera();
		pepper.addComponent(new SpriteRenderer("src/assets/images/pepper.png"));
	}

	public void update() {
		background(50, 50, 50);

//		pepper.getTransform().addX(Gamepad.axis(0, Gamepad.LEFT_STICK_HORIZONTAL) * 3);
//		pepper.getTransform().addY(Gamepad.axis(0, Gamepad.LEFT_STICK_VERTICAL) * 3);

	}
}

/** 
 * 
 * Copyright (C) 2020, Asher Haun
 * Released under the MIT license.
 * 
 * 
 * TODO:
 * - mouse input
 * - textures/images
 * - basic physics (gravity collisions)
 * - fonts
 * - native scene management
 * - networking library
 * - headless mode
 * - GUI library
 * - primitives
 * - vertex shapes
 * - push/pop matrices
 * - primitives
 * - constrain
 * - lerp
 * - animation?
 */

package Gprocessing;

import static Gprocessing.graphics.Graphics.*;
import org.joml.Vector2f;
import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.primitives.Rectangle;
import Gprocessing.physics.Transform;
import static Gprocessing.graphics.Color.*;
import Gprocessing.util.Assets;
import Gprocessing.util.Scene;

public class Main extends Scene {

	GameObject obj1 = new GameObject("obj1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
	Rectangle r = new Rectangle();
	public void awake() {
		camera = new Camera(new Vector2f());

		obj1.addComponent(new SpriteRenderer(Assets.getTexture("src/assets/images/pepper.png")));
		this.addGameObjectToScene(obj1);
	}

	public void update() {
		background(WHITE);
	}
}
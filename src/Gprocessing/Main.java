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
 * 
 * 	
 */

package Gprocessing;

import static Gprocessing.graphics.Graphics.background;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Sprite;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.Color;
import Gprocessing.physics.Transform;
import Gprocessing.util.Assets;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;



public class Main extends Scene {
	
	GameObject greenBox = new GameObject(new Transform(600, 230, 50, 50), 1);
	GameObject redBox = new GameObject(new Transform(600, 200, 50, 50), -1);
	
	public void awake() {		
		camera = new Camera();
		
		redBox.addComponent(new SpriteRenderer(new Sprite(Assets.getTexture("src/assets/images/blendImage1.png"))));
		greenBox.addComponent(new SpriteRenderer(new Sprite(Assets.getTexture("src/assets/images/blendImage2.png"))));	
	}

	public void update() {
		background(Color.WHITE);
	}
}
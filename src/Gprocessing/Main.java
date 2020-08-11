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
import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.ecs.Spritesheet;
import Gprocessing.graphics.Camera;
import Gprocessing.physics.Transform;
import Gprocessing.util.Assets;
import Gprocessing.util.Scene;

public class Main extends Scene {

	GameObject mario = new GameObject("obj1", new Transform(100, 100, 256, 256));

	Spritesheet sprites;
	
	public void awake() {
		camera = new Camera();

		sprites = Assets.loadSpritesheet("src/assets/images/spritesheet.png", 16, 16, 25, 0);
		
		mario.addComponent(new SpriteRenderer(sprites.getSprite(5)));	
	}

	public void update() {
		background(0);
	}
}
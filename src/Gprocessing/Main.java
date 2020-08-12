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

import Gprocessing.ecs.GameObject;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.Color;
import Gprocessing.ecs.Rectangle;
import Gprocessing.ecs.Sprite;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.ecs.Spritesheet;
import Gprocessing.physics.Transform;
import Gprocessing.util.Assets;
import Gprocessing.util.Scene;
import static Gprocessing.graphics.Graphics.*;

public class Main extends Scene {
	
	Spritesheet sprites;
	GameObject rectangle = new GameObject(new Transform(0, 0, 1600, 100));
	GameObject mario = new GameObject(new Transform(400, 100, 100, 100));
	GameObject pepper = new GameObject(new Transform(600, 200, 50, 50));
	
	public void awake() {		
		camera = new Camera();
		
		sprites = Assets.loadSpritesheet("src/assets/images/spritesheet.png", 16, 16, 25, 0);
		
		pepper.addComponent(new SpriteRenderer(new Sprite(Assets.getTexture("src/assets/images/pepper.png"))));
		mario.addComponent(new SpriteRenderer(sprites.getSprite(5)));
		rectangle.addComponent(new Rectangle(50, 200, 100));
	}

	public void update() {
		background(Color.BLACK);
	}
}
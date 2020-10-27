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
 * 	Note when adding a new scene, remember to set it in Window.java
 */

package Gprocessing;

import static Gprocessing.graphics.Graphics.background;

import Gprocessing.graphics.Camera;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;


public class Main extends Scene {
	
		
	public static void main (String[] args) {
		Engine.init(1600, 900, "Hello World!");
	}
	
	public void awake() {		
		camera = new Camera();
		
	}

	public void update() {
		background(50, 50, 50);

	}
}
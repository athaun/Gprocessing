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












































































//public class Main extends Scene {
//	
//	static int chunkWidth = 3840; // chunkSize * blockSize should fill the entire screen
//	static int chunkHeight = 2160;
//	static OpenSimplexNoise noise = new OpenSimplexNoise();
//	static double increment = 0.033; // the fineness of the noise map
//	static float[][] terrain = new float[chunkWidth][chunkHeight]; // create an empty 2D array with the dimensions of
//	static GameObject[][] rects = new GameObject[chunkWidth][chunkHeight];
//	
//	
//	public static void main (String[] args) {
//		Engine.init(3840, 2160, "Hello World!");
//	}
//	
//	public void awake() {		
//		camera = new Camera();
//		
//		float yoff = 0;
//		for (int x = 0; x < chunkWidth/4; x++) {
//			float xoff = 0;
//			for (int y = 0; y < chunkHeight/4; y++) {
//				terrain[x][y] = Engine.map((float) noise.eval(xoff, yoff), -1, 1, 0, 255);
//				xoff += increment;
//			}
//			yoff += increment;
//		}
//		
//		for (int x = 0; x < chunkWidth/4; x += 4) {
//			for (int y = 0; y < chunkHeight/4; y += 4) {
//				rects[x][y] = new GameObject(new Transform(x * 2, y * 2, 10, 10));
//				rects[x][y].addComponent(new Rectangle(new Color(terrain[x][y], terrain[x][y], terrain[x][y], 255)));
//			}
//		}
//	}
//	static float moveBegin = 0;
//	public void update() {
//		background(50, 50, 50);
//		
//		moveBegin += 0.01;
//		float yoff = 0;
//		for (int x = 0; x < chunkWidth/4; x+= 4) {
//			float xoff = moveBegin;
//			for (int y = 0; y < chunkHeight/4; y+= 4) {
//				terrain[x][y] = Engine.map((float) noise.eval(xoff, yoff), -1, 1, 0, 255);
//				rects[x][y].getComponent(Rectangle.class).setColor(new Color(terrain[x][y], terrain[x][y], terrain[x][y], 255));
//
//				xoff += increment;
//			}
//			yoff += increment;
//		}		
//	}
//}
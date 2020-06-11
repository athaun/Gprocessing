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

import static Gprocessing.graphics.Color.*;
import static Gprocessing.graphics.Graphics.*;

import Gprocessing.graphics.Window;
import Gprocessing.util.Scene;

public class Main extends Scene {
	public static void update() {
		background(255, 255, 255, 255);
		Window.drawShape();
	}
}
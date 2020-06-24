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
import Gprocessing.graphics.primitives.Rectangle;
import static Gprocessing.graphics.Color.*;
import Gprocessing.util.Scene;

public class Main extends Scene {

	int x = 0;

	Rectangle r = new Rectangle();

	public void update() {
		background(WHITE);
		r.setPosition(1000, 100, 100, 20);
	}

	// TODO: get setter working for ECS!
}
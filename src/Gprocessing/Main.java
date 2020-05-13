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

import static Gprocessing.Graphics.*;
import static Gprocessing.Engine.*;
import static Gprocessing.input.Mouse.*;
import static Gprocessing.input.Keyboard.*;
import static Gprocessing.graphics.Color.*;
import Gprocessing.physics.Rectangle;

public class Main {

	public static void main(String[] args) {
		init(1600, 900, "Gprocessing");
	}

	static void awake() {
	}

	static Rectangle r = new Rectangle(30, 250, 150, 50);

	static void update() {
		background(WHITE);
		fill(BLUE);
		if (inCircle(mouse, 100, 100, 35)) {
			fill(BLACK);
		}
		circle(100, 100, 70, 50);

		fill(BLUE);
		if (inRect(mouse, r)) {
			fill(BLACK);
		}
		rect(r);
		
		if (keyIsPressed(W_KEY) || keyIsPressed(71)) {
			r.y -= 3;
		}
		if (keyIsPressed(S_KEY)) {
			r.y += 3;
		}
		if (keyIsPressed(A_KEY)) {
			r.x -= 3;
		}
		if (keyIsPressed(D_KEY)) {
			r.x += 3;
		}
	}
}
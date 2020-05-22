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

import Gprocessing.pathfinding.Pathfinder;

import static Gprocessing.input.Keyboard.*;
import static Gprocessing.graphics.Color.*;
import Gprocessing.physics.Rectangle;
import Gprocessing.physics.Vector2;

public class Main {

	public static void main(String[] args) {
		init(1920, 1080, "Pathfinding Test");
	}

	static Pathfinder f;
	static Vector2[] nodeVectors = new Vector2[25];

	static void awake() {
		for (int i = 0 ; i < nodeVectors.length; i ++) {
			nodeVectors[i] = new Vector2(randomInt(0, w.width), randomInt(0, w.height));
		}
		f = new Pathfinder(nodeVectors);
	}
	
	static void update() {
		background(WHITE);
		fill(BLUE);
		f.findPath(Pathfinder.nodes[0], Pathfinder.nodes[Pathfinder.nodes.length - 1], 0);
		f.updateNodes();
	}
}
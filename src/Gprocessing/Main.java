/** 
 * 
 * Copyright (C) 2020, Asher Haun
 * Released under the MIT license.
 * 
 */

package Gprocessing;

import static Gprocessing.Graphics.*;
import static Gprocessing.Engine.*;
import static Gprocessing.input.Mouse.*;

import Gprocessing.physics.Rectangle;

import static Gprocessing.graphics.Color.*;


public class Main {

	public static void main(String[] args) {
		init(1600, 900, "Gprocessing");
	}
	
	static void awake() {}	

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
	}
}

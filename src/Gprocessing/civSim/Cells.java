package Gprocessing.civSim;

import static Gprocessing.graphics.Graphics.background;

import Gprocessing.graphics.Camera;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;

public class Cells extends Scene {		
	public static void main (String[] args) {
		Engine.init(1600, 900, "Cells");
	}
	
	public void awake() {		
		camera = new Camera();
	}

	public void update() {
		background(50, 50, 50);

	}
}

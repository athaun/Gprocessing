package Gprocessing;

import static Gprocessing.Graphics.*;
import static Gprocessing.Engine.*;

public class Main {

	static Window w;

	public static void main(String[] args) {
		init();
		w = new Window(1920, 1500, "Gprocessing");
		w.showWindow();
	}

	static void draw() {
		background(0, 0, 0);
		
	}
}

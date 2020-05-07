package Gprocessing;

import static Gprocessing.Graphics.*;
import static Gprocessing.Engine.*;
import Gprocessing.noise.*;
import Gprocessing.threads.*;


public class Main {

	public static void main(String[] args) {
		init(1600, 900, "Gprocessing");
	}

	static int chunkWidth = 1600; // chunkSize * blockSize should fill the entire screen
	static int chunkHeight = 900;
	static OpenSimplexNoise noise = new OpenSimplexNoise();
	static double increment = 0.013; // the fineness of the noise map
	static float[][] terrain = new float[chunkWidth][chunkHeight]; // create an empty 2D array with the dimensions of
																	// chunkSize
	static float moveBegin = 0;

	static ThreadMaster t;

	static void awake() {
		t = new ThreadMaster(new Runnable() {
			public void run() {
				while (true) {
					moveBegin += 0.01;
					float yoff = 0;
					for (int x = 0; x < chunkWidth / 2; x++) {
						float xoff = moveBegin;
						for (int y = 0; y < chunkHeight / 2; y++) {
							terrain[x][y] = map((float) noise.eval(xoff, yoff), -1, 1, 0, 255);
							xoff += increment;
						}
						yoff += increment;
					}
				}
			}
		});
	}

	static void update() {
		for (int x = 0; x < chunkWidth / 2; x += 2) {
			for (int y = 0; y < chunkHeight / 2; y += 2) {
				fill(terrain[x][y], terrain[x][y], terrain[x][y]);
				rect(x * 4, y * 4, 20, 20);
			}
		}
	}
}

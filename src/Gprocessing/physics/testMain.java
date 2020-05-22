package Gprocessing.physics;

public class testMain {
//	package Gprocessing;
//
//	import static Gprocessing.Graphics.*;
//	import static Gprocessing.Engine.*;
//	import static Gprocessing.input.Mouse.*;
//	import static Gprocessing.input.Keyboard.*;
//	import static Gprocessing.graphics.Color.*;
//	import Gprocessing.physics.Rectangle;
//
//	public class Main {
//
//		public static void main(String[] args) {
//			init(1600, 2160, "Gprocessing");
//		}
//
//		static int length = 140; // max seems to be around 100k
//		static Rectangle[] r = new Rectangle[length];
//		static double[] velocity = new double[length];
//		static float[] gravity = new float[length];
//		static double[] friction = new double[length];
//		
//		static void awake() {
//			for (int i = 0; i < r.length; i ++) {
//				r[i] = new Rectangle(random(0, w.width - 50), random(0, w.height- 800), 50, 50);
//				velocity[i] = 1;
//				gravity[i] = 1;
//				friction[i] = 0.7;
//			}
//		}
//
//
//
//		static void update() {
//			background(WHITE);
//			fill(BLUE);
//			
//			for (int i = 0; i < r.length; i ++) {
//				rect(r[i]);
//				
//				r[i].y += velocity[i];
//				
//				if (r[i].y + r[i].height > w.height && velocity[i] >= -1) {
//					velocity[i] = -velocity[i] * friction[i];
//				} else {
//					velocity[i] += gravity[i];
//				}
//			}
//			
////			if (keyIsPressed(W_KEY) || keyIsPressed(71)) {
////				r.y -= 3;
////			}
////			if (keyIsPressed(S_KEY)) {
////				r.y += 3;
////			}
////			if (keyIsPressed(A_KEY)) {
////				r.x -= 3;
////			}
////			if (keyIsPressed(D_KEY)) {
////				r.x += 3;
////			}
//		}
//	}
}

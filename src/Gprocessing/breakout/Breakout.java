package Gprocessing.breakout;

import Gprocessing.graphics.Camera;
import Gprocessing.graphics.Color;
import Gprocessing.graphics.Window;
import Gprocessing.physics.Transform;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;
import imgui.ImGui;

import static Gprocessing.graphics.Graphics.background;

public class Breakout extends Scene {
	public static void main(String[] args) {
		Engine.init(1280, 670, "Breakout");
	}

	Brick[][] bricks = new Brick[30][8];
	float brickWidth = 42.7f;
	int brickHeight = 25;
	int bricksLeft = 0;
	boolean wonGame = false;
	static Ball ball;
	static Paddle paddle;

	public void awake() {
		camera = new Camera();
		for (int x = 0; x < bricks.length; x++) {
			for (int y = 0; y < bricks[0].length; y++) {
				bricks[x][y] = new Brick(new Transform((x * brickWidth) + 1, 40 + ((y * brickHeight) + 1), brickWidth - 2, brickHeight - 2), new Color(x * 6, y * 5, 100, 255));
				bricksLeft++;
			}
		}

		ball = new Ball(new Transform(300, 300, 20, 20), new Color(0, 0, 0, 255));
		paddle = new Paddle(new Transform(Window.getWidth() / 2 - 75, Window.getHeight() - 40, 150, 10), Color.BLACK);
	}

	public void update() {
		if (ball.isAlive) {
			background(255);
			ball.update();

			for (int x = 0; x < bricks.length; x++) {
				for (int y = 0; y < bricks[0].length; y++) {
					if (ball.circleRectCollision(bricks[x][y].getTransform()) && bricks[x][y].isAlive) {
						if (ball.getTransform().getX() < bricks[x][y].getTransform().getX() || ball.getTransform()
								.getX() > bricks[x][y].getTransform().getX() + bricks[x][y].getTransform().getWidth()) {
							// Top or bottom
							ball.flipVelocityX();
						} else {
							// Left or right
							ball.flipVelocityY();
						}
						bricks[x][y].isAlive = false;
						bricksLeft--;
					}
					bricks[x][y].update();
				}
			}
			paddle.update();

			if (bricksLeft <= 0) {
				ball.isAlive = false;
				wonGame = true;
			}
		} else {
			for (int x = 0; x < bricks.length; x++) {
				for (int y = 0; y < bricks[0].length; y++) {
					bricks[x][y].isAlive = false;
					bricks[x][y].update();
				}
			}
		}
	}

	public void imgui() {
		if (ball.isAlive) {
			ImGui.begin("Stats");
			ImGui.text("Bricks Remaining: " + bricksLeft);
			ImGui.end();
		} else {
			ImGui.begin("Game Over");
			if (wonGame) {
				ImGui.text("You Won!!");
			}
			ImGui.text("Bricks Remaining: " + bricksLeft);
			if (ImGui.button("Play Again")) {
				for (int x = 0; x < bricks.length; x++) {
					for (int y = 0; y < bricks[0].length; y++) {
						bricks[x][y].isAlive = true;
						bricks[x][y].setColor(new Color(x * 6, y * 5, 100, 255));
						ball.isAlive = true;
						wonGame = false;
						ball.setTransform(new Transform(paddle.getTransform().position.x + paddle.getTransform().scale.x / 2,
										  paddle.getTransform().position.y - 30, 
										  ball.getTransform().scale.x,
										  ball.getTransform().scale.y));
					}
				}
			}
			ImGui.end();
		}
	}
}

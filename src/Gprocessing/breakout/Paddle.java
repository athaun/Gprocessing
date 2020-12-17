package Gprocessing.breakout;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Rectangle;
import Gprocessing.graphics.Color;
import Gprocessing.input.Keyboard;
import Gprocessing.physics.Collisions;
import Gprocessing.physics.Transform;
import Gprocessing.util.Utils;

import static Gprocessing.breakout.Breakout.ball;

public class Paddle {
private GameObject paddleGo;
	
	public boolean isAlive = true;
	
	public float xVelocity = 0;
	
	public Paddle (Transform t, Color c) {
		this.paddleGo = new GameObject(t);
		paddleGo.addComponent(new Rectangle(c));
	}
	
	float alpha = 255;
	
	public void update () {
		if (ball.circleRectCollision(getTransform())) {
			ball.flipVelocityY();
			ball.setVelocityX((float)((ball.getTransform().position.x - (getTransform().position.x + getTransform().scale.x/2)) * 0.05));
		}
		
		xVelocity = 0;
		if (Keyboard.keyIsPressed(Keyboard.A_KEY)) {
			xVelocity = -7;
		}
		if (Keyboard.keyIsPressed(Keyboard.D_KEY)) {
			xVelocity = 7;
		}
		
		setTransform(new Transform(
				 Utils.constrain(getTransform().position.x + xVelocity, 0, 1280 - getTransform().scale.x),
			 	 getTransform().position.y,
			 	 getTransform().scale.x,
			 	 getTransform().scale.y));
		
	}
	
	public Transform getTransform () {
		return paddleGo.getTransform();
	}
	
	public void setTransform (Transform t) {
		paddleGo.setTransform(t);
	}
	
	public Color getColor () {
		return paddleGo.getComponent(Rectangle.class).getColor();
	}
	
	public void setColor (Color c) {
		paddleGo.getComponent(Rectangle.class).setColor(c);
	}
	
	public boolean isColliding (Transform t) {
		return Collisions.rectInRect(t, this.getTransform());
	}
}

package Gprocessing.breakout;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Color;
import Gprocessing.physics.Transform;
import Gprocessing.physics.Vector2;
import Gprocessing.util.Utils;

import static Gprocessing.breakout.Breakout.paddle;

public class Ball {
	
	private GameObject ballGo;
	
	boolean isAlive = true;
	private Vector2 velocity = new Vector2(Utils.random(-3, 3), -3);
	private boolean firstLoop = true;
	
	public Ball (Transform t, Color c) {
		this.ballGo = new GameObject(t);
		ballGo.addComponent(new SpriteRenderer("src/assets/images/ball.png"));
		ballGo.getComponent(SpriteRenderer.class).setColor(c);
	}
	
	public Transform getTransform () {
		return ballGo.getTransform();
	}
	
	public void setTransform (Transform t) {
		ballGo.setTransform(t);
	}
	
	public Color getColor () {
		return ballGo.getComponent(SpriteRenderer.class).getColor();
	}
	
	public void setColor (Color c) {
		ballGo.getComponent(SpriteRenderer.class).setColor(c);
	}
	
	public void update () {
		if (firstLoop) {
			paddle.getTransform().addX(paddle.getTransform().scale.x/2);
			paddle.getTransform().addX(-30);
			firstLoop = false;
		}
		
		getTransform().addX(velocity.x);
		getTransform().addY(velocity.y);
		
		if (circleRectCollision(new Transform(-10, 0, 1280, 10))) {
			flipVelocityY(); // Ceiling Collision	
		}
		if (circleRectCollision(new Transform(0, 674, 1280, 10))) {
			isAlive = false;
		}
		if (circleRectCollision(new Transform(-10, 0, 10, 674))) {
			flipVelocityX(); // Left side
		}
		if (circleRectCollision(new Transform(1280, 0, 10, 674))) {
			flipVelocityX(); // Right side
		}
	}
	
	public void setVelocity (float x, float y) {
		velocity.x = x;
		velocity.y = y;
	}
	
	public void flipVelocityX () {
		velocity.x *= -1;
	}
	
	public void flipVelocityY () {
		velocity.y *= -1;
	}
	
	public void setVelocityX (float a) {
		velocity.x = a;
	}
	
	public Vector2 getVelocity () {
		return this.velocity;
	}
	
	public boolean circleRectCollision (Transform t) {
	    /** 
	        checks for a collision between a the radius surrounding a point (a circle) and a rectangle
	    */		
		
		float circleX = getTransform().position.x + getTransform().scale.x/2;
		float circleY = getTransform().position.y + getTransform().scale.y/2;
		float radius = getTransform().scale.x/2;
		float rectX = t.position.x;
		float rectY = t.position.y;
		float rectWidth = t.scale.x;
		float rectHeight = t.scale.y;
		
		// The closest edge on the rect to the circle
		
	    float closestEdgeX = circleX;
	    float closestEdgeY = circleY;

	    // Find the closest edge
	    if (circleX < rectX) {
	        // left edge
	        closestEdgeX = rectX;
	    } else if (circleX > rectX + rectWidth) {
	        // right edge
	        closestEdgeX = rectX + rectWidth;
	    }
	    if (circleY < rectY) {
	        // top edge
	        closestEdgeY = rectY;
	    } else if (circleY > rectY + rectHeight) {
	        // bottom edge
	        closestEdgeY = rectY + rectHeight;
	    }

	    // Get distance from closest edge
	    float distanceX = circleX - closestEdgeX;
	    float distanceY = circleY - closestEdgeY;
	    float distance = (float) Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));

	    // if the distance from the center of the circle to the closest edge of the rect is less than the radius, collision!
	    if (distance <= radius) {
	        return true;
	    }
	    return false;
	}
}

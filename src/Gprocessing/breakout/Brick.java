package Gprocessing.breakout;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Rectangle;
import Gprocessing.graphics.Color;
import Gprocessing.physics.Collisions;
import Gprocessing.physics.Transform;
import Gprocessing.util.Utils;

public class Brick {
	
	private GameObject brickGo;
	
	public boolean isAlive = true;
	
	public Brick (Transform t, Color c) {
		this.brickGo = new GameObject(t);
		brickGo.addComponent(new Rectangle(c));
	}
	
	float alpha = 255;
	
	public void update () {
		if (!isAlive) {
			setColor(new Color(Utils.map(getColor().r, 0, 1, 0, 255), Utils.map(getColor().g, 0, 1, 0, 255), Utils.map(getColor().b, 0, 1, 0, 255), alpha));
			alpha = Utils.constrain(alpha -= 14, 0, 255);
		} else {
			
		}
	}
	
	public Transform getTransform () {
		return brickGo.getTransform();
	}
	
	public void setTransform (Transform t) {
		brickGo.setTransform(t);
	}
	
	public Color getColor () {
		return brickGo.getComponent(Rectangle.class).getColor();
	}
	
	public void setColor (Color c) {
		brickGo.getComponent(Rectangle.class).setColor(c);
	}
	
	public boolean isColliding (Transform t) {
		return Collisions.rectInRect(t, this.getTransform());
	}
}

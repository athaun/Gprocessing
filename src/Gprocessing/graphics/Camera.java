package Gprocessing.graphics;

import Gprocessing.util.Engine;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
	
	// magic
	
	private Matrix4f projectionMatrix, viewMatrix;
	public Vector2f position;
	
	public Camera (Vector2f position) {
		this.position = position;
		this.projectionMatrix = new Matrix4f();
		this.viewMatrix = new Matrix4f();
		adjustProjection();
	}
	
	public Camera () {
		this.position = new Vector2f();
		this.projectionMatrix = new Matrix4f();
		this.viewMatrix = new Matrix4f();
		adjustProjection();
	}
	
	public void adjustProjection () {
		projectionMatrix.identity();
		// Top Left origin
		projectionMatrix.ortho(0, Window.getWidth(), Window.getHeight(), 0, 0, 100);
		Engine.println("Resizing: width = " + Window.getWidth());
	}
	
	public Matrix4f getViewMatrix () {
		Vector3f cameraFront = new Vector3f(0, 0, -1);
		Vector3f cameraUp = new Vector3f(0, 1, 0);
		this.viewMatrix.identity();
		viewMatrix.lookAt(new Vector3f(position.x, position.y, 20), cameraFront.add(position.x, position.y, 0), cameraUp);
		
		return this.viewMatrix;
	}

	public Matrix4f getProjectionMatrix () {
		return this.projectionMatrix;		
	}
	
}

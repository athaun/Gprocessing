package Gprocessing.graphics;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import Gprocessing.util.Engine;

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
	
	public void adjustProjection () {
		projectionMatrix.identity();
		if (Engine.projectionMode.equals("Gprocessing")) { // bottom left origin
			projectionMatrix.ortho(0, 32 * 40, 0, 32 * 21, 0, 100);
//			Engine.println("[INFO] Set camera projection mode to \"Gprocessing\".");
		} else if (Engine.projectionMode.equals("processing")) { // Top Left origin
			projectionMatrix.ortho(0, Engine.w.width, Engine.w.height, 0, 0, 100);
//			Engine.println("[INFO] Set camera projection mode to \"processing\".");
		} else {
			Engine.println("[WARNING] Specify a projectionMode of either \"Gprocessing\" or \"processing\".");
		}
	}
	
	public void changeProjectionMode (String mode) {
		Engine.projectionMode = mode;
		this.adjustProjection();
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

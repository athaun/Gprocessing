package Gprocessing.ecs;

public abstract class Component {

	// Parent GameObject
	public GameObject gameObject = null;
	
	public void start () {}
	
	public abstract void update (float dt);
	
}

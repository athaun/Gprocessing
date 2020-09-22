package Gprocessing.ecs;

import java.util.ArrayList;
import java.util.List;

import Gprocessing.Main;
import Gprocessing.physics.Transform;

public class GameObject {
	
	private String name;
	private List<Component> components;
	private Transform transform;
	private int zIndex;
	
	public GameObject (String name, Transform transform, int zIndex) {
		init(name, new ArrayList<>(), transform);
		Main.addGameObjectToScene(this);
		this.zIndex = zIndex;
	}
	
	public GameObject (String name, int zIndex) {
		init(name, new ArrayList<>(), new Transform());
		Main.addGameObjectToScene(this);
		this.zIndex = zIndex;
	}	
	
	public GameObject (Transform transform, int zIndex) {
		init("I really don't remember why I added names, they don't do anything useful", new ArrayList<>(), transform);
		Main.addGameObjectToScene(this);
		this.zIndex = zIndex;
	}
	
	public GameObject (Transform transform) {
		init("I really don't remember why I added names, they don't do anything useful", new ArrayList<>(), transform);
		Main.addGameObjectToScene(this);
		this.zIndex = 0;
	}
	
	private void init (String name, List<Component> components, Transform transform) {
		this.name = name;
		this.components = components;
		this.transform = transform;
	}
	
	public Transform getTransform () {
		return this.transform;
	}
	
	public void setTransform (Transform t) {
		this.transform = t;
	}

	public <T extends Component> T getComponent(Class<T> componentClass) {
		for (Component c : components) {
			if (componentClass.isAssignableFrom(c.getClass())) {
				try {
					return componentClass.cast(c);
				} catch (ClassCastException e) {
					assert false : "[ERROR] Failed to cast component.";
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public <T extends Component> void removeComponent (Class<T> componentClass) {
		for (int i = 0; i < components.size(); i ++) {
			Component c = components.get(i);
			if (componentClass.isAssignableFrom(c.getClass())) {
				components.remove(i);
				return;
			}
		}
	}
	
	public void addComponent (Component c) {
		this.components.add(c);
		c.gameObject = this;
	}
	
	public void update (float dt) {
		for (int i = 0; i < components.size(); i ++) {
			components.get(i).update(dt);
		}
	}
	
	public void start () {
		for (int i = 0; i < components.size(); i ++) {
			components.get(i).start();
		}
	}
	
	public void imgui () {
		for (Component c : components) {
			c.imgui();
		}
	}
	
	public int zIndex () {
		return zIndex;
	}
}

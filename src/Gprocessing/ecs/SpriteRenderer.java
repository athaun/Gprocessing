package Gprocessing.ecs;

import org.joml.Vector4f;
import Gprocessing.ecs.Component;

public class SpriteRenderer extends Component {

    private Vector4f color;

    public SpriteRenderer(Vector4f color) {
        this.setColor(color);
    }

    public void useGlobalFill () {
    	
    }
    
    @Override
    public void start() {
    }

    @Override
    public void update(float dt) {

    }

    public Vector4f getColor() {
        return this.color;
    }

	public void setColor(Vector4f color) {
		this.color = color;
	}
}

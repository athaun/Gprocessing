package Gprocessing.ecs;

import Gprocessing.util.Engine;

public class FontRenderer extends Component {

	@Override
	public void start() {
		if (gameObject.getComponent(SpriteRenderer.class) != null) {
			Engine.println("Found Font Renderer!");
		}
	}
	
	@Override
	public void update(float dt) {
				
	}

}

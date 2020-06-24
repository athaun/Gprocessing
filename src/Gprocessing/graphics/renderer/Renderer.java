package Gprocessing.graphics.renderer;

import java.util.ArrayList;
import java.util.List;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.SpriteRenderer;

public class Renderer {
	private final int MAX_BATCH_SIZE = 1000;
	private List<RenderBatch> batches;
	
	public Renderer () {
		this.batches = new ArrayList<>();
	}
	
	public void render () {
		for (RenderBatch batch : batches) {
			batch.render();
		}
	}
	
	public void add (GameObject go) {
		SpriteRenderer spr = go.getComponent(SpriteRenderer.class);
		if (spr != null) {
			addSpriteRenderer(spr);
		}
	}
	
	private void addSpriteRenderer (SpriteRenderer sprite) {
		boolean added = false;
		for (RenderBatch batch : batches) {
			if (batch.hasRoomLeft()) {
				batch.addSprite(sprite);
				added = true;
				break;
			}
		}
		
		if (!added) {
			// If unable to add to previous batch, create a new one
			RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE);
			newBatch.start();
			batches.add(newBatch);
			newBatch.addSprite(sprite);
		}
	}
}

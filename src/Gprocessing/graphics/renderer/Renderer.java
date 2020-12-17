package Gprocessing.graphics.renderer;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Texture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
			if (batch.hasRoomLeft() && batch.zIndex() == sprite.gameObject.zIndex()) {
				Texture tex = sprite.getTexture();
				if (tex == null || (batch.hasTexture(tex) || batch.hasTextureRoom())) {
					batch.addSprite(sprite);
					added = true;
					break;
				}
			}
		}
		
		if (!added) {
			// If unable to add to previous batch, create a new one
			RenderBatch newBatch = new RenderBatch(MAX_BATCH_SIZE, sprite.gameObject.zIndex());
			newBatch.start();
			batches.add(newBatch);
			newBatch.addSprite(sprite);
			Collections.sort(batches);
		}
	}
}

package Gprocessing.graphics;

import Gprocessing.ecs.Sprite;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class Spritesheet {

    private Texture texture;
    private List<Sprite> sprites;

    public Spritesheet(Texture texture, int spriteWidth, int spriteHeight, int numSprites, int spacing) {
        this.sprites = new ArrayList<>();

        this.texture = texture;
        int currentX = 0;
        int currentY = texture.getHeight() - spriteHeight;
        for (int i = 0; i < numSprites; i ++) {
            float topY = (currentY + spriteHeight) / (float)texture.getHeight();
            float rightX = (currentX + spriteWidth) / (float)texture.getWidth();
            float leftX = currentX / (float)texture.getWidth();
            float bottomY = currentY / (float)texture.getHeight();

            Vector2f[] texCoords = {
                new Vector2f(leftX, bottomY),
                new Vector2f(leftX, topY),
                new Vector2f(rightX, topY),
                new Vector2f(rightX, bottomY)
            };
            Sprite sprite = new Sprite(this.texture, texCoords);
            this.sprites.add(sprite);

            currentX += spriteWidth + spacing;
            if (currentX >= texture.getWidth()) {
                currentX = 0;
                currentY -= spriteHeight + spacing;
            }
        }
    }

    public int getSize () {
        return sprites.size();
    }

    public Sprite getSprite(int index) {
        return this.sprites.get(index);
    }

    public List<Sprite> getSprites () {
        return sprites;
    }
}








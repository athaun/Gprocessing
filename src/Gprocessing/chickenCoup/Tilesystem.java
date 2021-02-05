package Gprocessing.chickenCoup;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Spritesheet;
import Gprocessing.physics.TransformCollision;
import Gprocessing.physics.Transform;
import Gprocessing.util.Utils;

public class Tilesystem {
    Spritesheet sheet;
    GameObject[][] gameObjects = new GameObject[0][0];
    MapHandler m;

    int w, h;

    Tilesystem (Spritesheet s, int xTiles, int yTiles, int width, int height, Chicken c) {
        sheet = s;
        gameObjects = new GameObject[xTiles][yTiles];
        m = new MapHandler(xTiles, yTiles, 40);
        w = width;
        h = height;

        int i = 0;

        for (int x = 0; x < xTiles; x ++) {
            for (int y = 0; y < yTiles; y ++) {
                gameObjects[x][y] = new GameObject("Tile " + i, new Transform(x * width, y * height, width, height), 0);

                if (m.getMap()[x][y] == 1) {
                    gameObjects[x][y].addComponent(new TransformCollision(c.getGameObject().getTransform()));
                    gameObjects[x][y].addComponent(new SpriteRenderer(s.getSprite(
                            Utils.randomInt(0, 6)==0?11:Utils.randomInt(1, 5)
                    )));
                } else {
                    if (m.getMap()[x][y] != 1 && m.getMap()[x][y - 1] == 1) {
                        if (Utils.randomInt(0, 5) == 1) {
                            gameObjects[x][y].addComponent(new SpriteRenderer(s.getSprite(19))); // hanging vines
                        } else if (Utils.randomInt(0, 5) == 1) {
                            gameObjects[x][y].addComponent(new SpriteRenderer(s.getSprite(25)));
                        }
                    }
                }
                i ++;
            }
        }
    }

    public int getType (int worldX, int worldY) {
        int x = (int)worldX/w;
        int y = (int)worldY/h;

        return m.getMap()[x][y];
    }

    public int[] getIndex (int worldX, int worldY) {
        int x = (int)worldX/w;
        int y = (int)worldY/h;

        return new int[] {x, y};
    }

    public Transform getTransform (int worldX, int worldY) {
        int x = (int)worldX/w;
        int y = (int)worldY/h;

        return gameObjects[x][y].getTransform();
    }

}
package Gprocessing.civ;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Rectangle;
import Gprocessing.graphics.Color;
import Gprocessing.physics.Transform;
import Gprocessing.physics.Vector2;
import Gprocessing.util.Engine;

public class Cell {
    /* ATTRIBUTES:
    * X and Y
    * ReproductionRadius
    * Health
    *
    *
    *
    */

    private Vector2 position;
    int reproductionRadius;
    int reproductionRate;
    GameObject sprite;
    public static int cellSize = 4;
    Civ parentCiv;

    public Cell (Vector2 p, int radius, int rate) {
        position = p;
        reproductionRadius = radius;
        reproductionRadius = rate;

        sprite = new GameObject(new Transform(p.x, p.y, cellSize, cellSize));
        sprite.addComponent(new Rectangle(Color.BLACK));
    }

    public void update () {
        if (parentCiv != null) {
            sprite.getComponent(Rectangle.class).setColor(parentCiv.getColor());
        }
    }

    public void setCiv (Civ civ) {
        parentCiv = civ;
    }

    public Civ getParentCiv () {
        return parentCiv;
    }

    public GameObject getGameObject () {
        return sprite;
    }
}

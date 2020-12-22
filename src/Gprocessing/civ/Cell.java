package Gprocessing.civ;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Rectangle;
import Gprocessing.graphics.Color;
import Gprocessing.physics.Transform;
import Gprocessing.physics.Vector2;
import Gprocessing.util.Engine;
import Gprocessing.util.Utils;

public class Cell {
    /* ATTRIBUTES:
    * X and Y
    * ReproductionRadius
    * Health
    *
    *
    *
    */

    int reproductionRadius;
    int reproductionRate;
    GameObject sprite;
    public static int cellSize = 4;
    Civ parentCiv;

    int _x;
    int _y;

    public Cell (Vector2 p, int x, int y, int radius, int rate) {
        reproductionRadius = radius;
        reproductionRate = rate;

        _x = x;
        _y = y;

        sprite = new GameObject(new Transform(p.x, p.y, cellSize, cellSize));
        sprite.addComponent(new Rectangle(Color.BLACK));
    }

    public void update () {
        if (parentCiv != null) {
            sprite.getComponent(Rectangle.class).setColor(parentCiv.getColor());
        }

        reproduceWithinRadius();
    }

    public void reproduceWithinRadius () {
        int randomX = Utils.randomInt(_x - reproductionRadius, _x + reproductionRadius);
        int randomY = Utils.randomInt(_y - reproductionRadius, _y + reproductionRadius);

        randomX = Utils.constrain(randomX, 0, CivScene.cells.length - 1);
        randomY = Utils.constrain(randomY, 0, CivScene.cells[0].length - 1);

        if (CivScene.cells[randomX][randomY].getParentCiv() == null) {
            CivScene.cells[randomX][randomY].setCiv(getParentCiv());
            parentCiv.addCell(CivScene.cells[randomX][randomY]);
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

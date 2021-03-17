package Gprocessing.civ;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Rectangle;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Color;
import Gprocessing.physics.Transform;
import Gprocessing.physics.Vector2;
import Gprocessing.util.Engine;
import Gprocessing.util.Utils;

public class Cell {
    /* ATTRIBUTES:
    * X and Y
    * ReproductionRadius
    * Health -
    * Strength - Effects battles
    *
    *
    */

    int reproductionRadius;
    int fightRadius = 1;

    int reproductionRate;
    float strength = 1;

    GameObject sprite;

    Civ parentCiv;

    public float health = 1;
    public static int maxHealth = 10;
    float healthAlpha = 50;

    public static int cellSize = 12;
    int x;
    int y;

    boolean dead;

    public Cell (Vector2 p, int x, int y, int radius, int rate) {
        reproductionRadius = radius;
        reproductionRate = rate;

        this.x = x;
        this.y = y;

        sprite = new GameObject(new Transform(p.x, p.y, cellSize, cellSize));
        sprite.addComponent(new Rectangle(new Color(0, 0, 0, 0)));
    }

    public void update () {
        if (parentCiv != null && !dead) {

            sprite.getComponent(SpriteRenderer.class).setColor(parentCiv.getColor());
            sprite.getComponent(SpriteRenderer.class).setAlpha(healthAlpha);

            reproduceWithinRadius();
            fight();

            if (health < maxHealth) {
                health += 0.21;
            }

            if (health <= 0) {
                dead = true;
            }

            health = Utils.constrain(health, 0, maxHealth);
            healthAlpha = health * 25.5f;

        } else {
            sprite.getComponent(SpriteRenderer.class).setColor(Color.BLACK);

            dead = false;
            parentCiv.removeCell(this);
            parentCiv = null;
        }
    }

    public void fight () {
        int randomX = Utils.randomInt(x - fightRadius, x + fightRadius + 1);
        int randomY = Utils.randomInt(y - fightRadius, y + fightRadius + 1);

        randomX = Utils.constrain(randomX, 0, CivScene.cells.length - 1);
        randomY = Utils.constrain(randomY, 0, CivScene.cells[0].length - 1);

        Cell otherCell = CivScene.cells[randomX][randomY];

        if (otherCell.getParentCiv() != null) {
            if (otherCell.getParentCiv() != getParentCiv()) {
                if (Utils.randomInt(0, 44) == 1) {
                    //this.strength - other.strength = this.netAdvantage
                    this.strength += Utils.randomInt(-4, 4);
//                    if (this.getParentCiv().index == 2) {
//                        this.strength += 3;
//                    }
                    if (this.strength - otherCell.strength < 0) {
                        this.changeCiv(this.getParentCiv(), otherCell.getParentCiv(), this.strength - 3);
                    } else if (this.strength - otherCell.strength > 0) {
                        otherCell.changeCiv(otherCell.getParentCiv(), this.getParentCiv(), this.strength - 3);
                    }
                }
            }
        }
    }

    public void reproduceWithinRadius () {
        int randomX = Utils.randomInt(x - reproductionRadius, x + reproductionRadius);
        int randomY = Utils.randomInt(y - reproductionRadius, y + reproductionRadius);

        randomX = Utils.constrain(randomX, 0, CivScene.cells.length - 1);
        randomY = Utils.constrain(randomY, 0, CivScene.cells[0].length - 1);

        if (CivScene.cells[randomX][randomY].getParentCiv() == null) {
            CivScene.cells[randomX][randomY].setCiv(getParentCiv());
            parentCiv.addCell(CivScene.cells[randomX][randomY]);
        }
    }

    public void kill () {
        dead = true;
    }

    public void changeCiv (Civ old, Civ next, float strength) {
        old.removeCell(this);
//        this.strength = strength;
        this.setCiv(next);
        next.addCell(this);
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

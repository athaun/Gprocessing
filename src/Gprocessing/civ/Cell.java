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
    * diseased (boolean) - If this unit is diseased
    *
    */

    // Radius around cell to reproduce and/or fight in
    int reproductionRadius;
    int fightRadius = 1;

    int reproductionRate;
    float strength = 1;

    GameObject sprite;

    Civ parentCiv;

    // Health stats
    public float health = 1;
    public static int maxHealth = 10;
    float healthAlpha = 50;
    boolean dead = false;
    boolean diseased = false;

    // Cell info
    public static int cellSize = 12;
    int x;
    int y;

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

            // Set the cell's alpha component and color based on the parent civ's color, and the cell's health
            sprite.getComponent(SpriteRenderer.class).setColor(parentCiv.getColor());
            sprite.getComponent(SpriteRenderer.class).setAlpha(healthAlpha);

            // Alternate between reproduction and fighting (frame gain = ~5)
            if (CivScene.ticks % 2 == 1) {
                reproduceWithinRadius();
            } else {
                fight();
            }

            // Adds health to this cell if not above maxHealth
            if (health <= maxHealth) {
                health += 0.21;
            }

            // If the cell has no health, and should die
            if (health <= 0) {
                dead = true;
            }

            health = Utils.constrain(health, 0, maxHealth);
            healthAlpha = health * 25.5f;
        } else {
            // If the cell is dead, set its color to black
            sprite.getComponent(SpriteRenderer.class).setColor(Color.BLACK);
            dead = false;
            // Remove this cell from the parent civs cell list
            parentCiv.removeCell(this);
            // Remove the parent civ
            parentCiv = null;
        }
    }

    public void fight () {
        // Pick a random position in the cells array, called otherCell
        int randomX = Utils.randomInt(x - fightRadius, x + fightRadius + 1);
        int randomY = Utils.randomInt(y - fightRadius, y + fightRadius + 1);

        randomX = Utils.constrain(randomX, 0, CivScene.cells.length - 1);
        randomY = Utils.constrain(randomY, 0, CivScene.cells[0].length - 1);

        Cell otherCell = CivScene.cells[randomX][randomY];

        // Check if the other cell is not null, and not part of the same civ as this cell
        if (otherCell.getParentCiv() != null) {
            if (otherCell.getParentCiv() != getParentCiv()) {
                // Use a random to slow down how often a fight occurs
                if (Utils.randomInt(0, 34) == 1) {
                    // TODO: implement better strength determination system
                    this.strength += Utils.randomInt(-4, 4);

                    if (this.strength - otherCell.strength < 0) {
                        // if the other cell is stronger, flip this cell's civ to the other cell's
                        this.changeCiv(this.getParentCiv(), otherCell.getParentCiv());
                    } else if (this.strength - otherCell.strength > 0) {
                        // if the this cell is stronger, flip the other cell's civ to the this cell's
                        otherCell.changeCiv(otherCell.getParentCiv(), this.getParentCiv());
                    }
                    // If they have equal strength, do nothing
                }
            }
        }
    }

    public void reproduceWithinRadius () {
        // Pick a random position in the cells array
        int randomX = Utils.randomInt(x - reproductionRadius, x + reproductionRadius);
        int randomY = Utils.randomInt(y - reproductionRadius, y + reproductionRadius);

        randomX = Utils.constrain(randomX, 0, CivScene.cells.length - 1);
        randomY = Utils.constrain(randomY, 0, CivScene.cells[0].length - 1);

        // If that random position does not have a parent civ, reproduce onto it
        if (CivScene.cells[randomX][randomY].getParentCiv() == null) {
            CivScene.cells[randomX][randomY].setCiv(getParentCiv());
            parentCiv.addCell(CivScene.cells[randomX][randomY]);
        }
    }

    public void changeCiv (Civ old, Civ next) {
        // Remove this cell from the old Civ's cell list, and add it to the next Civ
        old.removeCell(this);
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
        return this.sprite;
    }
}

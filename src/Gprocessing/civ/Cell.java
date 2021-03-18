package Gprocessing.civ;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Rectangle;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Color;
import Gprocessing.physics.Transform;
import Gprocessing.physics.Vector2;
import Gprocessing.util.Engine;
import Gprocessing.util.Utils;
import jdk.jshell.execution.Util;
import org.joml.Random;

public class Cell {
    /* ATTRIBUTES:
    * X and Y
    * ReproductionRadius
    * Health -
    * Strength - Effects battles
    * diseased (boolean) - If this unit is diseased
    *
    */

    private static Random randomNumberGenerator = new Random(1);

    // Radius around cell to reproduce and/or fight in
    int reproductionRadius;
    int fightRadius = 1;

    int reproductionRate;
    float strength = 1;

    GameObject sprite;

    Civ parentCiv;

    // Health stats
    public float health = 1;
    public float maxHealth = 10;
    float healthAlpha = 50;
    boolean dead = false;

    Disease disease;

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

            // Alternate between reproduction and fighting (frame gain = ~5)
            if (CivScene.ticks % 2 == 0) {
                reproduceWithinRadius();
            } else {
                fight();
            }
            virus();

            sprite.getComponent(SpriteRenderer.class).setAlpha(healthAlpha);

            // Adds health to this cell if not above maxHealth
            if (health < maxHealth) {
                health += Utils.random(0.05f, 0.15f); // 0.1;
            } else {
                if (disease != null) {
                    // Remove the disease object
                    disease.removeCell(this);
                    disease = null;
                }
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
            sprite.getComponent(SpriteRenderer.class).setAlpha(healthAlpha);
            // Remove this cell from the parent civs cell list

            if (parentCiv != null) {
                // Remove the parent civ
                parentCiv.removeCell(this);
                parentCiv = null;
            }
            dead = false;
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
                    /* TODO: implement better strength determination system
                    * this.strength + this.health vs. other.strength + other.health
                    *
                    * END (?):
                    * Strength = attack
                    * Health = defense
                    *
                    * Health = ?
                    */

                    this.strength += Utils.randomInt(-4, 4);

                    if (this.strength - otherCell.strength < 0) {
                        // If the other cell is stronger, flip this cell's civ to the other cell's
                        this.changeCiv(this.getParentCiv(), otherCell.getParentCiv());
                        this.health = maxHealth;
                    } else if (this.strength - otherCell.strength > 0) {
                        // If the this cell is stronger, flip the other cell's civ to the this cell's
                        otherCell.changeCiv(otherCell.getParentCiv(), this.getParentCiv());
                        otherCell.health = maxHealth;
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
            CivScene.cells[randomX][randomY].health = 4;
        }
    }

    public void virus () {
        if (disease != null && parentCiv != null) {
            if (Utils.randomInt(0, 101) <= disease.getSpreadProbability()) {

                health -= disease.getHealthImpact();
                maxHealth -= disease.getHealthImpact() * 0.1;

                // Pick a random position in the cells array
                int randomX = Utils.randomInt(x - disease.getSpreadRadius(), x + disease.getSpreadRadius() + 1);
                int randomY = Utils.randomInt(y - disease.getSpreadRadius(), y + disease.getSpreadRadius() + 1);

                randomX = Utils.constrain(randomX, 0, CivScene.cells.length - 1);
                randomY = Utils.constrain(randomY, 0, CivScene.cells[0].length - 1);

                // If that random position has a parent civ, infect it
                if (CivScene.cells[randomX][randomY].getParentCiv() != null && !CivScene.cells[randomX][randomY].isDiseased()) {
                    CivScene.cells[randomX][randomY].setDisease(disease);
                    disease.addCell(CivScene.cells[randomX][randomY]);
                }
            }
        }
    }

    public boolean isDiseased () {
        return disease != null;
    }

    public void setDisease (Disease disease) {
        this.disease = disease;
    }

    public void changeCiv (Civ old, Civ next) {
        // Remove this cell from the old civ's cell list, and add it to the next Civ
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

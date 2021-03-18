package Gprocessing.civ;

import Gprocessing.util.Engine;
import Gprocessing.util.Utils;

import java.util.ArrayList;

public class Disease {

    private ArrayList<Cell> cells;
    Cell origin;

    private ArrayList<Cell> queue;
    private ArrayList<Cell> removeQueue;

    float healthImpact; // The amount of health that the virus will reduce for every infected cell
    float spreadProbability; // The probability that the virus will spread to a cells neighbours
    float spreadCount; // The amount of times an infected cell is allowed to spread
    int spreadRadius = 1; // The range of tiles that a virus can spread to its neighbours

    public Disease (Cell cell) {
        cells = new ArrayList<Cell>();
        queue = new ArrayList<Cell>();
        removeQueue = new ArrayList<Cell>();

        origin = cell;
        cells.add(origin);

        // Generating a chance of creating a super virus
        if (Utils.randomInt(0, 3) == 1) {
             // Super spreader
             // Initial probability that the disease will spread to another cell
            spreadProbability = Utils.random(75, 90);

            // Impact the disease has on other cell's health
            healthImpact = Utils.random(0.18f, 0.25f);
            spreadRadius = 2;
        } else {
            // Regular disease
            // Initial probability that the disease will spread to another cell
            spreadProbability = Utils.random(50, 65);

            // Impact the disease has on other cell's health
            healthImpact = Utils.random(0.22f, 0.35f);
        }
        origin.setDisease(this);

        // Give the disease a slight head start by calling it multiple times on the first frame
        for (int i = 0; i < 50; i ++) {
            origin.virus();
        }
    }

    public void update () {
        // Decrease the probability that the disease will spread each frame
        spreadProbability -= 0.3f;
        emptyQueues();
    }

    private void emptyQueues () {
        if (queue.size() > 0) {
            for (Cell c : queue) {
                cells.add(c);
            }
            queue = new ArrayList<Cell>();
        }

        if (removeQueue.size() > 0) {
            for (Cell c : removeQueue) {
                cells.remove(c);
            }
            removeQueue = new ArrayList<Cell>();
        }
    }

    public void addCell (Cell newCell) {
        queue.add(newCell);
    }

    public void removeCell (Cell cell) {
        removeQueue.add(cell);
    }

    public float getHealthImpact() {
        return healthImpact;
    }

    public float getSpreadProbability() {
        return spreadProbability;
    }

    public float getSpreadCount() {
        return spreadCount;
    }

    public int getSpreadRadius () {
        return spreadRadius;
    }
}

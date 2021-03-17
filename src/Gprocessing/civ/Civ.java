package Gprocessing.civ;

import Gprocessing.graphics.Color;
import Gprocessing.physics.Transform;
import Gprocessing.util.Engine;
import Gprocessing.util.Utils;
import imgui.ImGui;

import java.util.ArrayList;
import java.util.ListIterator;

/*
* TODO:
*
*
* THEORY:
* Health
* Reproduction Rate
*
* Battles
*
*
*  */


public class Civ {

    private ArrayList<Cell> cells;
    private Cell origin;
    private Color color;
    public int index;

    private ArrayList<Cell> queue;
    private ArrayList<Cell> removeQueue;

    public static ArrayList<Color> reservedColors = new ArrayList<>();
    public ArrayList<Color> availableColors = new ArrayList<>();

    public Civ (int index, Cell[][] globalCells, Civ[] civs, Cell origin, Color color) {
        this.index = index;
        this.origin = origin;
        this.color = color;
        this.cells = new ArrayList<Cell>();
        this.queue = new ArrayList<Cell>();
        this.removeQueue = new ArrayList<Cell>();

        for (int i = 0; i < civs.length; i ++) {
            if (civs[i] != null) {
                Transform otherOrigin = civs[i].getOrigin().getGameObject().getTransform();
                Transform selfOrigin = this.origin.getGameObject().getTransform();

                // While origin is equal to another civilization's origin, find a new random position.
                while (otherOrigin.getX() == selfOrigin.getX() && otherOrigin.getY() == selfOrigin.getY()) {
                    Engine.printInfo("New cell origin because of overlap.\nX: " + selfOrigin.getX() + " | Y: " + selfOrigin.getY());
                    this.origin = globalCells[Utils.randomInt(0, globalCells.length - 1)][Utils.randomInt(0, globalCells[0].length - 1)];
                }
            }
        }
        test();
    }

    void test () {
        origin.setCiv(this);
        cells.add(origin);

        if (index == 0) {
            color = new Color(255);
            reservedColors.add(color);
        } else {

            for (int i = 0; i < Color.LIST.length; i++) {
                if (!reservedColors.contains(Color.LIST[i])) {
                    availableColors.add(Color.LIST[i]);
                }
            }

            color = availableColors.get(Utils.randomInt(0, availableColors.size() - 1));
            reservedColors.add(color);
        }
    }

    public void update () {
        for (Cell c : cells) {
            c.update();
        }

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
//Strength
    public void addStrength (float s) {
         for (Cell c : cells) {
            c.strength +=  s;
        }

    }

    public ArrayList<Cell> getCells () {
        return cells;
    }

    public void addCell (Cell newCell) {
        queue.add(newCell);
    }

    public void removeCell (Cell cell) {
        removeQueue.add(cell);
    }

    public Cell getOrigin () {
        return origin;
    }

    public Color getColor() {
        return color;
    }

    public void imgui () {
        ImGui.textColored(color.r, color.g, color.b, color.a, "Civ " + index + ": " + cells.size());
    }

}

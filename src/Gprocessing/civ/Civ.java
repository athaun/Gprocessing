package Gprocessing.civ;

import Gprocessing.graphics.Color;
import Gprocessing.physics.Transform;
import Gprocessing.util.Engine;
import Gprocessing.util.Utils;
import imgui.ImGui;

import java.util.ArrayList;
import java.util.ListIterator;

public class Civ {

    private ArrayList<Cell> _cells;
    private Cell _origin;
    private Color _color;
    private int _index;

    private ArrayList<Cell> _queue;

    public Civ (int index, Cell[][] cells, Civ[] civs, Cell origin, Color color) {
        _index = index;
        _origin = origin;
        _color = color;
        _cells = new ArrayList<Cell>();
        _queue = new ArrayList<Cell>();

        for (int i = 0; i < civs.length; i ++) {
            if (civs[i] != null) {
                Transform otherOrigin = civs[i].getOrigin().getGameObject().getTransform();
                Transform selfOrigin = _origin.getGameObject().getTransform();

                // While origin is equal to another civilization's origin, find a new random position.
                while (otherOrigin.getX() == selfOrigin.getX() && otherOrigin.getY() == selfOrigin.getY()) {
                    Engine.printInfo("New cell origin because of overlap.\nX: " + selfOrigin.getX() + " | Y: " + selfOrigin.getY());
                    _origin = cells[Utils.randomInt(0, cells.length - 1)][Utils.randomInt(0, cells[0].length - 1)];
                }
            }
        }

        _origin.setCiv(this);
        _cells.add(_origin);

        for (int i = 0; i < civs.length; i ++) {
            if (civs[i] != null) {
                // While origin is equal to another civilization's origin, find a new random position.
                while (civs[i].getColor().equals(getColor())) {
                    _color = Color.randomColor();
                }
            }
        }
    }

    public void update () {
        for (Cell c : _cells) {
            c.update();
        }

        if (_queue.size() > 0) {
            for (Cell c : _queue) {
                _cells.add(c);
            }
            _queue = new ArrayList<Cell>();
        }

    }

    public ArrayList<Cell> getCells () {
        return _cells;
    }

    public void addCell (Cell newCell) {
        _queue.add(newCell);
    }

    public Cell getOrigin () {
        return _origin;
    }

    public int getOriginRadius () {
        return 123;
    }

    public Color getColor() {
        return _color;
    }

    public void imgui () {
        ImGui.text("Civ " + _index + ": " + _cells.size());
    }

}

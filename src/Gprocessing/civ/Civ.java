package Gprocessing.civ;

import Gprocessing.graphics.Color;
import Gprocessing.physics.Transform;
import Gprocessing.util.Engine;
import Gprocessing.util.Utils;
import imgui.ImGui;

import java.util.ArrayList;

public class Civ {

    private ArrayList<Cell> _cells;
    private Cell _origin;
    private Color _color;

    public Civ (Cell[][] cells, Civ[] civs, Cell origin, Color color) {
        _origin = origin;
        _color = color;
        _cells = new ArrayList<Cell>();

        for (int i = 0; i < civs.length; i ++) {
            if (civs[i] != null) {
                Transform otherOrigin = civs[i].getOrigin().getGameObject().getTransform();
                Transform selfOrigin = _origin.getGameObject().getTransform();

                // While origin is equal to another civilization's origin, find a new random position.
                while (otherOrigin.getX() == selfOrigin.getX() && otherOrigin.getY() == selfOrigin.getY()) {
                    Engine.printInfo("New cell origin because of overlap.");
                    _origin = cells[Utils.randomInt(0, cells.length - 1)][Utils.randomInt(0, cells[0].length - 1)];
                }

                _origin.setCiv(this);
                _cells.add(_origin);

                // While origin is equal to another civilization's origin, find a new random position.
                while (civs[i].getColor().equals(getColor())) {
                    Engine.printInfo("Pulled new color from hat because color is already in use.");
                    _color = Color.randomColor();
                }
            }
        }
    }

    public void update () {
        for (Cell c : _cells) {
            c.update();
        }
    }

    public ArrayList<Cell> getCells () {
        return _cells;
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
        ImGui.text("Civ Position - X: " + _origin.getGameObject().getTransform().getX() + " | Y: " + _origin.getGameObject().getTransform().getY());
    }

}

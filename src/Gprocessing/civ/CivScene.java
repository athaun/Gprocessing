package Gprocessing.civ;

import Gprocessing.ecs.Rectangle;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.Color;
import Gprocessing.graphics.Window;
import Gprocessing.physics.Vector2;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;
import Gprocessing.util.Utils;

import static Gprocessing.graphics.Graphics.background;

public class CivScene extends Scene {
    public static void main (String[] args) {
        Engine.init(1600, 1600, "Civ");
    }

    public static Cell[][] cells;
    int civCount = 10; // Check Color.LIST for safe values
    public static Civ[] civs;

    public void awake () {
        camera = new Camera();

        initializeCells();
        initializeCivs();
    }

    private void initializeCells () {
        // Initialize the cells array with new cells
        cells = new Cell[Window.getWidth()/Cell.cellSize][Window.getHeight()/Cell.cellSize];
        for (int x = 0; x < cells.length; x ++) {
            for (int y = 0; y < cells[0].length; y ++) {
                cells[x][y] = new Cell(new Vector2(x * Cell.cellSize, y * Cell.cellSize), x, y, 4, 5);
            }
        }
    }

    private void initializeCivs () {
        // Add an origin cell to each civilization
        civs = new Civ[civCount];

        for (int i = 0; i < civs.length; i ++) {
            civs[i] = new Civ(i, cells, civs, cells[Utils.randomInt(0, cells.length - 1)][Utils.randomInt(0, cells[0].length - 1)], Color.randomColor());
        }
    }

    public static int ticks = 0;

    public void update () {
        background(Color.BLACK);

        for (Civ c : civs) {
            c.update();
        }
        ticks ++;
    }

    public void imgui () {
        for (Civ c : civs) {
            c.imgui();
        }
    }
}

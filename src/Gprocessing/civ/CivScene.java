package Gprocessing.civ;

import Gprocessing.ecs.Rectangle;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.Color;
import Gprocessing.graphics.Window;
import Gprocessing.input.Keyboard;
import Gprocessing.input.Mouse;
import Gprocessing.physics.Vector2;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;
import Gprocessing.util.Utils;
import imgui.ImGui;
import org.joml.Vector2f;

import static Gprocessing.graphics.Graphics.background;

public class CivScene extends Scene {
    public static void main (String[] args) {
        Engine.init(3840, 2160, "Civ");
    }

    public static Cell[][] cells;
    int civCount = 10; // Check Color.LIST for safe values
    public static Civ[] civs;

    public void awake () {
        camera = new Camera();

        initializeCells();
        initializeCivs();
    }

    float hoveredCellHealth = 0;
    Civ hoveredCellCiv;

    private void initializeCells () {
        // Initialize the cells array with new cells
        cells = new Cell[Window.getWidth()/Cell.cellSize][Window.getHeight()/Cell.cellSize];
        for (int x = 0; x < cells.length; x ++) {
            for (int y = 0; y < cells[0].length; y ++) {
                cells[x][y] = new Cell(new Vector2(x * Cell.cellSize, y * Cell.cellSize), x, y, 2, 5);
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

    DiseaseGenerator dg = new DiseaseGenerator();

    Vector2f mouseToGrid = new Vector2f();

    public void update () {
        background(Color.BLACK);

        for (Civ c : civs) {
            c.update();
        }
        ticks ++;

        mouseToGrid.x = Utils.constrain(Mouse.mouseX / Cell.cellSize, 0, cells.length - 1);
        mouseToGrid.y = Utils.constrain(Mouse.mouseY / Cell.cellSize, 0, cells[0].length - 1);

        hoveredCellCiv = getCellAt(mouseToGrid).getParentCiv();
        if (Mouse.mouseButtonDown(0)) {
            if (hoveredCellCiv != null) {
                hoveredCellCiv.addStrength(100);
            }
        }

        hoveredCellHealth = getCellAt(mouseToGrid).health;

    }

    public Cell getCellAt (Vector2f v) {
        return cells[(int)v.x][(int)v.y];
    }

    public void imgui () {
        for (Civ c : civs) {
            c.imgui();
        }

        ImGui.text("\nHovered Health: " + hoveredCellHealth);
        if (hoveredCellCiv != null) {
            ImGui.text("Hovered Civ: " + hoveredCellCiv.index);
        }
    }
}

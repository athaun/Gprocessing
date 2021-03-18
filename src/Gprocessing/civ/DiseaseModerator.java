package Gprocessing.civ;

import Gprocessing.util.Engine;
import org.joml.Vector2f;

import java.util.ArrayList;

public class DiseaseModerator {
    public static ArrayList<Disease> diseases = new ArrayList<Disease>();

    public void addDisease (Vector2f cellCoordinates) {
        Cell[][] cells = CivScene.cells;

        Cell cellToInfect = cells[(int)cellCoordinates.x][(int)cellCoordinates.y];
        if (cellToInfect.getParentCiv() != null) {
            diseases.add(new Disease(cellToInfect));
        }
    }

    public void update () {
        for (Disease d : diseases) {
            d.update();
        }
    }
}

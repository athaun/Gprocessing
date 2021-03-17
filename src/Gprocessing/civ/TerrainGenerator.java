package Gprocessing.civ;
import Gprocessing.noise.OpenSimplexNoise;
import Gprocessing.util.Engine;
import Gprocessing.util.Utils;
import static Gprocessing.civ.CivScene.cells;

public class TerrainGenerator {
    static double increment = 0.023; // The fineness of the noise map
    static OpenSimplexNoise noise = new OpenSimplexNoise();

    static float moveBegin = 0;

    void run () {
        moveBegin -= 0.001;
        float yoff = 0;
        for (int x = 0; x < cells.length; x ++) {
            float xoff = moveBegin;
            for (int y = 0; y < cells[0].length; y ++) {

                float n = Utils.map((float) noise.eval(xoff, yoff), -1, 1, 0.0f, 0.3f);

                cells[x][y].health -= n;

                xoff += increment;

            }
            yoff += increment;
        }
    }
}

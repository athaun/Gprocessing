package Gprocessing.util;

import Gprocessing.physics.Vector2;

public class Utils {

    /**
     * Re-maps a number from one range to another.
     * @param value Number to me re-mapped
     * @param start1 Lowest number of first range
     * @param stop1 Highest number of first range
     * @param start2 Lowest number of second range
     * @param stop2 Highest number of second range
     * @return Returns the re-mapped value as a float.
     */
    public static float map(float value, float start1, float stop1, float start2, float stop2) {
        return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
    }

    /**
     * Generates a random number from a range of floats.
     * @param min Minimum possible output
     * @param max Maximum possible output
     * @return returns a random float from the range passed.
     */
    public static float random(float min, float max) {
        return map((float) Math.random(), 0, 1, min, max);
    }

    /**
     * Generates a random number from a range of ints.
     * @param min Minimum possible output
     * @param max Maximum possible output
     * @return returns a random int from the range passed.
     */
    public static int randomInt(int min, int max) {
        return (int) map((float) Math.random(), 0, 1, min, max);
    }

    /**
     * This is for people who are too lazy to even import Math, but do want to import Utils. ¯\_(ツ)_/¯
     * @param x Number to be rounded
     * @return Returns the rounded number "x".
     */
    public static float round(float x) {
        return Math.round(x);
    }

    /**
     * Returns the distance between two sets of X and Y coordinates.
     * @param x1 First X coordinate
     * @param y1 First Y coordinate
     * @param x2 Second X coordinate
     * @param y2 Second Y coordinate
     * @return Returns the distance as a float.
     */
    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.hypot(x1 - x2, y1 - y2);
    }

    /**
     * Returns the distance between two sets of X and Y coordinates in the form of "Vector2"s.
     * @param pos1 First Vector2 position
     * @param pos2 Second Vector2 position
     * @return Returns the distance as a float.
     */
    public static float dist(Vector2 pos1, Vector2 pos2) {
        return (float) Math.hypot(pos1.x - pos2.x, pos1.y - pos2.y);
    }

    /**
     * Takes an integer value clamps/constrains it between a minimum and maximum.
     * @param value Input to be constrained
     * @param min Minimum possible value
     * @param max Maximum possible value
     * @return Constrained value as an int.
     */
    public static int constrain (int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Takes a float value clamps/constrains it between a minimum and maximum.
     * @param value Input to be constrained
     * @param min Minimum possible value
     * @param max Maximum possible value
     * @return Constrained value as a float.
     */
    public static float constrain (float value, float min, float max) {
        return (value > max) ? max : (value < min ? min: value);
    }
}

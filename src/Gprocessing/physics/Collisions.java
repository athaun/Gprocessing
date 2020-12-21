package Gprocessing.physics;

import Gprocessing.util.Utils;

public class Collisions {

    /**
     * Checks if a set of X and Y coordinates are inside of a circle.
     * @param inX X position of point to check
     * @param inY Y position of point to check
     * @param circleX X position of circle
     * @param circleY Y position of circle
     * @param radius Radius of circle
     * @return Returns true if the point is inside the circle, otherwise returns false.
     */
    public static boolean inCircle(float inX, float inY, float circleX, float circleY, float radius) {
        return Utils.dist(inX, inY, circleX, circleY) < radius * 2;
    }

    /**
     * Checks if a set of X and Y coordinates are inside of a circle.
     * @param in Vector2 containing coordinates of point to check
     * @param circleX X position of circle
     * @param circleY Y position of circle
     * @param radius Radius of circle
     * @return Returns true if the point is inside the circle, otherwise returns false.
     */
    public static boolean inCircle(Vector2 in, float circleX, float circleY, float radius) {
        return Utils.dist(in.x, in.y, circleX, circleY) < radius * 2;
    }

    /**
     * Checks if a set of X and Y coordinates are inside of a circle.
     * @param in Vector2 containing coordinates of point to check
     * @param circle Vector2 containing coordinates of the circle
     * @param radius Radius of circle
     * @return Returns true if the point is inside the circle, otherwise returns false.
     */
    public static boolean inCircle(Vector2 in, Vector2 circle, float radius) {
        return Utils.dist(in.x, in.y, circle.x, circle.y) < radius * 2;
    }

    /**
     * Checks if a set of X and Y coordinates are inside of a rectangle.
     * @param inX X position of point to check
     * @param inY Y position of point to check
     * @param rectX X position of rectangle
     * @param rectY Y position of rectangle
     * @param rectWidth Width of rectangle
     * @param rectHeight Height of rectangle
     * @return Returns true if the point is inside the rectangle, otherwise returns false.
     */
    public static boolean inRect(float inX, float inY, float rectX, float rectY, float rectWidth, float rectHeight) {
        return inX >= rectX && inX <= (rectY + rectWidth) && inY >= rectY && inY <= (rectY + rectHeight);
    }

    /**
     * Checks if a set of X and Y coordinates are inside of a rectangle.
     * @param in Vector2 containing coordinates of point to check
     * @param rectX X position of rectangle
     * @param rectY Y position of rectangle
     * @param rectWidth Width of rectangle
     * @param rectHeight Height of rectangle
     * @return Returns true if the point is inside the rectangle, otherwise returns false.
     */
    public static boolean inRect(Vector2 in, float rectX, float rectY, float rectWidth, float rectHeight) {
        return in.x >= rectX && in.x <= (rectY + rectWidth) && in.y >= rectY && in.y <= (rectY + rectHeight);
    }

    /**
     * Checks if a rectangle is completely inside of another rectangle.
     * @param t1
     * @param t2
     * @return
     */
    public static boolean rectInRect(Transform t1, Transform t2) {
        return  t1.position.x < t2.scale.x 		&&
                t1.scale.x 	  > t2.position.x 	&&
                t1.position.y < t2.scale.y 		&&
                t1.scale.y    > t2.position.y;
    }

}









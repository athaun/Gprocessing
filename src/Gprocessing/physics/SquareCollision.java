package Gprocessing.physics;

import Gprocessing.ecs.Component;
import Gprocessing.util.Engine;

import java.awt.*;

public class SquareCollision extends Component {

    private static final int LEFT_EDGE = 0;
    private static final int TOP_EDGE = 1;
    private static final int RIGHT_EDGE = 2;
    private static final int BOTTOM_EDGE = 3;

    private Vector2 topLeft;
    private Vector2 bottomRight;

    private Vector2 topLeftOther;
    private Vector2 bottomRightOther;

    private boolean[] edges = {false, false, false, false};

    Transform self, other;    
    
    public SquareCollision(Transform o) {

        other = o; // Transform to check against
    }

    @Override
    public void start () {
        self = gameObject.getTransform(); // Parent game object's transform

    }

    @Override
    public void update (float dt) {
        getDirection();
    }

    public boolean leftEdge () {
        return edges[LEFT_EDGE];
    }

    public boolean rightEdge () {
        return edges[RIGHT_EDGE];
    }

    public boolean topEdge () {
        return edges[TOP_EDGE];
    }

    public boolean bottomEdge () {
        return edges[BOTTOM_EDGE];
    }

    public boolean isOverlapping() {

        if (topLeft.x < bottomRightOther.x || bottomRight.x > topLeftOther.x) {
            return false;
        }
        if (topLeft.y < bottomRightOther.y || bottomRight.y > topLeftOther.y) {
            return false;
        }
        return true;
    }
    
    private void getDirection () {

        self = gameObject.getTransform(); // Parent game object's transform

        topLeft = new Vector2(self.getX(), self.getY());
        bottomRight = new Vector2(self.getX() + self.getWidth(), self.getY() + self.getHeight());

        topLeftOther = new Vector2(other.getX(), other.getY());
        bottomRightOther = new Vector2(other.getX() + other.getWidth(), other.getY() + other.getHeight());

        Rectangle self = new Rectangle((int)topLeft.x, (int)topLeft.y, (int)bottomRight.x, (int)bottomRight.y);
        Rectangle other = new Rectangle((int)topLeftOther.x, (int)topLeftOther.y, (int)bottomRightOther.x, (int)bottomRightOther.y);

        Engine.println("ID: " + gameObject.getName() + "\t\t\t\tt: " + self.y + "\t\tc: " + other.y + "\t\t" + self.intersects(other));

        if (self.intersects(other)) {
//            Engine.printInfo("Colliding");
            // self's left edge is to the right of other's right edge
            if (self.getX() < other.getX() + other.getWidth()) {
                edges[LEFT_EDGE] = true;
            }

            // self's right edge is to the left of other left edge
            if (self.getX() + self.getWidth() > other.getX()) {
                edges[RIGHT_EDGE] = true;
            }

            // self's top edge is lower than other's bottom edge
            if (self.getY() < other.getY() + other.getHeight()) {
                edges[TOP_EDGE] = true;
            }

            // self's bottom edge is higher than other's top edge
            if (self.getY() + self.getHeight() > other.getY()) {
                edges[BOTTOM_EDGE] = true;
            }
        }
    }

}

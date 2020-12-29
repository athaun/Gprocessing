package Gprocessing.physics;

import Gprocessing.ecs.Component;
import Gprocessing.util.Engine;

import java.awt.*;

public class TransformCollision extends Component {

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
    
    public TransformCollision(Transform o) {
        other = o; // Transform to check against
    }

    @Override
    public void start () {
        self = gameObject.getTransform(); // Parent game object's transform
    }

    @Override
    public void update (float dt) {
        findDirection();
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

    public boolean[] getEdges () {
        return edges;
    }

    public boolean isOverlapping () {
        float topEdge1 = self.getY() + self.getHeight();
        float rightEdge1 = self.getX() + self.getWidth();
        float leftEdge1 = self.getX();
        float bottomEdge1 = self.getY();
        float topEdge2 = other.getY() + other.getHeight();
        float rightEdge2 = other.getX() + other.getWidth();
        float leftEdge2 = other.getX();
        float bottomEdge2 = other.getY();

        if (leftEdge1 < rightEdge2 && rightEdge1 > leftEdge2 && bottomEdge1 < topEdge2 && topEdge1 > bottomEdge2) {
            return true;
        }
        return false;
    }
    
    private void findDirection () {
        // Note that edges refers to the edges of self, not other
        self = gameObject.getTransform(); // Parent game object's transform

        if (isOverlapping()) {
            if (self.getX() < other.getX() + other.getWidth() && self.getX() > other.getX()) {
                edges[LEFT_EDGE] = true;
            } else {
                edges[LEFT_EDGE] = false;
            }

            if (self.getX() + self.getWidth() > other.getX() && self.getX() < other.getX()) {
                edges[RIGHT_EDGE] = true;
            } else {
                edges[RIGHT_EDGE] = false;
            }

            if (self.getY() < other.getY() + other.getHeight() && self.getY() > other.getY()) {
                edges[TOP_EDGE] = true;
            } else {
                edges[TOP_EDGE] = false;
            }

            if (self.getY() + self.getHeight() > other.getY() && self.getY() < other.getY()) {
                edges[BOTTOM_EDGE] = true;
            } else {
                edges[BOTTOM_EDGE] = false;
            }
        } else edges = new boolean[]{false, false, false, false};
    }

}

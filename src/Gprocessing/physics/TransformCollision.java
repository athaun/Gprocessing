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

    public boolean collide() {
        float x1 = self.getX();
        float y1 = self.getY();
        float w1 = self.getWidth();
        float h1 = self.getHeight();

        float x2 = other.getX();
        float y2 = other.getY();
        float w2 = other.getWidth();
        float h2 = other.getHeight();

        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
    }
    
    private void findDirection () {
        // Note that edges refers to the edges of self, not other
        self = gameObject.getTransform(); // Parent game object's transform

//        if (isOverlapping()) {
//            if (self.getX() < other.getX() + other.getWidth() && self.getX() > other.getX()) {
//                edges[LEFT_EDGE] = true;
//            } else {
//                edges[LEFT_EDGE] = false;
//            }
//
//            if (self.getX() + self.getWidth() > other.getX() && self.getX() < other.getX()) {
//                edges[RIGHT_EDGE] = true;
//            } else {
//                edges[RIGHT_EDGE] = false;
//            }
//
//            if (self.getY() <= other.getY() + other.getHeight() + 0.1 && self.getY() > other.getY()) {
//                edges[TOP_EDGE] = true;
//            } else {
//                edges[TOP_EDGE] = false;
//            }
//
//            if (self.getY() + self.getHeight() >= other.getY() && self.getY() < other.getY()) {
//                edges[BOTTOM_EDGE] = true;
//            } else {
//                edges[BOTTOM_EDGE] = false;
//            }
//        } else edges = new boolean[]{false, false, false, false};
    }

}

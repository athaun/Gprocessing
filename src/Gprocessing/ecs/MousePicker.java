package Gprocessing.ecs;

import Gprocessing.input.Mouse;
import Gprocessing.physics.Collisions;
import Gprocessing.physics.Transform;
import Gprocessing.util.Engine;

public class MousePicker extends Component {
    Transform p;

    public boolean mouseOver () {
        return Collisions.inRect(Mouse.mouseX, Mouse.mouseY, p.getX(), p.getY(), p.getWidth(), p.getHeight());
    }

    public boolean mousePressed () {
        if (mouseOver() && Mouse.mouseButtonDown(0)) {
            Engine.println("At X " + p.getX() + ", Y " + p.getY());
            return true;
        }
        return false;
    }

    @Override
    public void start () {
        p = gameObject.getTransform();
    }

    @Override
    public void update (float dt) {
//        mouseOver();

        mousePressed();
    }
}

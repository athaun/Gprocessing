package Gprocessing.chickenCoup;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Rectangle;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Color;
import Gprocessing.graphics.Spritesheet;
import Gprocessing.input.Gamepad;
import Gprocessing.physics.SquareCollision;
import Gprocessing.physics.Transform;
import Gprocessing.physics.Vector2;
import Gprocessing.util.Assets;
import Gprocessing.util.Engine;

public class Chicken {
    GameObject chicken;
    Vector2 lastPosition;
    Vector2 velocity = new Vector2(0, 100);
    Spritesheet s = new Spritesheet(Assets.getTexture("src/assets/images/chickens.png"), 24, 24, 8, 0);

    float gravity = 6;
    boolean grounded = false;

    GameObject square;

    Chicken (Transform tr) {
        chicken = new GameObject(tr);
        chicken.addComponent(new SpriteRenderer(s.getSprite(2)));

        square = new GameObject(chicken.getTransform());
        square.addComponent(new Rectangle(new Color(0, 0, 200, 100)));
    }

    public void update (Tilesystem ts) {

        float cWidth = chicken.getTransform().getWidth();
        float cHeight = chicken.getTransform().getHeight();
        float cX = chicken.getTransform().getX();
        float cY = chicken.getTransform().getY();

        lastPosition = new Vector2(cX, cY);

        // ---------------------------------

        chicken.getTransform().addY(velocity.y * Engine.deltaTime);

        if (Gamepad.buttonPressed(0, Gamepad.A) && grounded) {
            velocity.y = -600;
            grounded = false;
        }





        for (int x = 0; x < ts.gameObjects.length; x ++) {
            for (int y = 0; y < ts.gameObjects[0].length; y ++) {
                SquareCollision tile = ts.gameObjects[x][y].getComponent(SquareCollision.class);
//
//                if (tile.leftEdge()) {
//                    Engine.println("LEFT");
//                }
//
//                if (tile.rightEdge()) {
//                    Engine.println("RIGHT");
//                }
//
//                if (tile.topEdge()) {
//                    Engine.println("TOP");
//                }
//
//                if (tile.bottomEdge()) {
//                    Engine.println("BOTTOM");
//                }
            }
        }




        if (!grounded) {
//            velocity.y += gravity;
        }

        chicken.getTransform().addX(Gamepad.axis(0, Gamepad.LEFT_STICK_HORIZONTAL) * 240 * Engine.deltaTime);

        square.setTransform(chicken.getTransform());
    }

    public GameObject getGameObject () {
        return chicken;
    }
}

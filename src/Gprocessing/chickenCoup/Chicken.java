package Gprocessing.chickenCoup;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Rectangle;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Color;
import Gprocessing.graphics.Spritesheet;
import Gprocessing.input.Gamepad;
import Gprocessing.physics.TransformCollision;
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
    boolean collidingX = false;

    Chicken (Transform tr) {
        chicken = new GameObject(tr);
//        chicken.addComponent(new SpriteRenderer(s.getSprite(2)));
        chicken.addComponent(new SpriteRenderer("src/assets/images/blendImage1.png"));
    }

    float lastDelta = 0;

    public void update (Tilesystem ts) {

        float cX = chicken.getTransform().getX();
        float cY = chicken.getTransform().getY();

        lastPosition = new Vector2(cX, cY);

        // ---------------------------------

        if (!grounded) velocity.y += gravity;


        for (int x = 0; x < ts.gameObjects.length; x ++) {
            for (int y = 0; y < ts.gameObjects[0].length; y ++) {
                TransformCollision tile = ts.gameObjects[x][y].getComponent(TransformCollision.class);
                if (tile != null) {
                    if (tile.topEdge() && !tile.bottomEdge()) {
                        chicken.getTransform().addY(-velocity.y * lastDelta);
                        chicken.getTransform().setY(ts.gameObjects[x][y].getTransform().getY() - chicken.getTransform().getHeight());
                        velocity.y = 0;
                        grounded = true;
                        Engine.println("CALLED");
                    }

                    if (tile.leftEdge()) {
//                        chicken.getTransform().setX(ts.gameObjects[x][y].getTransform().getX() - chicken.getTransform().getX());

//                        chicken.getTransform().addX(-velocity.x * lastDelta);
//                        velocity.x = 0;
                        collidingX = true;
                    }
                }
            }
        }


        if (Gamepad.buttonPressed(0, Gamepad.A) && grounded) {
            velocity.y = -500;
            grounded = false;
            Engine.println("Jump");
        }

        chicken.getTransform().addY(velocity.y * Engine.deltaTime);
        chicken.getTransform().addX(velocity.x * Engine.deltaTime);
        if (!collidingX) velocity.x = Gamepad.axis(0, Gamepad.LEFT_STICK_HORIZONTAL) * 200;
        collidingX = false;
        grounded = false;

        lastDelta = Engine.deltaTime;
    }

    public GameObject getGameObject () {
        return chicken;
    }
}
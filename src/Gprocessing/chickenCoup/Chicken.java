package Gprocessing.chickenCoup;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Rectangle;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Color;
import Gprocessing.graphics.Spritesheet;
import Gprocessing.input.Gamepad;
import Gprocessing.input.Keyboard;
import Gprocessing.physics.TransformCollision;
import Gprocessing.physics.Transform;
import Gprocessing.physics.Vector2;
import Gprocessing.util.Assets;
import Gprocessing.util.Engine;

public class Chicken {
    GameObject chicken;
    Vector2 velocity = new Vector2(0, 100);
    Spritesheet s = new Spritesheet(Assets.getTexture("src/assets/images/chickens.png"), 24, 24, 8, 0);

    float gravity = 9;
    private boolean grounded = false;
    private Vector2 collisionIndex;
    private Vector2 lastPosition;
    private Tilesystem _ts;

    Chicken (Transform tr) {
        chicken = new GameObject(tr);
        chicken.addComponent(new SpriteRenderer(s.getSprite(2)));
//        chicken.addComponent(new SpriteRenderer("src/assets/images/blendImage1.png"));
        lastPosition = new Vector2(chicken.getTransform().getX(), chicken.getTransform().getY());
    }

    public void update (Tilesystem ts) {
        if (_ts == null) _ts = ts;

        moveX();
        collideX();

        moveY();
        collideY();
    }

    float sprintSpeed = 0;
    private void moveX () {
        lastPosition.x = chicken.getTransform().getX();
        chicken.getTransform().addX(velocity.x * Engine.deltaTime);

        if (Gamepad.buttonPressed(0, Gamepad.B) || Keyboard.keyIsPressed(Keyboard.SPACE)) {
            sprintSpeed = 200;
        } else {
            sprintSpeed = 0;
        }
        if (Keyboard.keyIsPressed(Keyboard.A_KEY) || Keyboard.keyIsPressed(Keyboard.LEFT_ARROW) || Keyboard.keyIsPressed(Keyboard.D_KEY) || Keyboard.keyIsPressed(Keyboard.RIGHT_ARROW)) {
            if (Keyboard.keyIsPressed(Keyboard.A_KEY) || Keyboard.keyIsPressed(Keyboard.LEFT_ARROW)) {
                velocity.x = -200 + sprintSpeed;
            }
            if (Keyboard.keyIsPressed(Keyboard.D_KEY) || Keyboard.keyIsPressed(Keyboard.RIGHT_ARROW)) {
                velocity.x = 200 + sprintSpeed;
            }
        } else {
            velocity.x = Gamepad.axis(0, Gamepad.LEFT_STICK_HORIZONTAL) * (200 + sprintSpeed);
        }
    }

    private void moveY () {
        lastPosition.y = chicken.getTransform().getY();
        if ((Gamepad.buttonPressed(0, Gamepad.A) || Keyboard.keyIsPressed(Keyboard.UP_ARROW) || Keyboard.keyIsPressed(Keyboard.W_KEY)) && grounded) {
            chicken.getTransform().addY(-1);
            velocity.y = -560;
            grounded = false;
            Engine.println("Jump");
        }
        velocity.y += gravity;
        chicken.getTransform().addY(velocity.y * Engine.deltaTime);
    }

    private void collideX () {
        if (this.checkCollision()) {
            Transform currentTile = _ts.gameObjects[(int)collisionIndex.x][(int)collisionIndex.y].getTransform();
            if (lastPosition.x < currentTile.getX()) {
                chicken.setTransformX(currentTile.getX() - chicken.getTransform().getWidth());
            } else {
                chicken.setTransformX(currentTile.getX() + currentTile.getWidth());
            }
        }
    }

    private void collideY () {
        this.grounded = false;
        if (this.checkCollision()) {
            Transform currentTile = _ts.gameObjects[(int) collisionIndex.x][(int) collisionIndex.y].getTransform();
            if (lastPosition.y < currentTile.getY()) {
                chicken.setTransformY(currentTile.getY() - chicken.getTransform().getHeight());
                velocity.y = 0;
                this.grounded = true;
            } else {
                chicken.setTransformY(currentTile.getY() + currentTile.getHeight());
                velocity.y = -velocity.y/4;
            }
        }
    }

    private boolean checkCollision () {
        for (int x = 0; x < _ts.gameObjects.length; x ++) {
            for (int y = 0; y < _ts.gameObjects[0].length; y++) {
                TransformCollision tile = _ts.gameObjects[x][y].getComponent(TransformCollision.class);
                if (tile != null) {
                    if (tile.collide()) {
                        // not passing chicken to .collide because chicken passed in constructor of each tile
                        collisionIndex = new Vector2(x, y);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public GameObject getGameObject () {
        return chicken;
    }
}

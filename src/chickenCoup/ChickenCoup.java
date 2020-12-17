package chickenCoup;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.Spritesheet;
import Gprocessing.physics.Transform;
import Gprocessing.util.Assets;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;

import static Gprocessing.graphics.Graphics.background;

public class ChickenCoup extends Scene {
    public static void main (String[] args) {
        Engine.init(1920, 1080, "Chicken Coup", false);
    }

    Spritesheet mesa;
    GameObject s = new GameObject(new Transform(0, 0, 100, 100));

    GameObject a = new GameObject(new Transform(100, 100, 100, 100));
    GameObject b = new GameObject(new Transform(150, 150, 100, 100));
    public void awake() {
        camera = new Camera();
        mesa = new Spritesheet(Assets.getTexture("src/assets/images/pepper.png"), 512, 512, 20, 0);
        s.addComponent(new SpriteRenderer(mesa.getSprite(0)));

        a.addComponent(new SpriteRenderer("src/assets/images/pepper.png"));
        b.addComponent(new SpriteRenderer("src/assets/images/pepper.png"));
    }

    public void update() {
        background(107, 134, 136);

    }
}

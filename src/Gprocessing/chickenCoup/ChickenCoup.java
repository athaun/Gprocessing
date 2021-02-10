package Gprocessing.chickenCoup;

import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.Rectangle;
import Gprocessing.ecs.Sprite;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Camera;
import Gprocessing.graphics.Color;
import Gprocessing.graphics.Spritesheet;
import Gprocessing.graphics.Window;
import Gprocessing.input.Gamepad;
import Gprocessing.physics.Transform;
import Gprocessing.physics.TransformCollision;
import Gprocessing.physics.Vector2;
import Gprocessing.util.Assets;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;
import Gprocessing.util.Utils;
import imgui.ImGui;
import imgui.ImVec2;
import imgui.type.ImString;
import org.joml.Vector2f;

import java.util.ArrayList;

import static Gprocessing.graphics.Graphics.background;

public class ChickenCoup extends Scene {

    public static void main (String[] args) {
        Engine.init(1920, 1080, "Chicken Coup", true);
    }

    Spritesheet s;
    Tilesystem t;
    ArrayList<GameObject> images = new ArrayList<>();

    public Chicken chicken;

    public void awake() {
        camera = new Camera();

        chicken = new Chicken(new Transform(1400, 500, Window.getWidth()/16, Window.getHeight()/9));

        s = new Spritesheet(Assets.getTexture("src/assets/images/platformer.png"), 8, 8, 26, 0);
        t = new Tilesystem(s, 90, 10, Window.getWidth()/16, Window.getHeight()/9, chicken);
    }

    public void update() {
        background(41, 30, 49);

        smoothCameraFollow();
        chicken.update(t);
    }

    private void smoothCameraFollow () {
        Transform c = chicken.getGameObject().getTransform();

        float smoothing = 0.045f;
        Vector2f desiredPosition = new Vector2f(c.getX() - Window.getWidth()/2,c.getY() - Window.getHeight()/3*2);
        Vector2f smoothedPosition = new Vector2f(Utils.lerp(camera().position.x, desiredPosition.x, smoothing), Utils.lerp(camera().position.y, desiredPosition.y, smoothing));
        if (Utils.dist(desiredPosition, camera().position) < 15) {
            camera().position = desiredPosition;
        }
        camera().position = smoothedPosition;
    }

    public GameObject getChicken () {
        return chicken.getGameObject();
    }

    ImString test = new ImString("Hello!");

    public void imgui () {
        ImGui.inputTextMultiline("AYYY!", test);

        ImVec2 windowPos = new ImVec2();
        ImGui.getWindowPos(windowPos);
        ImVec2 windowSize = new ImVec2();
        ImGui.getWindowSize(windowSize);
        ImVec2 itemSpacing = new ImVec2();
        ImGui.getStyle().getItemSpacing(itemSpacing);

        float windowX2 = windowPos.x + windowSize.x;
        Spritesheet sprites = s;
        for (int i=0; i < sprites.getSprites().size(); i++) {
            Sprite sprite = sprites.getSprite(i);
            float spriteWidth = sprite.getWidth();
            float spriteHeight = sprite.getHeight();
            int id = sprite.getTextureID();
            Vector2f[] texCoords = sprite.getTextureCoordinates();

            ImGui.pushID(i);
            ImGui.text((i < 10? "0":"") +  i + "");
            ImGui.sameLine();
            ImGui.image(id, spriteWidth, spriteHeight, texCoords[2].x, texCoords[0].y, texCoords[0].x, texCoords[2].y);
            ImGui.popID();

            ImVec2 lastButtonPos = new ImVec2();
            ImGui.getItemRectMax(lastButtonPos);
            float lastButtonX2 = lastButtonPos.x;
            float nextButtonX2 = lastButtonX2 + itemSpacing.x + spriteWidth;
            if (i + 1 < sprites.getSprites().size() && nextButtonX2 < windowX2) {
                ImGui.sameLine();
            }
        }
    }
}

package Gprocessing.porfolioManager;

import Gprocessing.ImGui.ImGuiLayer;
import Gprocessing.ecs.GameObject;
import Gprocessing.ecs.SpriteRenderer;
import Gprocessing.graphics.Camera;
import Gprocessing.physics.Transform;
import Gprocessing.util.Engine;
import Gprocessing.util.Scene;
import imgui.ImGui;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;

import static imgui.ImGui.*;

import static Gprocessing.graphics.Graphics.background;

public class PortfolioManager extends Scene {

    public static void main (String[] args) {
        Engine.init(1920, 1080, "Portfolio");
    }


    public void awake() {
//        camera = new Camera();
    }

    public void update() {
        background(50, 50, 50);
    }

    public void imgui () {
        ImGui.begin("Test", ImGuiWindowFlags.NoTitleBar);
        setNextWindowPos(0, 0);
        text("Hello World!");
        ImGui.end();
    }
}


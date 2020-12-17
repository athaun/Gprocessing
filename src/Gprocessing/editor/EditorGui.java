package Gprocessing.editor;

import Gprocessing.ecs.Component;
import Gprocessing.ecs.GameObject;
import Gprocessing.graphics.Window;
import imgui.ImGui;

public class EditorGui {

    private int selectedId = -1;

    private GameObject getGameObject (int i) {
        return ((GameObject) (Window.currentScene.getGameObjects().get(i)));
    }

    float[] xyz = {0, 0, 0};
    float[] wh = {0, 0};
    boolean setTempProperties = true;

    void transformEditor (GameObject g) {
        if (!ImGui.collapsingHeader("Transform")) {

            ImGui.dragFloat3("X, Y, Z", xyz);
            g.getTransform().setX(xyz[0]);
            g.getTransform().setY(xyz[1]);
            g.setZindex((int) xyz[2]);

            ImGui.dragFloat2("W, H", wh);
			g.getTransform().setWidth(wh[0]);
			g.getTransform().setHeight(wh[1]);
        }
    }

    public void drawGui() {
        ImGui.begin("Object Editor");

        // Game Objects List
        ImGui.beginChild("leftCol", ImGui.getWindowContentRegionMaxX() * 0.3f, ImGui.getWindowContentRegionMaxY() - 40);

        if (ImGui.beginTabBar("Game Object list"))
        {
            if (ImGui.beginTabItem("GameObjects"))
            {
                ImGui.beginChild("Listbox");
                for (int i = 0; i < Window.currentScene.getGameObjects().size(); i++) {
                    if (ImGui.selectable(getGameObject(i).getName(), selectedId == i)) {
                        selectedId = i;
                        setTempProperties = true;
                    }
                }
                ImGui.endChild();
                ImGui.endTabItem();
            }
            ImGui.endTabBar();
        }

        ImGui.endChild();
        ImGui.sameLine();
        // Component Editor
        ImGui.beginChild("rightCol", 0, ImGui.getWindowContentRegionMaxY() - 40);

            if (selectedId != -1) {
                // Game Object is selected
                GameObject g = getGameObject(selectedId);
                if (setTempProperties) {
                    xyz[0] = g.getTransform().getX();
                    xyz[1] = g.getTransform().getY();
                    xyz[2] = g.zIndex();
                    wh[0] = g.getTransform().getWidth();
                    wh[1] = g.getTransform().getHeight();
                    setTempProperties = false;
                }

                if (ImGui.beginTabBar("Editor")) {
                    if (ImGui.beginTabItem("Components")) {

                        ImGui.text("Name: " + g.getName());

                        transformEditor(g);

                        for (Component c : g.getComponents()) {
                            c.ImGuiEditorWidget();
                        }

                        ImGui.endTabItem();
                    }
                    if (ImGui.beginTabItem("Description")) {
                        ImGui.textWrapped("Lorem Ipsum set dolar amet, bla. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ");
                        ImGui.endTabItem();
                    }
                    ImGui.endTabBar();
                }
            } else {
                if (ImGui.beginTabBar("EditorEmpty")) {
                    if (ImGui.beginTabItem("Components")) {
                        ImGui.textColored(255, 255, 0, 255, "Select a gameObject to edit it's properties.");
                        ImGui.endTabItem();
                    }
                    ImGui.endTabBar();
                }

            }

        ImGui.endChild();
        ImGui.end();
        ImGui.showDemoWindow();
    }
}

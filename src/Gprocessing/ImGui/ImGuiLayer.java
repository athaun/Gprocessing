package Gprocessing.ImGui;

import imgui.*;
import imgui.flag.*;
import imgui.gl3.ImGuiImplGl3;
import imgui.callback.ImStrConsumer;
import imgui.callback.ImStrSupplier;
import static org.lwjgl.glfw.GLFW.*;
import Gprocessing.graphics.Window;
import Gprocessing.util.Scene;

public class ImGuiLayer {

	private long glfwWindow;

	// Mouse cursors provided by GLFW
	private final long[] mouseCursors = new long[ImGuiMouseCursor.COUNT];

	// LWJGL3 renderer (SHOULD be initialized)
	private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

	public ImGuiLayer(long glfwWindow) {
		this.glfwWindow = glfwWindow;
	}

	// Initialize Dear ImGui.
	public void initImGui() {
		// IMPORTANT!!
		// This line is critical for Dear ImGui to work.
		ImGui.createContext();

		// ------------------------------------------------------------
		// Initialize ImGuiIO config
		final ImGuiIO io = ImGui.getIO();

		io.setIniFilename("imgui.ini"); // We don't want to save .ini file
		io.setConfigFlags(ImGuiConfigFlags.NavEnableKeyboard); // Navigation with keyboard
		io.setBackendFlags(ImGuiBackendFlags.HasMouseCursors); // Mouse cursors to display while resizing windows etc.
		io.setBackendPlatformName("imgui_java_impl_glfw");

		// ------------------------------------------------------------
		// Keyboard mapping. ImGui will use those indices to peek into the io.KeysDown[]
		// array.
		final int[] keyMap = new int[ImGuiKey.COUNT];
		keyMap[imgui.flag.ImGuiKey.Tab] = GLFW_KEY_TAB;
		keyMap[ImGuiKey.LeftArrow] = GLFW_KEY_LEFT;
		keyMap[ImGuiKey.RightArrow] = GLFW_KEY_RIGHT;
		keyMap[ImGuiKey.UpArrow] = GLFW_KEY_UP;
		keyMap[ImGuiKey.DownArrow] = GLFW_KEY_DOWN;
		keyMap[ImGuiKey.PageUp] = GLFW_KEY_PAGE_UP;
		keyMap[ImGuiKey.PageDown] = GLFW_KEY_PAGE_DOWN;
		keyMap[ImGuiKey.Home] = GLFW_KEY_HOME;
		keyMap[ImGuiKey.End] = GLFW_KEY_END;
		keyMap[ImGuiKey.Insert] = GLFW_KEY_INSERT;
		keyMap[ImGuiKey.Delete] = GLFW_KEY_DELETE;
		keyMap[ImGuiKey.Backspace] = GLFW_KEY_BACKSPACE;
		keyMap[ImGuiKey.Space] = GLFW_KEY_SPACE;
		keyMap[ImGuiKey.Enter] = GLFW_KEY_ENTER;
		keyMap[ImGuiKey.Escape] = GLFW_KEY_ESCAPE;
		keyMap[ImGuiKey.KeyPadEnter] = GLFW_KEY_KP_ENTER;
		keyMap[ImGuiKey.A] = GLFW_KEY_A;
		keyMap[ImGuiKey.C] = GLFW_KEY_C;
		keyMap[ImGuiKey.V] = GLFW_KEY_V;
		keyMap[ImGuiKey.X] = GLFW_KEY_X;
		keyMap[ImGuiKey.Y] = GLFW_KEY_Y;
		keyMap[ImGuiKey.Z] = GLFW_KEY_Z;
		io.setKeyMap(keyMap);

		// ------------------------------------------------------------
		// Mouse cursors mapping
		mouseCursors[ImGuiMouseCursor.Arrow] = glfwCreateStandardCursor(GLFW_ARROW_CURSOR);
		mouseCursors[ImGuiMouseCursor.TextInput] = glfwCreateStandardCursor(GLFW_IBEAM_CURSOR);
		mouseCursors[ImGuiMouseCursor.ResizeAll] = glfwCreateStandardCursor(GLFW_ARROW_CURSOR);
		mouseCursors[ImGuiMouseCursor.ResizeNS] = glfwCreateStandardCursor(GLFW_VRESIZE_CURSOR);
		mouseCursors[ImGuiMouseCursor.ResizeEW] = glfwCreateStandardCursor(GLFW_HRESIZE_CURSOR);
		mouseCursors[ImGuiMouseCursor.ResizeNESW] = glfwCreateStandardCursor(GLFW_ARROW_CURSOR);
		mouseCursors[ImGuiMouseCursor.ResizeNWSE] = glfwCreateStandardCursor(GLFW_ARROW_CURSOR);
		mouseCursors[ImGuiMouseCursor.Hand] = glfwCreateStandardCursor(GLFW_HAND_CURSOR);
		mouseCursors[ImGuiMouseCursor.NotAllowed] = glfwCreateStandardCursor(GLFW_ARROW_CURSOR);

		// ------------------------------------------------------------
		// GLFW callbacks to handle user input

		glfwSetKeyCallback(glfwWindow, (w, key, scancode, action, mods) -> {
			if (action == GLFW_PRESS) {
				io.setKeysDown(key, true);
			} else if (action == GLFW_RELEASE) {
				io.setKeysDown(key, false);
			}

			io.setKeyCtrl(io.getKeysDown(GLFW_KEY_LEFT_CONTROL) || io.getKeysDown(GLFW_KEY_RIGHT_CONTROL));
			io.setKeyShift(io.getKeysDown(GLFW_KEY_LEFT_SHIFT) || io.getKeysDown(GLFW_KEY_RIGHT_SHIFT));
			io.setKeyAlt(io.getKeysDown(GLFW_KEY_LEFT_ALT) || io.getKeysDown(GLFW_KEY_RIGHT_ALT));
			io.setKeySuper(io.getKeysDown(GLFW_KEY_LEFT_SUPER) || io.getKeysDown(GLFW_KEY_RIGHT_SUPER));
		});

		glfwSetCharCallback(glfwWindow, (w, c) -> {
			if (c != GLFW_KEY_DELETE) {
				io.addInputCharacter(c);
			}
		});

		glfwSetMouseButtonCallback(glfwWindow, (w, button, action, mods) -> {
			final boolean[] mouseDown = new boolean[5];

			mouseDown[0] = button == GLFW_MOUSE_BUTTON_1 && action != GLFW_RELEASE;
			mouseDown[1] = button == GLFW_MOUSE_BUTTON_2 && action != GLFW_RELEASE;
			mouseDown[2] = button == GLFW_MOUSE_BUTTON_3 && action != GLFW_RELEASE;
			mouseDown[3] = button == GLFW_MOUSE_BUTTON_4 && action != GLFW_RELEASE;
			mouseDown[4] = button == GLFW_MOUSE_BUTTON_5 && action != GLFW_RELEASE;

			io.setMouseDown(mouseDown);

			if (!io.getWantCaptureMouse() && mouseDown[1]) {
				ImGui.setWindowFocus(null);
			}
		});

		glfwSetScrollCallback(glfwWindow, (w, xOffset, yOffset) -> {
			io.setMouseWheelH(io.getMouseWheelH() + (float) xOffset);
			io.setMouseWheel(io.getMouseWheel() + (float) yOffset);
		});

		io.setSetClipboardTextFn(new ImStrConsumer() {
			@Override
			public void accept(final String s) {
				glfwSetClipboardString(glfwWindow, s);
			}
		});

		io.setGetClipboardTextFn(new ImStrSupplier() {
			@Override
			public String get() {
				final String clipboardString = glfwGetClipboardString(glfwWindow);
				if (clipboardString != null) {
					return clipboardString;
				}
				return "";
			}
		});

		// ------------------------------------------------------------
		// Fonts configuration
		// Read: https://raw.githubusercontent.com/ocornut/imgui/master/docs/FONTS.txt

        final ImFontAtlas fontAtlas = io.getFonts();
        final ImFontConfig fontConfig = new ImFontConfig(); // Natively allocated object, should be explicitly destroyed

        // Glyphs could be added per-font as well as per config used globally like here
        fontConfig.setGlyphRanges(fontAtlas.getGlyphRangesDefault());
        fontConfig.setPixelSnapH(true);
        fontAtlas.addFontFromFileTTF("src/assets/fonts/roboto.ttf", 15, fontConfig);

        fontConfig.destroy(); // After all fonts were added we don't need this config more

        // ------------------------------------------------------------
        // Use freetype instead of stb_truetype to build a fonts texture
        ImGuiFreeType.buildFontAtlas(fontAtlas, ImGuiFreeType.RasterizerFlags.LightHinting);
        
        
        // Theme Test --------------------------------------------
        ImGuiStyle style = ImGui.getStyle();        
        
        style.setFrameRounding(4);
        style.setGrabRounding(4);
       
        style.setPopupRounding(3);

        style.setWindowPadding(4, 4);
        style.setFramePadding(6, 4);
        style.setItemSpacing(6, 2);

        style.setScrollbarSize(18);

        style.setChildBorderSize(0);
        style.setPopupBorderSize(0);
        style.setFrameBorderSize(0); 

        style.setWindowRounding(3);
        style.setChildRounding(3);
        style.setScrollbarRounding(2);

        style.setColor(ImGuiCol.Text, 255, 255, 255, 255);
        style.setColor(ImGuiCol.TextDisabled, 255, 255, 255, 255);
		
        style.setColor(ImGuiCol.Text, 1.00f, 1.00f, 1.00f, 1.00f);
        style.setColor(ImGuiCol.TextDisabled, 0.40f, 0.40f, 0.40f, 1.00f);
        style.setColor(ImGuiCol.ChildBg, 0.25f, 0.25f, 0.25f, 1.00f);
        style.setColor(ImGuiCol.WindowBg, 0.25f, 0.25f, 0.25f, 1.00f);
        style.setColor(ImGuiCol.PopupBg, 0.25f, 0.25f, 0.25f, 1.00f);
        style.setColor(ImGuiCol.Border, 0.12f, 0.12f, 0.12f, 0.71f);
        style.setColor(ImGuiCol.BorderShadow, 1.00f, 1.00f, 1.00f, 0.06f);
        style.setColor(ImGuiCol.FrameBg, 0.42f, 0.42f, 0.42f, 0.54f);
        style.setColor(ImGuiCol.FrameBgHovered, 0.42f, 0.42f, 0.42f, 0.40f);
        style.setColor(ImGuiCol.FrameBgActive, 0.56f, 0.56f, 0.56f, 0.67f);
        style.setColor(ImGuiCol.TitleBg, 0.19f, 0.19f, 0.19f, 1.00f);
        style.setColor(ImGuiCol.TitleBgActive, 0.22f, 0.22f, 0.22f, 1.00f);
        style.setColor(ImGuiCol.TitleBgCollapsed, 0.17f, 0.17f, 0.17f, 0.90f);
        style.setColor(ImGuiCol.MenuBarBg, 0.335f, 0.335f, 0.335f, 1.000f);
        style.setColor(ImGuiCol.ScrollbarBg, 0.24f, 0.24f, 0.24f, 0.53f);
        style.setColor(ImGuiCol.ScrollbarGrab, 0.41f, 0.41f, 0.41f, 1.00f);
        style.setColor(ImGuiCol.ScrollbarGrabHovered, 0.52f, 0.52f, 0.52f, 1.00f);
        style.setColor(ImGuiCol.ScrollbarGrabActive, 0.76f, 0.76f, 0.76f, 1.00f);
        style.setColor(ImGuiCol.CheckMark, 0.65f, 0.65f, 0.65f, 1.00f);
        style.setColor(ImGuiCol.SliderGrab, 0.52f, 0.52f, 0.52f, 1.00f);
        style.setColor(ImGuiCol.SliderGrabActive, 0.64f, 0.64f, 0.64f, 1.00f);
        style.setColor(ImGuiCol.Button, 0.54f, 0.54f, 0.54f, 0.35f);
        style.setColor(ImGuiCol.ButtonHovered, 0.52f, 0.52f, 0.52f, 0.59f);
        style.setColor(ImGuiCol.ButtonActive, 0.76f, 0.76f, 0.76f, 1.00f);
        style.setColor(ImGuiCol.Header, 0.38f, 0.38f, 0.38f, 1.00f);
        style.setColor(ImGuiCol.HeaderHovered, 0.47f, 0.47f, 0.47f, 1.00f);
        style.setColor(ImGuiCol.HeaderActive, 0.76f, 0.76f, 0.76f, 0.77f);
        style.setColor(ImGuiCol.Separator, 0.000f, 0.000f, 0.000f, 0.137f);
        style.setColor(ImGuiCol.SeparatorHovered, 0.700f, 0.671f, 0.600f, 0.290f);
        style.setColor(ImGuiCol.SeparatorActive, 0.702f, 0.671f, 0.600f, 0.674f);
        style.setColor(ImGuiCol.ResizeGrip, 0.26f, 0.59f, 0.98f, 0.25f);
        style.setColor(ImGuiCol.ResizeGripHovered, 0.26f, 0.59f, 0.98f, 0.67f);
        style.setColor(ImGuiCol.ResizeGripActive, 0.26f, 0.59f, 0.98f, 0.95f);
        style.setColor(ImGuiCol.PlotLines, 0.61f, 0.61f, 0.61f, 1.00f);
        style.setColor(ImGuiCol.PlotLinesHovered, 1.00f, 0.43f, 0.35f, 1.00f);
        style.setColor(ImGuiCol.PlotHistogram, 0.90f, 0.70f, 0.00f, 1.00f);
        style.setColor(ImGuiCol.PlotHistogramHovered, 1.00f, 0.60f, 0.00f, 1.00f);
        style.setColor(ImGuiCol.TextSelectedBg, 0.73f, 0.73f, 0.73f, 0.35f);
        style.setColor(ImGuiCol.ModalWindowDimBg, 0.80f, 0.80f, 0.80f, 0.35f);
        style.setColor(ImGuiCol.DragDropTarget, 1.00f, 1.00f, 0.00f, 0.90f);
        style.setColor(ImGuiCol.NavHighlight, 0.26f, 0.59f, 0.98f, 1.00f);
        style.setColor(ImGuiCol.NavWindowingHighlight, 1.00f, 1.00f, 1.00f, 0.70f);
        style.setColor(ImGuiCol.NavWindowingDimBg, 0.80f, 0.80f, 0.80f, 0.20f);

		// Method initializes LWJGL3 renderer.
		// This method SHOULD be called after you've initialized your ImGui
		// configuration (fonts and so on).
		// ImGui context should be created as well.
		imGuiGl3.init("#version 330 core");
	}

	public void update(float dt, Scene currentScene) {
		startFrame(dt);

		// Any Dear ImGui code SHOULD go between ImGui.newFrame()/ImGui.render() methods
		ImGui.newFrame();
		currentScene.sceneImgui();
		ImGui.showDemoWindow();
		ImGui.render();

		endFrame();
	}

	private void startFrame(final float deltaTime) {
		// Get window properties and mouse position
		float[] winWidth = { Window.getWidth() };
		float[] winHeight = { Window.getHeight() };
		double[] mousePosX = { 0 };
		double[] mousePosY = { 0 };
		glfwGetCursorPos(glfwWindow, mousePosX, mousePosY);

		// We SHOULD call those methods to update Dear ImGui state for the current frame
		final ImGuiIO io = ImGui.getIO();
		io.setDisplaySize(winWidth[0], winHeight[0]);
		io.setDisplayFramebufferScale(1f, 1f);
		io.setMousePos((float) mousePosX[0], (float) mousePosY[0]);
		io.setDeltaTime(deltaTime);

		// Update the mouse cursor
		final int imguiCursor = ImGui.getMouseCursor();
		glfwSetCursor(glfwWindow, mouseCursors[imguiCursor]);
		glfwSetInputMode(glfwWindow, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
	}

	private void endFrame() {
		// After Dear ImGui prepared a draw data, we use it in the LWJGL3 renderer.
		// At that moment ImGui will be rendered to the current OpenGL context.
		imGuiGl3.render(ImGui.getDrawData());
	}

	// If you want to clean a room after yourself - do it by yourself
	private void destroyImGui() {
		imGuiGl3.dispose();
		ImGui.destroyContext();
	}
}
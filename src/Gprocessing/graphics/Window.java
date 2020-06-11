package Gprocessing.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;
import Gprocessing.Main;
import Gprocessing.input.*;
import Gprocessing.util.Engine;
import static Gprocessing.util.Engine.*;

public class Window {

	public long frameCount = 0;

	String title;
	public static long window;
	private GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

	int width;
	int height;

	double deltaTime = 0;
	
	static Shader defaultShader;

	public Window(int pwidth, int pheight, String ptitle) {

		width = pwidth;
		height = pheight;
		title = ptitle;

		// Configure GLFW
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

		// Create window
		window = glfwCreateWindow(width, height, title, 0, 0);

		if (window == 0)
			throw new IllegalStateException("[FATAL] Failed to create window.");

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);

		// Enable V-Sync
		glfwSwapInterval(1);
		
		glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
		
	}

	void getFPS() {
		frameCount++;
		glfwSetWindowTitle(window, "GProcessing @ " + Math.round((frameCount / (Engine.millis() / 1000))) + " FPS");
	}

	public void setTitle(String title) {
		glfwSetWindowTitle(window, title);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	public static float[] vertexArray = {
		// position		color
		 0.5f, -0.5f, 0.0f,		1.0f, 0.0f, 0.0f, 1.0f, // 0 - bottom right
		-0.5f,  0.5f, 0.0f, 	0.0f, 1.0f, 0.0f, 1.0f, // 1 - top left
		 0.5f,  0.5f, 0.0f, 	0.0f, 0.0f, 1.0f, 1.0f, // 2 - top right
		-0.5f, -0.5f, 0.0f,		1.0f, 1.0f, 0.0f, 1.0f 	// 3 - bottom left
	};
	
	// counterclockwise order
	public static int[] elementArray = {
		2, 1, 0, // top right triangle
		0, 1, 3  // bottom left triangle
	};

	public static void initShaders() {
		defaultShader = new Shader("src/assets/shaders/default.glsl");
		defaultShader.compile();
		
		
		GL.createCapabilities();
		
		
		// Generate VAO, VPO, an EBO buffer objects, and send to GPU
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		// create a float buffer of vertices
		FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
		vertexBuffer.put(vertexArray).flip();
		
		// Create VBO, and upload vertex buffer
		vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
		
		// create the indices and upload
		IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
		elementBuffer.put(elementArray).flip();
		
		eboID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);
		
		// Add vertex attribute pointers
		int positionSize = 3;
		int colorSize = 4;
		int floatSize = 4; // in bytes
		int vertexSizeBytes = (positionSize + colorSize) * floatSize;
		
		glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0); // at position 0 in the shader, put position
		glEnableVertexAttribArray(0);
		
		glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * floatSize); // at position 1 in the shader, put color
		glEnableVertexAttribArray(1);
	}
	
	// for drawing
	public static int vaoID, vboID, eboID;	
	
	public static void drawShape () {
		defaultShader.use();
		// bing the VAO
		glBindVertexArray(vaoID);
		// enable vertex attribute pointers
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);
		
		// unbind everything
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		
		glBindVertexArray(0);
		
		defaultShader.detach();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public void showWindow() {
		glfwShowWindow(window);
		GL.createCapabilities();

//		glOrtho(0, width, height, 0, -1, 1);

//		Graphics.background(0);
//		Graphics.fill(255);

		double frameBeginTime = millis();
		double frameEndTime = millis();

		
		initShaders();
		
		
		while (!glfwWindowShouldClose(window)) {

			Mouse.update();

			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);

			Main.update();
			
			glfwSwapBuffers(window);
			getFPS();

			frameEndTime = millis();
			deltaTime = frameEndTime - frameBeginTime;
			frameBeginTime = frameEndTime;
		}

		glfwDestroyWindow(window);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	};
}
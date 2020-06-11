package Gprocessing.graphics;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_INFO_LOG_LENGTH;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUseProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Gprocessing.util.Engine;

public class Shader {

	private int shaderProgramID;

	// Setup shaders
	public static int vertexID;
	public static int fragmentID;

	private String vertexSource;
	private String fragmentSource;
	private String filepath;

	public Shader(String filePath) {
		this.filepath = filePath;
		try {
			
			String source = new String(Files.readAllBytes(Paths.get(filepath)));
			String[] splitString = source.split("(#type)( )+([a-zA-Z]+)");

			// Find first pattern after #type
			int index = source.indexOf("#type") + 6;
			int endOfLine = source.indexOf("\n", index); // \r\n on windows
			String firstPattern = source.substring(index, endOfLine).trim();

			// find the second pattern after #type
			index = source.indexOf("#type", endOfLine) + 6;
			endOfLine = source.indexOf("\n", index);
			String secondPattern = source.substring(index, endOfLine).trim();

			if (firstPattern.equals("vertex")) {
				vertexSource = splitString[1];
			} else if (firstPattern.equals("fragment")) {
				fragmentSource = splitString[1];
			} else {
				throw new IOException("Unexpected token \"" + firstPattern + "\"");
			}

			if (secondPattern.equals("vertex")) {
				vertexSource = splitString[2];
			} else if (secondPattern.equals("fragment")) {
				fragmentSource = splitString[2];
			} else {
				throw new IOException("Unexpected token \"" + secondPattern + "\"");
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			assert false : "[ERROR] could not open shader file at \"" + filepath + "\"";
		
		}
	}

	public void compile() {
		// load and compile vertex shader
		vertexID = glCreateShader(GL_VERTEX_SHADER);
		// Add the shader source to the GPU
		glShaderSource(vertexID, vertexSource);
		glCompileShader(vertexID);

		// Check for errors
		int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
		if (success == GL_FALSE) {
			int length = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
			Engine.println("[FATAL] Vertex shader compilation failed. Gprocessing.graphics.Window.initShaders()\n\t");
			Engine.println(glGetShaderInfoLog(vertexID, length));
			assert false : "";
		}

		// load and compile vertex shader
		fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
		// Add the shader source to the GPU
		glShaderSource(fragmentID, fragmentSource);
		glCompileShader(fragmentID);

		// Check for errors
		success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
		if (success == GL_FALSE) {
			int length = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
			Engine.println("[FATAL] Fragment shader compilation failed. Gprocessing.graphics.Window.initShaders()\n\t");
			Engine.println(glGetShaderInfoLog(fragmentID, length));
			assert false : "";
		}

		// Compile and link shaders
		shaderProgramID = glCreateProgram();
		glAttachShader(shaderProgramID, vertexID);
		glAttachShader(shaderProgramID, fragmentID);
		glLinkProgram(shaderProgramID);

		success = glGetProgrami(shaderProgramID, GL_LINK_STATUS);
		if (success == GL_FALSE) {
			int length = glGetProgrami(shaderProgramID, GL_INFO_LOG_LENGTH);
			Engine.println("[FATAL] Shader linking failed. Gprocessing.graphics.Window.initShaders()\n\t");
			Engine.println(glGetProgramInfoLog(shaderProgramID, length));
			assert false : "";
		}
	}

	public void use() {
		// bind shader program
		glUseProgram(shaderProgramID);
	}

	public void detach() {
		glUseProgram(0);
	}
}

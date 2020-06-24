package Gprocessing.util;

import java.io.File;
import java.util.HashMap;
import Gprocessing.graphics.Shader;
import Gprocessing.graphics.Texture;

public class Assets {
	private static HashMap<String, Shader> shaders = new HashMap<>();
	private static HashMap<String, Texture> textures = new HashMap<>();

	public static Shader getShader(String path) {
		File file = new File(path);
		if (shaders.containsKey(file.getAbsolutePath())) {
			return shaders.get(file.getAbsolutePath());
		}
		Shader shader = new Shader(path);
		shader.compile();
		shaders.put(file.getAbsolutePath(), shader);
		return shader;
	}

	public static Texture getTexture(String path) {
		File file = new File(path);
		if (textures.containsKey(file.getAbsolutePath())) {
			return textures.get(file.getAbsolutePath());
		}
		Texture texture = new Texture(path);
		textures.put(file.getAbsolutePath(), texture);
		return texture;
	}
}

#type vertex
#version 330 core
layout (location=0) in vec3 positionAttribute;
layout (location=1) in vec4 colorAttribute;

uniform mat4 uProjection;
uniform mat4 uView;

out vec4 colorFragment;

void main() {
	colorFragment = colorAttribute;
	gl_Position = uProjection * uView * vec4(positionAttribute, 1.0);
}

#type fragment
#version 330 core

in vec4 colorFragment;

out vec4 color;

void main () {
	color = colorFragment;
}

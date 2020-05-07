package Gprocessing.font;

import org.lwjgl.opengl.GL11;

public class Font {

	public static void drawString(String s, float x, float y, float scale, float width) {
		float startX = x;
		scale = scale * 0.25f;
		GL11.glLineWidth(width);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glEnable(GL11.GL_LINE_WIDTH);
		GL11.glColor3f(1, 1, 1);
		boolean space = false;
		int charNum = 1, spaceNum = 0;
		for (char c : s.toLowerCase().toCharArray()) {
			switch (c) {
			case ' ':
				space = true;
				break;
			case '\n':
				y -= 2;
				startX -= charNum * (scale * 5);
				startX -= spaceNum * (scale * 3);
				spaceNum = 0;
				charNum = 0;
				break;
			case 'a':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + -1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + -1));
				GL11.glVertex2f(scale * startX, scale * (y + -1));
				GL11.glVertex2f(scale * startX, scale * (y + -1));
				GL11.glVertex2f(scale * startX, scale * y);
				GL11.glVertex2f(scale * startX, scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * startX, scale * y);
				GL11.glVertex2f(scale * startX, scale * (y + 0.5f));
				GL11.glVertex2f(scale * startX, scale * (y + 0.5f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.5f));
				break;
			case 'b':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				break;
			case 'd':
				GL11.glVertex2f(scale * startX, scale * (y - 1));
				GL11.glVertex2f(scale * startX, scale * (y + 0.8f));
			case 'c':
				GL11.glVertex2f(scale * startX, scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * startX, scale * (y - 1));
				break;
			case 'e':
				GL11.glVertex2f(scale * startX, scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * startX, scale * y);
				GL11.glVertex2f(scale * startX, scale * (y - 0.5f));
				GL11.glVertex2f(scale * startX, scale * (y - 0.5f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 0.5f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 0.5f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 0.5f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				break;
			case 'f':
				GL11.glVertex2f(scale * (startX + 0.25f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.25f));
				GL11.glVertex2f(scale * (startX), scale * (y + 0.25f));
				break;
			case 'g':
				GL11.glVertex2f(scale * (startX), scale * y);
				GL11.glVertex2f(scale * (startX), scale * (y - 1.5f));
				GL11.glVertex2f(scale * (startX), scale * (y - 1.5f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1.5f));
				GL11.glVertex2f(scale * (startX), scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1f));
				break;
			case 'h':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				break;
			case 'i':
				GL11.glVertex2f(scale * (startX - 0.25f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y - 1));
				GL11.glEnd();
				GL11.glPointSize(width);
				GL11.glBegin(GL11.GL_POINTS);
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y + 0.25f));
				GL11.glEnd();
				GL11.glBegin(GL11.GL_LINES);
				break;
			case 'j':
				GL11.glVertex2f(scale * (startX - 0.25f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y - 1.5f));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y - 1.5f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1.5f));
				GL11.glEnd();
				GL11.glPointSize(width);
				GL11.glBegin(GL11.GL_POINTS);
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y + 0.25f));
				GL11.glEnd();
				GL11.glBegin(GL11.GL_LINES);
				break;
			case 'k':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y + 0.25f));
				break;
			case 't':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.15f));
				GL11.glVertex2f(scale * (startX), scale * (y + 0.15f));
			case 'l':
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y - 1));
				break;
			case 'm':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.25f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.25f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * y);
				GL11.glVertex2f(scale * (startX), scale * y);
				GL11.glVertex2f(scale * (startX), scale * y);
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				break;
			case 'p':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1.5f));
			case 'o':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
			case 'n':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX), scale * y);
				GL11.glVertex2f(scale * (startX), scale * y);
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				break;
			case 'q':
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1.5f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX), scale * y);
				GL11.glVertex2f(scale * (startX), scale * y);
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				break;
			case 'r':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 0.15f));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y - 0.1f));
				break;
			case 's':
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 0.5f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 0.5f));
				GL11.glVertex2f(scale * (startX), scale * (y - 0.5f));
				GL11.glVertex2f(scale * (startX), scale * (y - 0.5f));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				break;
			case 'u':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * y);
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * y);
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				break;
			case 'w':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.125f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.125f), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX + 0.125f), scale * (y));
				break;
			case 'v':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y));
				break;
			case 'x':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				break;
			case 'y':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.33f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1.5f));
				break;
			case 'z':
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				break;
			case '1':
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.25f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				break;
			case '2':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.65f));
				GL11.glVertex2f(scale * (startX - 0.3f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.3f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.15f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.15f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.35f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				break;
			case '3':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				break;
			case '4':
				GL11.glVertex2f(scale * startX, scale * (y + 0.8f));
				GL11.glVertex2f(scale * startX, scale * (y - 1));
				GL11.glVertex2f(scale * startX, scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y));
				break;
			case '8':
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y + 0.8f));
			case '6':
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
			case '5':
				GL11.glVertex2f(scale * (startX), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				break;
			case '7':
				GL11.glVertex2f(scale * (startX), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX), scale * (y + 0.8f));
				break;
			case '9':
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				break;
			case '0':
				GL11.glVertex2f(scale * (startX), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y - 1));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX - 0.5f), scale * (y + 0.8f));
				GL11.glVertex2f(scale * (startX), scale * (y + 0.8f));
				break;
			}
			if (!space) {
				startX += scale * 5;
				charNum++;
			} else {
				startX += scale * 3;
				spaceNum++;
			}
			space = false;
		}
		GL11.glEnd();
		GL11.glDisable(GL11.GL_LINE_WIDTH);
	}

}
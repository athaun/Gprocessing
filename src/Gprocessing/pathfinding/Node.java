package Gprocessing.pathfinding;

import Gprocessing.Engine;
import Gprocessing.Graphics;
import Gprocessing.graphics.Color;
import Gprocessing.input.Mouse;
import Gprocessing.physics.Vector2;
import static Gprocessing.Graphics.*;
import static Gprocessing.Engine.*;
import java.util.ArrayList;

public class Node {

	public ArrayList<Node> neighbors = new ArrayList<Node>();
	public Vector2 position;
	public float distanceFromDestination;
	public String nodeType = "void"; // types are "start", "void", "path", "end"
	boolean foundNeigbors = false;
	int totalNeigbors = 0;

	Node(Vector2 pos) {
		position = pos;
	}

	public void update() {

		if (!foundNeigbors) {
			for (int i = 0; i < Pathfinder.nodes.length; i++) {
				if (inCircle(position, Pathfinder.nodes[i].position, 200)) {
					// found a neighbor
					if (dist(position, Pathfinder.nodes[i].position) != 0) {
						neighbors.add(Pathfinder.nodes[i]);
						totalNeigbors++;
					}
				}
			}
			distanceFromDestination = dist(position, Pathfinder.nodes[Pathfinder.endNodeIndex].position);
			foundNeigbors = true;
		}
		
		
		fill(Color.BLACK);
		if (nodeType.equals("start")) fill(Color.RED);
		if (nodeType.equals("path")) { 
			fill(Color.BLUE);
		}
		if (nodeType.equals("end") || nodeType.equals("pathEnd")) fill(Color.GREEN);
		
		circle(position.x, position.y, 20, 50);
		fill(Color.BLACK);
		
//		for (int i = 0; i < neighbors.size(); i++) {
//			line(position, neighbors.get(i).position);
//		}
		for (int i = 1; i < Pathfinder.path.size(); i++) {
			line(Pathfinder.path.get(i - 1).position, Pathfinder.path.get(i).position);
		}
	}
}

package Gprocessing.pathfinding;

import Gprocessing.physics.Vector2;
import static Gprocessing.util.Engine.*;
import java.util.ArrayList;

public class Pathfinder {
	public static Node[] nodes;
	public static ArrayList<Node> path = new ArrayList<Node>();
	public static boolean pathFound = false;
	public static int endNodeIndex;

	public Pathfinder(Vector2[] nodePositions) {
		nodes = new Node[nodePositions.length];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node(nodePositions[i]); // create new nodes at each position in the passed array
		}
		nodes[0].nodeType = "start";
		path.add(nodes[0]);
		nodes[nodes.length - 1].nodeType = "end";
		endNodeIndex = nodes.length - 1;
	}

	public void findPath(Node startNode, Node endNode) {
		if (!pathFound) {
			for (int i = 0; i < nodes.length; i++) {

				Node n = nodes[i]; // The current node
				Node theChosenOne = nodes[0]; // the node chosen to be added to the path

				float smallestDistToEnd = n.distanceFromEnd; // which node has the shortest distance to the end as the
																// crow flies

				for (int j = 0; j < n.neighbors.size(); j++) {
					// loop through each neighbor, and find the neighbor closest to the end.
					Node neighbor = n.neighbors.get(j);
					if (neighbor.distanceFromEnd < smallestDistToEnd) {
						smallestDistToEnd = neighbor.distanceFromEnd;
					}
					if (neighbor.distanceFromEnd == smallestDistToEnd) {
						theChosenOne = neighbor;
					}
					if (neighbor.nodeType.equals("end")) {
						// if we found the end node, exit the loop
						theChosenOne = neighbor;
						break;
					}
				}

				if (theChosenOne.nodeType.equals("void") || theChosenOne.nodeType.equals("end")) {
					// add the chosen one the the path array
					path.add(theChosenOne);
					if (theChosenOne.nodeType.equals("end")) {
						// If the chosen one is the end node, exit the loop
						theChosenOne.nodeType = "pathEnd";
						println("PATH FOUND");
						pathFound = true;
						break;
					}
					// Else, change the nodeType to path so that it displays as blue
					theChosenOne.nodeType = "path";
				}
			}
		}
	}

//	public void findPath(Node startNode, Node endNode, int i) {
//		if (!pathFound) {
//			Node n = nodes[i];
//			int totalNeighbors = n.neighbors.size();
//
//			float[] distanceToNeighbor = new float[totalNeighbors];
//			float[] neighborsDistanceToEnd = new float[totalNeighbors];
//			for (int j = 0; j < totalNeighbors; j++) {
//				distanceToNeighbor[j] = dist(n.position, n.neighbors.get(j).position);
//				neighborsDistanceToEnd[j] = dist(n.neighbors.get(j).position, nodes[endNodeIndex].position);
//
//				float smallestDistToEnd = 0;
//				if (neighborsDistanceToEnd.length != 0) {
//
//					smallestDistToEnd = neighborsDistanceToEnd[0];
//					float index = 0;
//					for (int k = 0; k < neighborsDistanceToEnd.length; k ++) {
//					    if (neighborsDistanceToEnd[k] < smallestDistToEnd) {
//					    	smallestDistToEnd = neighborsDistanceToEnd[k];
//					        index = k;
//					    }
//					}
//				}				
//				
////				println("" + neighborsDistanceToEnd[j]);
//
//				for (int k = 0; k < neighborsDistanceToEnd.length; k++) {
//					if (neighborsDistanceToEnd[k] <= smallestDistToEnd) {
//
//						if (n.neighbors.get(j).nodeType.equals("void") || n.neighbors.get(j).nodeType.equals("end")) {
//							if (n.neighbors.get(j).nodeType.equals("end")) {
//								pathFound = true;
//								n.neighbors.get(j).nodeType = "pathEnd";
//								println("End node found");
//							}
//							path.add(n.neighbors.get(j));
//							if (n.neighbors.get(j).nodeType != "pathEnd")
//								n.neighbors.get(j).nodeType = "path";
//							
//						}
//
//						if (i < endNodeIndex - 1) {
//							findPath(startNode, endNode, i + 1);
//						} else {
//							pathFound = true;
//						}
//					}
//				}
//			}
//		}
//	}

	public void updateNodes() {
		for (int i = 0; i < nodes.length; i++) {
			nodes[i].update(); // draw the nodes, and calculate neighbors
		}
	}
}

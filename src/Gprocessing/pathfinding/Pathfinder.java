package Gprocessing.pathfinding;

import Gprocessing.physics.Vector2;
import static Gprocessing.Engine.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Pathfinder {
	public static Node[] nodes;
	public static ArrayList<Node> path = new ArrayList<Node>();
	public boolean pathFound = false;
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

//	public void findPath(Node startNode, Node endNode) {
//		if (!pathFound) {
//			for (int i = 0; i < nodes.length; i++) {
//				Node n = nodes[i];
//				int totalNeigbors = n.neighbors.size();
//
//				float[] distanceToNeighbor = new float[totalNeigbors];
//				float[] neigborsDistanceToEnd = new float[totalNeigbors];
//				for (int j = 0; j < totalNeigbors; j++) {
//					distanceToNeighbor[j] = dist(n.position, n.neighbors.get(j).position);
//					neigborsDistanceToEnd[j] = dist(n.neighbors.get(j).position, nodes[endNodeIndex].position);
//
//					float smallestDistToEnd = neigborsDistanceToEnd[0];
//					float smallestDistToNeigbor = distanceToNeighbor[0];
//					
//					for (float k : neigborsDistanceToEnd) {
//						if (smallestDistToEnd > k && k != 0)
//							smallestDistToEnd = k;
//					}
//
////					for (float k : distanceToNeighbor) {
////						if (smallestDistToNeigbor > k && k != 0)
////							smallestDistToNeigbor = k;
////					}
//
//					for (int k = 0; k < neigborsDistanceToEnd.length; k++) {
//						if (neigborsDistanceToEnd[k] == smallestDistToEnd && distanceToNeighbor[k] < 200) {
//							
//							
//							/*
//							 * have tried
//							 * nodes[k].
//							 * n.neighbors.get(k).
//							 * n.
//							 *  
//							 */
//							if (n.neighbors.get(k).nodeType.equals("void")) {
//								path.add(n.neighbors.get(k));
//								n.neighbors.get(k).nodeType = "path";
//							}
//							pathFound = true;
//						}
//					}
////			        println("Shortest option for node " + i + " is neigboring node " + smallest);
//				}
//			}
//			
//		}
//		// return path; // return the nodes that should be traveled
//	}

	public void findPath(Node startNode, Node endNode, int i) {
		if (!pathFound) {
//			println("Find New Path @ " + i);
			Node n = nodes[i];
			int totalNeigbors = n.neighbors.size();

			float[] distanceToNeighbor = new float[totalNeigbors];
			float[] neigborsDistanceToEnd = new float[totalNeigbors];
			for (int j = 0; j < totalNeigbors; j++) {
				distanceToNeighbor[j] = dist(n.position, n.neighbors.get(j).position);
				neigborsDistanceToEnd[j] = dist(n.neighbors.get(j).position, nodes[endNodeIndex].position);

				float smallestDistToEnd = neigborsDistanceToEnd[0];

				for (float k : neigborsDistanceToEnd) {
					if (smallestDistToEnd > k && k != 0)
						smallestDistToEnd = k;
				}

				for (int k = 0; k < neigborsDistanceToEnd.length; k++) {
					if (neigborsDistanceToEnd[k] <= smallestDistToEnd /*&& distanceToNeighbor[k] < 200*/) {

						if (n.neighbors.get(j).nodeType.equals("void") || n.neighbors.get(j).nodeType.equals("end")) {
							if (n.neighbors.get(j).nodeType.equals("end")) {
								pathFound = true;
								n.neighbors.get(j).nodeType = "pathEnd";
								println("Path Found, end node = ");
								n.neighbors.get(j).position.print();
							}	
							path.add(n.neighbors.get(j));
							if (n.neighbors.get(j).nodeType != "pathEnd")
								n.neighbors.get(j).nodeType = "path";
							break;
						}

						if (i < endNodeIndex - 1) {
							findPath(startNode, endNode, i + 1);
						} else {
							pathFound = true;
						}
					}
				}
			}
		}
	}

	public void updateNodes() {
		for (int i = 0; i < nodes.length; i++) {
			nodes[i].update(); // draw the nodes, and calculate neighbors
		}
	}
}

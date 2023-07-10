package gad.dijkstra;

import java.util.Collection;

public class Graph {
	public static final class Node {
		private int id;

		public int getID() {
			return id;
		}

		public Collection<Node> getNeighbours() {
			return null;
		}

		public int getShortestDistanceToNeighbour(Node neighbour) {
			return 0;
		}

	}

	public Graph() {
	}

	public int addNode() {
		return 0;
	}

	public Node getNode(int id) {
		return null;
	}

	public Collection<Node> getAllNodes() {
		return null;
	}

	public void addEdge(int from, int to, int weight) {
	}
}

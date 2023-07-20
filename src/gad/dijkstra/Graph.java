package gad.dijkstra;

import java.util.*;

public class Graph {
	public static final class Node {
		private int id;
		private ArrayList<Node> neighbours;
		private ArrayList<Edge> edges;

		public Node(int id) {
			this.id = id;
			this.neighbours = new ArrayList<>();
			this.edges = new ArrayList<>();
		}

		public int getID() {
			return id;
		}

		public void addNeighbour(Node neighbour) {
			neighbours.add(neighbour);
			neighbours.sort(Comparator.comparing(Node::getID));
		}

		public void addEdge(Edge edge) {
			edges.add(edge);
			edges.sort(Comparator.comparing(Edge::getWeight));
		}

		public Collection<Node> getNeighbours() {
			return neighbours;
		}

		public Collection<Edge> getEdges() {
			return edges;
		}

		public int getShortestDistanceToNeighbour(Node neighbour) {
			if (!neighbours.contains(neighbour)) {
				throw new IllegalArgumentException();
			} else {
				return edges.get(0).weight;
			}
		}
	}

	public static final class Edge {

		private int to;
		private int weight;
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		public int getTo() {
			return to;
		}

		public int getWeight() {
			return weight;
		}

		public void setTo(int to) {
			this.to = to;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}
	}

	private ArrayList<Node> nodes;
	private int id;

	public Graph() {
		nodes = new ArrayList<>();
		this.id = 0;
	}

	public int addNode() {
		Node node = new Node(id);
		id++;
		nodes.add(node);
		nodes.sort(Comparator.comparing(Node::getID));
		return node.getID();
	}

	public Node getNode(int id) {
		if (id >= nodes.size() || id < 0) {
			throw new IllegalArgumentException();
		} else {
			return nodes.get(id);
		}
	}

	public Collection<Node> getAllNodes() {
		return nodes;
	}

	public void addEdge(int from, int to, int weight) {
		if (weight < 0) {
			throw new IllegalArgumentException();
		} else {
			Edge edge = new Edge(to, weight);
			nodes.get(from).addNeighbour(nodes.get(to));
			nodes.get(from).addEdge(edge);
		}
	}
}

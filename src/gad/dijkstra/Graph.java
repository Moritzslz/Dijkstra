package gad.dijkstra;

import java.util.*;

public class Graph {
	public static final class Node {
		private final int id;
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
			return List.copyOf(neighbours);
		}

		public Collection<Edge> getEdges() {
			return edges;
		}

		public int getShortestDistanceToNeighbour(Node neighbour) {
			if (!neighbours.contains(neighbour)) {
				throw new IllegalArgumentException();
			} else {
				for (Edge edge : edges) {
					if (edge.getTo() == neighbour.getID()) {
						return edge.weight;
					}
				}
			}
			return 0;
		}
	}

	public static final class Edge {
		private final int to;
		private final int weight;
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
	}



	private HashMap<Integer, Node> nodes;
	private int id;

	public Graph() {
		nodes = new HashMap<>();
		this.id = 0;
	}

	public int addNode() {
		Node node = new Node(id);
		nodes.put(node.getID(), node);
		id++;
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
		return List.copyOf(nodes.values());
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

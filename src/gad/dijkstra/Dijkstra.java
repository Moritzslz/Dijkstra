package gad.dijkstra;

import java.util.*;

import gad.dijkstra.Graph.Node;

public class Dijkstra {

	ArrayList<Node> visited;
	PriorityQueue<Node> queue;
	ArrayList shortestPath;
	HashMap<Node, Integer> distances;
	int shortestPathLength;

	public Dijkstra() {
		this.visited = new ArrayList<>();
		this.queue = new PriorityQueue<>();
		this.shortestPath = new ArrayList();
		this.distances = new HashMap<>();
		this.shortestPathLength = 0;
	}

	public void findRoute(Graph g, Node start, Node goal, Result result) {

		Node current = start;
		distances.put(current, 0);

		while (!queue.isEmpty()) {
			List<Node> neighbours = List.copyOf(current.getNeighbours());
			List<Integer> ids = new ArrayList<>();
			for (Node neighbour : neighbours) {
				ids.add(neighbour.getID());
			}

			result.addNode(current.getID(), distances.get(current));
			result.addNeighbours(ids);

			shortestPath.add(current);

			if (current.equals(goal)) {
				break;
			}

			visited.add(current);

			while (!current.edges.isEmpty()) {
				Graph.Edge edge = current.edges.poll();
				Node neighbour = g.getNode(edge.getTo());
				distances.put(neighbour, distances.get(current) + edge.getWeight());
				queue.add(g.getNode(edge.getTo()));
			}

			current = queue.poll();

			if (visited.contains(current) && !queue.isEmpty()) {
				queue.poll();
			} else {
				throw new RuntimeException();
			}
		}

		if (!current.equals(goal)) {
			throw new RuntimeException();
		}

		shortestPathLength = distances.get(goal);

		getShortestPath();
		getShortestPathLength();
	}

	public List<Node> getShortestPath() {
		return shortestPath;
	}

	public int getShortestPathLength() {
		return shortestPathLength;
	}
	public static void main(String[] args) {
		// Create a graph and add nodes and edges
		Graph graph = new Graph();
		int nodeA = graph.addNode();
		int nodeB = graph.addNode();
		int nodeC = graph.addNode();
		int nodeD = graph.addNode();

		graph.addEdge(nodeA, nodeB, 10);
		graph.addEdge(nodeA, nodeC, 5);
		graph.addEdge(nodeB, nodeC, 2);
		graph.addEdge(nodeB, nodeD, 7);
		graph.addEdge(nodeC, nodeD, 1);

		// Create a Dijkstra instance and find the shortest path
		Dijkstra dijkstra = new Dijkstra();
		Result result = new StudentResult();
		dijkstra.findRoute(graph, graph.getNode(nodeA), graph.getNode(nodeD), result);

		// Get the shortest path and its length
		List<Graph.Node> shortestPath = dijkstra.getShortestPath();
		int shortestPathLength = dijkstra.getShortestPathLength();

		// Print the result
		System.out.println("Shortest Path: " + Arrays.toString(shortestPath.toArray()));
		System.out.println("Shortest Path Length: " + shortestPathLength);
	}
}
package gad.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gad.dijkstra.Graph.Node;

public class Dijkstra {

	ArrayList<Node> visited;
	ArrayList<Node> shortestPath;
	int distance;

	public Dijkstra() {
		this.visited = new ArrayList<>();
		this.shortestPath = new ArrayList<>();
		distance = 0;
	}

	public void findRoute(Graph g, Node start, Node goal, Result result) {
		Node current  = start;
		Node previous = null;
		while (!current.edges.isEmpty()) {
			result.addNode(current.getID(), distance);
			ArrayList<Integer> neighbourIds = new ArrayList<>();
			for (Node neighbour : current.getNeighbours()) {
				neighbourIds.add(neighbour.getID());
			}
			result.addNeighbours(neighbourIds);

			if (current == goal) {
				break;
			}

			Graph.Edge edge = current.edges.poll();
			Node neighbour = g.getNode(edge.getTo());
			visited.add(current);
			shortestPath.add(current);
			distance += edge.getWeight();

			previous = current;
			current = neighbour;

			if (current.edges.isEmpty() && !previous.edges.isEmpty()) {
				shortestPath.remove(current);
				distance -= edge.getWeight();
				current = previous;
			}
		}

		if (!current.equals(goal)) {
			shortestPath = null;
			distance = 0;
			throw new RuntimeException();
		}
		getShortestPath();
		getShortestPathLength();
	}

	public List<Node> getShortestPath() {
		return shortestPath;
	}

	public int getShortestPathLength() {
		return distance;
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
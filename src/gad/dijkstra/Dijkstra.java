package gad.dijkstra;

import java.util.ArrayList;
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
			Node neighbour = current.getNeighbour(edge.getTo());
			visited.add(current);
			shortestPath.add(current);
			distance += edge.getWeight();

			previous = current;
			current = neighbour;

			if (current.edges.isEmpty() && !previous.edges.isEmpty()) {
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
}
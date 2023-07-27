package gad.dijkstra;

import java.util.*;

import gad.dijkstra.Graph.Node;

public class Dijkstra {

	private ArrayList<Node> visited;
	private PriorityQueue<Node> queue;
	private ArrayList shortestPath;
	private HashMap<Node, Integer> distances;
	private Map<Node, Node> previous;
	private int shortestPathLength;

	public Dijkstra() {
		this.visited = new ArrayList<>();
		this.queue = new PriorityQueue();
		this.shortestPath = new ArrayList();
		this.distances = new HashMap<>();
		this.previous = new HashMap<>();
		this.shortestPathLength = 0;
	}

	public void findRoute(Graph g, Node start, Node goal, Result result) {
		distances.put(start, 0);
		queue.add(start);

		while (!queue.isEmpty()) {
			Node current = queue.poll();

			List<Node> neighbours = List.copyOf(current.getNeighbours());
			ArrayList<Integer> ids = new ArrayList<>();
			for (Node neighbour : neighbours) {
				ids.add(neighbour.getID());
			}

			result.addNode(current.getID(), distances.get(current));
			result.addNeighbours(ids);

			if (current.equals(goal)) {
				break;
			}

			visited.add(current);

			for (Graph.Edge edge : current.getEdges()) {
				Node neighbor = g.getNode(edge.getTo());
				int newDistance = distances.get(current) + edge.getWeight();

				if (!distances.containsKey(neighbor) || newDistance < distances.get(neighbor)) {
					distances.put(neighbor, newDistance);
					previous.put(neighbor, current); // Store the predecessor for the neighbor
					queue.remove(neighbor);
					queue.add(neighbor);
				}
			}
		}

		if (!visited.contains(goal)) {
			throw new RuntimeException("Goal node is not reachable from the start node.");
		}

		shortestPathLength = distances.get(goal);

		Node currentNode = goal;
		while (currentNode != null) {
			shortestPath.add(0, currentNode);
			currentNode = previous.get(currentNode); // Retrieve the predecessor for the current node
		}
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
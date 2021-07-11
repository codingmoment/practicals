package com.datastructure.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.datastructure.graph.algorithm.sort.TopologicalSortAlgorithm;
import com.datastructure.graph.implementation.Adjacency;
import com.datastructure.graph.implementation.UDAdjacencyList;
import com.datastructure.graph.implementation.UDAdjacencyMatrix;
import com.datastructure.graph.implementation.GraphNode;

public class TopologicalSortTest {

  public static void main(String[] args) {
    // Create Directed Adjacency Matrix
    UDAdjacencyMatrix matrix = new UDAdjacencyMatrix();
    plotAdjacency(matrix);

    // Topological sort using adjacency matrix
    TopologicalSortAlgorithm topologicalSort = new TopologicalSortAlgorithm();
    System.out.println("Topological Sort Using Adjacency Matrix:" + topologicalSort.topolocialSort(matrix));

    // Create Directed Adjacency Matrix
    UDAdjacencyList adjacencyList = new UDAdjacencyList();
    plotAdjacency(adjacencyList);

    // Topological sort using adjacency matrix
    System.out.println("Topological Sort Using Adjacency List:" + topologicalSort.topolocialSort(adjacencyList));
  }

  private static void plotAdjacency(Adjacency adjacency) {
    Map<String, GraphNode> graphMap = new HashMap<>();

    graphMap.put("A", new GraphNode("A"));
    graphMap.put("B", new GraphNode("B"));
    graphMap.put("C", new GraphNode("C"));
    graphMap.put("D", new GraphNode("D"));
    graphMap.put("E", new GraphNode("E"));
    graphMap.put("F", new GraphNode("F"));
    graphMap.put("G", new GraphNode("G"));
    graphMap.put("H", new GraphNode("H"));

    adjacency.setGraphNodes(graphMap.values().stream().collect(Collectors.toList()));

    // Add Dependencies
    adjacency.addEdge(graphMap.get("A"), graphMap.get("C"));
    adjacency.addEdge(graphMap.get("B"), graphMap.get("C"));
    adjacency.addEdge(graphMap.get("B"), graphMap.get("D"));
    adjacency.addEdge(graphMap.get("C"), graphMap.get("E"));
    adjacency.addEdge(graphMap.get("E"), graphMap.get("F"));
    adjacency.addEdge(graphMap.get("D"), graphMap.get("F"));
    adjacency.addEdge(graphMap.get("E"), graphMap.get("H"));
    adjacency.addEdge(graphMap.get("F"), graphMap.get("G"));

  }
}

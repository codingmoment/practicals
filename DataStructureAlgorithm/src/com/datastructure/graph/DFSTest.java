package com.datastructure.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.datastructure.graph.algorithm.traversal.DFSAlgorithm;
import com.datastructure.graph.implementation.Adjacency;
import com.datastructure.graph.implementation.GraphNode;
import com.datastructure.graph.implementation.UUAdjacencyList;
import com.datastructure.graph.implementation.UUAdjacencyMatrix;

public class DFSTest {

  public static void main(String[] args) {
    // Create Adjacency Matrix
    UUAdjacencyMatrix matrix = new UUAdjacencyMatrix();
    plotAdjacency(matrix);

    // DFS traversal using adjacency matrix
    DFSAlgorithm dfs = new DFSAlgorithm();
    System.out.println("DFS Traversal Using Adjacency Matrix:");
    dfs.traverse(matrix);

    // Create Adjacency List
    UUAdjacencyList adjacencyList = new UUAdjacencyList();
    plotAdjacency(adjacencyList);

    // DFS traversal using adjacency list
    System.out.println("\nDFS Traversal Using Adjacency List:");
    dfs.traverse(adjacencyList);
  }

  private static void plotAdjacency(Adjacency adjacency) {
    Map<String, GraphNode> graphMap = new HashMap<>();

    // Create 10 vertices: V1-V10
    for (int i = 1; i <= 10; i++) {
      GraphNode graphNode = new GraphNode("V" + i);
      graphMap.put(graphNode.getName(), graphNode);
    }

    adjacency.setGraphNodes(graphMap.values().stream().collect(Collectors.toList()));

    // Add Edges
    adjacency.addEdge(graphMap.get("V1"), graphMap.get("V2"));
    adjacency.addEdge(graphMap.get("V1"), graphMap.get("V4"));
    adjacency.addEdge(graphMap.get("V2"), graphMap.get("V3"));
    adjacency.addEdge(graphMap.get("V2"), graphMap.get("V5"));
    adjacency.addEdge(graphMap.get("V3"), graphMap.get("V6"));
    adjacency.addEdge(graphMap.get("V3"), graphMap.get("V10"));
    adjacency.addEdge(graphMap.get("V4"), graphMap.get("V7"));
    adjacency.addEdge(graphMap.get("V5"), graphMap.get("V8"));
    adjacency.addEdge(graphMap.get("V6"), graphMap.get("V9"));
    adjacency.addEdge(graphMap.get("V7"), graphMap.get("V8"));
    adjacency.addEdge(graphMap.get("V8"), graphMap.get("V9"));
    adjacency.addEdge(graphMap.get("V9"), graphMap.get("V10"));

  }
}

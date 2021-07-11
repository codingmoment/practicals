package com.datastructure.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.datastructure.graph.algorithm.minspanningtree.PrimsAlgorithm;
import com.datastructure.graph.implementation.GraphNode;
import com.datastructure.graph.implementation.PUAdjacencyList;

public class PrimsTest {
  public static void main(String[] args) {
    PrimsAlgorithm algorithm = new PrimsAlgorithm();

    // Create positive weighted undirected Adjacency List
    PUAdjacencyList adjacency = new PUAdjacencyList();

    Map<String, GraphNode> graphMap = new HashMap<>();

    // Create 5 vertices: A, B, C, D, E
    graphMap.put("A", new GraphNode("A"));
    graphMap.put("B", new GraphNode("B"));
    graphMap.put("C", new GraphNode("C"));
    graphMap.put("D", new GraphNode("D"));
    graphMap.put("E", new GraphNode("E"));

    adjacency.setGraphNodes(graphMap.values().stream().collect(Collectors.toList()));

    // Add Weighted Edges
    adjacency.addWeightedEdge(graphMap.get("A"), graphMap.get("B"), 10);
    adjacency.addWeightedEdge(graphMap.get("A"), graphMap.get("C"), 20);
    adjacency.addWeightedEdge(graphMap.get("B"), graphMap.get("C"), 30);
    adjacency.addWeightedEdge(graphMap.get("B"), graphMap.get("D"), 5);
    adjacency.addWeightedEdge(graphMap.get("C"), graphMap.get("D"), 15);
    adjacency.addWeightedEdge(graphMap.get("C"), graphMap.get("E"), 6);
    adjacency.addWeightedEdge(graphMap.get("D"), graphMap.get("E"), 8);

    algorithm.minimumSpanningTree(adjacency);

  }
}

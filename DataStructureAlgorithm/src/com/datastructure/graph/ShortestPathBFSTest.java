package com.datastructure.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.datastructure.graph.algorithm.shortestpath.ShortestPathUsingBSFAlgo;
import com.datastructure.graph.implementation.Adjacency;
import com.datastructure.graph.implementation.GraphNode;
import com.datastructure.graph.implementation.UUAdjacencyList;

public class ShortestPathBFSTest {

  public static void main(String[] args) {
    // Create unweighed undirected Adjacency List
    UUAdjacencyList adjacencyList = new UUAdjacencyList();
    plotAdjacency(adjacencyList);

    ShortestPathUsingBSFAlgo algorithm = new ShortestPathUsingBSFAlgo();
    algorithm.findShortestPath(adjacencyList, "4", "8");

  }

  private static void plotAdjacency(Adjacency adjacency) {
    Map<String, GraphNode> graphMap = new HashMap<>();

    // Create 10 vertices: V0-V9
    for (int i = 0; i <= 9; i++) {
      GraphNode graphNode = new GraphNode("" + i);
      graphMap.put(graphNode.getName(), graphNode);
    }

    adjacency.setGraphNodes(graphMap.values().stream().collect(Collectors.toList()));

    // Add Edges
    adjacency.addEdge(graphMap.get("0"), graphMap.get("8"));
    adjacency.addEdge(graphMap.get("8"), graphMap.get("2"));
    adjacency.addEdge(graphMap.get("8"), graphMap.get("9"));
    adjacency.addEdge(graphMap.get("2"), graphMap.get("4"));
    adjacency.addEdge(graphMap.get("2"), graphMap.get("1"));
    adjacency.addEdge(graphMap.get("9"), graphMap.get("1"));
    adjacency.addEdge(graphMap.get("4"), graphMap.get("3"));
    adjacency.addEdge(graphMap.get("1"), graphMap.get("3"));
    adjacency.addEdge(graphMap.get("1"), graphMap.get("7"));
    adjacency.addEdge(graphMap.get("3"), graphMap.get("5"));
    adjacency.addEdge(graphMap.get("5"), graphMap.get("6"));
    adjacency.addEdge(graphMap.get("7"), graphMap.get("6"));

  }
}

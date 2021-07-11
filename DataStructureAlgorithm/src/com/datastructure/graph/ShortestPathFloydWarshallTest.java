package com.datastructure.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.datastructure.graph.algorithm.shortestpath.ShortestPathUsingFloydWarshall;
import com.datastructure.graph.implementation.GraphNode;
import com.datastructure.graph.implementation.PDAdjacencyList;

public class ShortestPathFloydWarshallTest {

  public static void main(String[] args) {
    test2();
  }

  private static void test1() {
    // Create positive weighted directed Adjacency List
    PDAdjacencyList adjacency = new PDAdjacencyList();

    Map<String, GraphNode> graphMap = new HashMap<>();

    // Create 5 vertices: A, B, C, D, E
    graphMap.put("A", new GraphNode("A"));
    graphMap.put("B", new GraphNode("B"));
    graphMap.put("C", new GraphNode("C"));
    graphMap.put("D", new GraphNode("D"));
    graphMap.put("E", new GraphNode("E"));

    adjacency.setGraphNodes(graphMap.values().stream().collect(Collectors.toList()));

    // Add Weighted Edges
    adjacency.addWeightedEdge(graphMap.get("E"), graphMap.get("B"), 4);
    adjacency.addWeightedEdge(graphMap.get("E"), graphMap.get("D"), 2);
    adjacency.addWeightedEdge(graphMap.get("B"), graphMap.get("A"), 3);
    adjacency.addWeightedEdge(graphMap.get("D"), graphMap.get("B"), 1);
    adjacency.addWeightedEdge(graphMap.get("D"), graphMap.get("C"), 1);
    adjacency.addWeightedEdge(graphMap.get("A"), graphMap.get("D"), 6);
    adjacency.addWeightedEdge(graphMap.get("A"), graphMap.get("C"), 6);
    adjacency.addWeightedEdge(graphMap.get("C"), graphMap.get("D"), 2);

    ShortestPathUsingFloydWarshall algorithm = new ShortestPathUsingFloydWarshall();
    algorithm.findShortestPath(adjacency);
  }

  private static void test2() {
    // Create positive weighted directed Adjacency List
    PDAdjacencyList adjacency = new PDAdjacencyList();

    Map<String, GraphNode> graphMap = new HashMap<>();

    // Create vertices: A, B, C, D, E, F, G
    graphMap.put("A", new GraphNode("A"));
    graphMap.put("B", new GraphNode("B"));
    graphMap.put("C", new GraphNode("C"));
    graphMap.put("D", new GraphNode("D"));
    graphMap.put("E", new GraphNode("E"));
    graphMap.put("F", new GraphNode("F"));
    graphMap.put("G", new GraphNode("G"));

    adjacency.setGraphNodes(graphMap.values().stream().collect(Collectors.toList()));

    // Add Weighted Edges
    adjacency.addWeightedEdge(graphMap.get("A"), graphMap.get("B"), 10);
    adjacency.addWeightedEdge(graphMap.get("A"), graphMap.get("C"), 6);
    adjacency.addWeightedEdge(graphMap.get("A"), graphMap.get("F"), 20);
    adjacency.addWeightedEdge(graphMap.get("A"), graphMap.get("G"), 5);

    adjacency.addWeightedEdge(graphMap.get("B"), graphMap.get("D"), 3);

    adjacency.addWeightedEdge(graphMap.get("C"), graphMap.get("D"), 1);
    adjacency.addWeightedEdge(graphMap.get("C"), graphMap.get("F"), 2);

    adjacency.addWeightedEdge(graphMap.get("D"), graphMap.get("B"), 1);
    adjacency.addWeightedEdge(graphMap.get("D"), graphMap.get("E"), 3);

    adjacency.addWeightedEdge(graphMap.get("E"), graphMap.get("D"), 7);

    adjacency.addWeightedEdge(graphMap.get("F"), graphMap.get("E"), 10);

    adjacency.addWeightedEdge(graphMap.get("G"), graphMap.get("F"), 9);

    ShortestPathUsingFloydWarshall algorithm = new ShortestPathUsingFloydWarshall();
    algorithm.findShortestPath(adjacency);
  }

}

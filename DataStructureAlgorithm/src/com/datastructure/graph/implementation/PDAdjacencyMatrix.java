package com.datastructure.graph.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Positive weighted-directed Graph implemented using Adjacency Matrix
 */
public class PDAdjacencyMatrix extends UDAdjacencyMatrix {

  private Map<String, Map<String, Integer>> nodeToNodeWeightMap = new HashMap<>();
  private List<Edge> edges = new ArrayList<>();

  @Override
  public void addWeightedEdge(GraphNode fromNode, GraphNode toNode, int weight) {
    addEdge(fromNode, toNode);

    // Add weight information in the map
    if (!nodeToNodeWeightMap.containsKey(fromNode.getName())) {
      nodeToNodeWeightMap.put(fromNode.getName(), new HashMap<>());
    }
    nodeToNodeWeightMap.get(fromNode.getName()).put(toNode.getName(), weight);

    edges.add(new Edge(fromNode, toNode, weight));
  }

  @Override
  public int getWeightOfEdge(GraphNode fromNode, GraphNode toNode) {
    if (nodeToNodeWeightMap.containsKey(fromNode.getName())) {
      Integer weight = nodeToNodeWeightMap.get(fromNode.getName()).get(toNode.getName());
      if (weight != null) {
        return weight;
      }
    }
    return Integer.MAX_VALUE;
  }

  @Override
  public List<Edge> getEdges() {
    return edges;
  }

}

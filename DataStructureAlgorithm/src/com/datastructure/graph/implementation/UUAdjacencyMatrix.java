package com.datastructure.graph.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unweighted-Undirected Graph implemented using Adjacency Matrix
 */
public class UUAdjacencyMatrix implements Adjacency {
  protected int[][] adjacencyMatrix;
  protected List<GraphNode> graphNodes;
  protected Map<String, Integer> nodeToIndexMap = new HashMap<String, Integer>();

  @Override
  public void setGraphNodes(List<GraphNode> graphNodes) {
    adjacencyMatrix = new int[graphNodes.size()][graphNodes.size()];

    this.graphNodes = graphNodes;
    for (int i = 0; i < graphNodes.size(); i++) {
      nodeToIndexMap.put(graphNodes.get(i).getName(), i);
    }
  }

  @Override
  public void addEdge(GraphNode fromNode, GraphNode toNode) {
    int fromNodeIndex = nodeToIndexMap.get(fromNode.getName());
    int toNodeIndex = nodeToIndexMap.get(toNode.getName());

    adjacencyMatrix[fromNodeIndex][toNodeIndex] = 1;
    adjacencyMatrix[toNodeIndex][fromNodeIndex] = 1;
  }

  @Override
  public List<GraphNode> getAdjacentNodes(GraphNode graphNode) {
    List<GraphNode> adjacentNodes = new ArrayList<>();

    // Get the index for graphNode
    int graphNodeIndex = nodeToIndexMap.get(graphNode.getName());

    // Adjacent vertices
    for (int i = 0; i < adjacencyMatrix[graphNodeIndex].length; i++) {
      if (adjacencyMatrix[graphNodeIndex][i] == 1) {
        adjacentNodes.add(graphNodes.get(i));
      }
    }

    return adjacentNodes;
  }

  @Override
  public List<GraphNode> getGraphNodes() {
    return graphNodes;
  }
}

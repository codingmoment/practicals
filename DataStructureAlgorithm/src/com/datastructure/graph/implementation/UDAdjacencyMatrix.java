package com.datastructure.graph.implementation;

/**
 * Unweighted-directed Graph implemented using Adjacency Matrix
 */
public class UDAdjacencyMatrix extends UUAdjacencyMatrix {
  @Override
  public void addEdge(GraphNode fromNode, GraphNode toNode) {
    int fromNodeIndex = nodeToIndexMap.get(fromNode.getName());
    int toNodeIndex = nodeToIndexMap.get(toNode.getName());

    adjacencyMatrix[fromNodeIndex][toNodeIndex] = 1;
  }
}

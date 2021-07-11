package com.datastructure.graph.implementation;

/**
 * Unweighted-Directed Graph implemented using Adjacency List
 */
public class UDAdjacencyList extends UUAdjacencyList {

  @Override
  public void addEdge(GraphNode fromNode, GraphNode toNode) {
    int fromIndex = nodeToIndexMap.get(fromNode.getName());
    adjacencyList[fromIndex].add(toNode);
  }
}

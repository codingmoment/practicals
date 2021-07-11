package com.datastructure.graph.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Unweighted-Undirected Graph implemented using Adjacency List
 */
public class UUAdjacencyList implements Adjacency {

  protected LinkedList<GraphNode> adjacencyList[];
  protected Map<String, Integer> nodeToIndexMap = new HashMap<String, Integer>();

  @Override
  public void setGraphNodes(List<GraphNode> graphNodes) {
    adjacencyList = new LinkedList[graphNodes.size()];

    int index = 0;
    for (GraphNode graphNode : graphNodes) {
      adjacencyList[index] = new LinkedList<GraphNode>();
      adjacencyList[index].add(graphNode);
      nodeToIndexMap.put(graphNode.getName(), index);
      index++;
    }
  }

  @Override
  public void addEdge(GraphNode fromNode, GraphNode toNode) {
    int fromIndex = nodeToIndexMap.get(fromNode.getName());
    int toIndex = nodeToIndexMap.get(toNode.getName());

    adjacencyList[fromIndex].add(toNode);
    adjacencyList[toIndex].add(fromNode);
  }

  @Override
  public List<GraphNode> getGraphNodes() {
    return Arrays.asList(adjacencyList).stream().map(list -> list.get(0)).collect(Collectors.toList());
  }

  @Override
  public List<GraphNode> getAdjacentNodes(GraphNode graphNode) {
    int index = nodeToIndexMap.get(graphNode.getName());

    List<GraphNode> adjacentNodes = new ArrayList<GraphNode>();
    for (GraphNode node : adjacencyList[index]) {
      if (!node.getName().equalsIgnoreCase(graphNode.getName())) {
        adjacentNodes.add(node);
      }
    }

    return adjacentNodes;
  }
}

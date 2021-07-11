package com.datastructure.graph.algorithm.sort;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import com.datastructure.graph.implementation.Adjacency;
import com.datastructure.graph.implementation.GraphNode;

/**
 * Topological Sort algorithm to sort the nodes in the order of dependency. 
 * The nodes on which a specific node is dependent on will come before that node in the sequence.
 */
public class TopologicalSortAlgorithm {

  private Stack<GraphNode> stack;

  public List<GraphNode> topolocialSort(Adjacency adjacency) {

    List<GraphNode> graphNodes = adjacency.getGraphNodes();
    stack = new Stack<>();

    for (GraphNode graphNode : graphNodes) {
      if (!graphNode.isVisited()) {
        topologicalVisit(graphNode, adjacency);
      }
    }

    return stack.stream().collect(Collectors.toList());
  }

  private void topologicalVisit(GraphNode graphNode, Adjacency adjacency) {
    List<GraphNode> adjacentNodes = adjacency.getAdjacentNodes(graphNode);

    for (GraphNode adjacentNode : adjacentNodes) {
      if (!adjacentNode.isVisited()) {
        topologicalVisit(adjacentNode, adjacency);
      }
    }

    graphNode.setVisited(true);
    stack.push(graphNode);
  }
}

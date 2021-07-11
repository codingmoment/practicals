package com.datastructure.graph.algorithm.traversal;

import java.util.Stack;

import com.datastructure.graph.implementation.Adjacency;
import com.datastructure.graph.implementation.GraphNode;

/**
 * Algorithm to traverse the graph using depth-first-search 
 */
public class DFSAlgorithm {

  public void traverse(Adjacency adjacency) {
    Stack<GraphNode> stack = new Stack<GraphNode>();

    if (adjacency.getGraphNodes().isEmpty()) {
      return;
    }

    stack.push(adjacency.getGraphNodes().get(0));

    while (!stack.isEmpty()) {
      GraphNode graphNode = stack.pop();
      if (!graphNode.isVisited()) {
        System.out.print(graphNode.getName() + " ");
        graphNode.setVisited(true);

        for (GraphNode adjacentNode : adjacency.getAdjacentNodes(graphNode)) {
          if (!adjacentNode.isVisited()) {
            stack.push(adjacentNode);
          }
        }
      }
    }
  }
}

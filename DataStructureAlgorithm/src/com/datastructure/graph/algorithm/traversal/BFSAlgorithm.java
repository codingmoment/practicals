package com.datastructure.graph.algorithm.traversal;

import java.util.LinkedList;
import java.util.Queue;

import com.datastructure.graph.implementation.Adjacency;
import com.datastructure.graph.implementation.GraphNode;

/**
 * Algorithm to traverse the graph using breadth-first-search
 */
public class BFSAlgorithm {

  public void traverse(Adjacency adjacency) {
    Queue<GraphNode> queue = new LinkedList<>();

    if (adjacency.getGraphNodes().isEmpty()) {
      return;
    }

    queue.add(adjacency.getGraphNodes().get(0));

    while (!queue.isEmpty()) {
      GraphNode graphNode = queue.remove();
      if (!graphNode.isVisited()) {
        System.out.print(graphNode.getName() + " ");
        graphNode.setVisited(true);

        for (GraphNode adjacentNode : adjacency.getAdjacentNodes(graphNode)) {
          if (!adjacentNode.isVisited()) {
            queue.add(adjacentNode);
          }
        }
      }
    }
  }
}

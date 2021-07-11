package com.datastructure.graph.algorithm.shortestpath;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.datastructure.graph.implementation.Adjacency;
import com.datastructure.graph.implementation.GraphNode;

/**
 * Implementation of single source shortest path algorithm using BFS.
 * 
 * The algorithm gives you the shortest path (based on the number of intermediate nodes) from source to target node.
 */
public class ShortestPathUsingBSFAlgo {

  public void findShortestPath(Adjacency adjacency, String sourceNode, String targetNode) {
    Queue<GraphNode> queue = new LinkedList<>();

    if (adjacency.getGraphNodes().isEmpty()) {
      return;
    }

    GraphNode sourceGraphNode = adjacency.getGraphNodes().stream().filter(node -> node.getName().equalsIgnoreCase(sourceNode)).findFirst().orElse(null);
    GraphNode targetGraphNode = adjacency.getGraphNodes().stream().filter(node -> node.getName().equalsIgnoreCase(targetNode)).findFirst().orElse(null);

    if (sourceGraphNode == null || targetGraphNode == null) {
      System.out.println("Either source or target node does not exists!");
      return;
    }

    // Traversing the graph using BFS
    queue.add(sourceGraphNode);

    while (!queue.isEmpty()) {
      GraphNode graphNode = queue.remove();
      if (!graphNode.isVisited()) {
        graphNode.setVisited(true);

        for (GraphNode adjacentNode : adjacency.getAdjacentNodes(graphNode)) {
          if (!adjacentNode.isVisited()) {
            if (adjacentNode.getPreviousNode() == null) {
              adjacentNode.setPreviousNode(graphNode);
            }
            queue.add(adjacentNode);
          }
        }
      }
    }

    // Printing the shortest path from sourceNode to targetNode
    Stack<GraphNode> stack = new Stack<GraphNode>();
    stack.push(targetGraphNode);

    GraphNode previousNode = targetGraphNode.getPreviousNode();

    while (previousNode != null) {
      stack.push(previousNode);
      previousNode = previousNode.getPreviousNode();
    }

    System.out.printf("Shorted Path Between %s and %s:\n", sourceNode, targetNode);
    while (!stack.isEmpty()) {
      System.out.printf(" %s ", stack.pop());
    }
  }
}

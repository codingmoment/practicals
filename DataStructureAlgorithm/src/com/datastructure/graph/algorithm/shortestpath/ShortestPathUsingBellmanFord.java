package com.datastructure.graph.algorithm.shortestpath;

import java.util.Stack;

import com.datastructure.graph.implementation.Adjacency;
import com.datastructure.graph.implementation.Adjacency.Edge;
import com.datastructure.graph.implementation.GraphNode;

/**
 * Implementation of single source shortest path algorithm using Bellman Ford.
 */
public class ShortestPathUsingBellmanFord {

  public void findShortestPath(Adjacency adjacency, String sourceNode, String targetNode) {
    GraphNode sourceGraphNode = adjacency.getGraphNodes().stream().filter(node -> node.getName().equalsIgnoreCase(sourceNode)).findFirst().orElse(null);
    GraphNode targetGraphNode = adjacency.getGraphNodes().stream().filter(node -> node.getName().equalsIgnoreCase(targetNode)).findFirst().orElse(null);

    if (sourceGraphNode == null || targetGraphNode == null) {
      System.out.println("Either source or target node does not exists!");
      return;
    }

    // Setting distance of all vertices as infinite from the source vertex
    adjacency.getGraphNodes().forEach(graphNode -> graphNode.setDistanceFromSource(Integer.MAX_VALUE));

    // Setting distance of source vertex as 0
    sourceGraphNode.setDistanceFromSource(0);

    // Traversing vertices from 0 to V-1
    for (int i = 0; i < adjacency.getGraphNodes().size() - 1; i++) {
      // Traversing all the edges
      for (Edge edge : adjacency.getEdges()) {
        if (edge.getSourceNode().getDistanceFromSource() == Integer.MAX_VALUE) {
          continue;
        }
        // If the target node of current edge has more weight than source node weight plus edge weight, set the new weight
        if (edge.getTargetNode().getDistanceFromSource() > edge.getSourceNode().getDistanceFromSource() + edge.getWeight()) {
          edge.getTargetNode().setDistanceFromSource(edge.getSourceNode().getDistanceFromSource() + edge.getWeight());
          edge.getTargetNode().setPreviousNode(edge.getSourceNode());
        }
      }
    }
    
    // Identifying if there is any negative cycle
    for (Edge edge : adjacency.getEdges()) {
      if (edge.getTargetNode().getDistanceFromSource() > edge.getSourceNode().getDistanceFromSource() + edge.getWeight()) {
        System.out.println("Negative cycle exists!");
        return;
      }
    }

    System.out.printf("\nShortest distance of node %s from %s is %s.\n", targetNode, sourceNode, targetGraphNode.getDistanceFromSource());

    // Printing the shortest path from sourceNode to targetNode
    Stack<GraphNode> stack = new Stack<GraphNode>();
    stack.push(targetGraphNode);

    GraphNode previousNode = targetGraphNode.getPreviousNode();

    while (previousNode != null) {
      stack.push(previousNode);
      previousNode = previousNode.getPreviousNode();
    }

    while (!stack.isEmpty()) {
      System.out.printf(" %s ", stack.pop());
    }
  }
}

package com.datastructure.graph.algorithm.shortestpath;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import com.datastructure.graph.implementation.Adjacency;
import com.datastructure.graph.implementation.GraphNode;

/**
 * Implementation of single source shortest path algorithm using Dijkstra.
 */
public class ShortestPathUsingDijkstra {

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

    // Put the source node into minHeap. In Java, PriorityQueue can be used as minHeap
    PriorityQueue<GraphNode> queue = new PriorityQueue<GraphNode>(new GraphNodeComparator());
    queue.add(sourceGraphNode);

    while (!queue.isEmpty()) {
      // Get the next node from the queue
      GraphNode currentNode = queue.remove();

      // Get the adjacent nodes from current node
      List<GraphNode> adjacentNodes = adjacency.getAdjacentNodes(currentNode);

      for (GraphNode adjacentNode : adjacentNodes) {
        // If the adjacent node's distance from source is greater than current node's distance plus the edge distance, then set the adjacent node's distance
        if (currentNode.getDistanceFromSource() + adjacency.getWeightOfEdge(currentNode, adjacentNode) < adjacentNode.getDistanceFromSource()) {
          adjacentNode.setDistanceFromSource(currentNode.getDistanceFromSource() + adjacency.getWeightOfEdge(currentNode, adjacentNode));

          // Set the new parent
          adjacentNode.setPreviousNode(currentNode);

          // Refresh the priority queue
          queue.remove(adjacentNode);
          queue.add(adjacentNode);
        }
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

  private class GraphNodeComparator implements Comparator<GraphNode> {

    @Override
    public int compare(GraphNode o1, GraphNode o2) {
      return o1.getDistanceFromSource() - o2.getDistanceFromSource();
    }

  }
}

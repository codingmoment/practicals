package com.datastructure.graph.algorithm.minspanningtree;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.datastructure.graph.implementation.Adjacency;
import com.datastructure.graph.implementation.GraphNode;

public class PrimsAlgorithm {

  public int minimumSpanningTree(Adjacency adjacency) {
    // Setting distance (weight) of all vertices as infinite
    adjacency.getGraphNodes().forEach(graphNode -> graphNode.setDistanceFromSource(Integer.MAX_VALUE));

    // Setting distance of any node as 0
    GraphNode graphNode = adjacency.getGraphNodes().get(0);
    graphNode.setDistanceFromSource(0);

    // Put the node into minHeap. In Java, PriorityQueue can be used as minHeap
    PriorityQueue<GraphNode> queue = new PriorityQueue<GraphNode>(new GraphNodeComparator());
    queue.add(graphNode);

    while (!queue.isEmpty()) {
      // Get the next node from the queue
      GraphNode currentNode = queue.remove();

      // Get the adjacent nodes from current node
      List<GraphNode> adjacentNodes = adjacency.getAdjacentNodes(currentNode);

      for (GraphNode adjacentNode : adjacentNodes) {
        if (!adjacentNode.isVisited()) {
          // If the adjacent node's current distance is greater than the distance from current node, then set the adjacent node's distance
          if (adjacentNode.getDistanceFromSource() > adjacency.getWeightOfEdge(currentNode, adjacentNode)) {
            adjacentNode.setDistanceFromSource(adjacency.getWeightOfEdge(currentNode, adjacentNode));
            
            adjacentNode.setPreviousNode(currentNode);

            // Refresh the priority queue
            queue.remove(adjacentNode);
            queue.add(adjacentNode);
          }
        }
      }

      // Mark the current node as visited
      currentNode.setVisited(true);
    }

    int cost = 0;

    for (GraphNode node : adjacency.getGraphNodes()) {
      System.out.println(node.getPreviousNode() + " - " + node.getName() + " : " + node.getDistanceFromSource());
      cost += node.getDistanceFromSource();
    }
    
    System.out.println("Cost: " + cost);

    return cost;
  }

  private class GraphNodeComparator implements Comparator<GraphNode> {
    @Override
    public int compare(GraphNode o1, GraphNode o2) {
      return o1.getDistanceFromSource() - o2.getDistanceFromSource();
    }
  }

}

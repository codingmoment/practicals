package com.datastructure.graph.algorithm.shortestpath;

import com.datastructure.graph.implementation.Adjacency;

/**
 * Implementation of all pair shortest path algorithm using Floy Warshall.
 */
public class ShortestPathUsingFloydWarshall {
  public void findShortestPath(Adjacency adjacency) {

    // Create a table (matrix) of V x V size where V is number of vertices.
    int numberOfVertices = adjacency.getGraphNodes().size();
    int[][] weightMatrix = new int[numberOfVertices][numberOfVertices];

    // Initialize matrix with infinite weights
    for (int i = 0; i < weightMatrix.length; i++) {
      for (int j = 0; j < weightMatrix[i].length; j++) {
        weightMatrix[i][j] = Integer.MAX_VALUE;
      }
    }
    
    // Copy weights into weight matrix
    for (int i = 0; i < adjacency.getGraphNodes().size(); i++) {
      for (int j = 0; j < adjacency.getGraphNodes().size(); j++) {
        if (i == j) {
          weightMatrix[i][j] = 0;
        }
        else {
          weightMatrix[i][j] = adjacency.getWeightOfEdge(adjacency.getGraphNodes().get(i), adjacency.getGraphNodes().get(j));
        }
      }
    }

    // Applying Floyd Warshall algorithm
    for (int v = 0; v < adjacency.getGraphNodes().size(); v++) {
      for (int i = 0; i < weightMatrix.length; i++) {
        for (int j = 0; j < weightMatrix[i].length; j++) {
          if (weightMatrix[i][v] == Integer.MAX_VALUE || weightMatrix[v][j] == Integer.MAX_VALUE) {
            continue;
          }
          if (weightMatrix[i][j] > weightMatrix[i][v] + weightMatrix[v][j]) {
            weightMatrix[i][j] = weightMatrix[i][v] + weightMatrix[v][j];
          }
        }
      }
    }

    // Printing shortest distances
    for (int i = 0; i < adjacency.getGraphNodes().size(); i++) {
      for (int j = 0; j < adjacency.getGraphNodes().size(); j++) {
        System.out.println(adjacency.getGraphNodes().get(i) + " and " + adjacency.getGraphNodes().get(j) + " = " + (weightMatrix[i][j] == Integer.MAX_VALUE ? "Not Reachable" : weightMatrix[i][j]));
      }
    }
  }
}
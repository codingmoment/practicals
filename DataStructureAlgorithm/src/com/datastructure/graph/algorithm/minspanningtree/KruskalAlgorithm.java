package com.datastructure.graph.algorithm.minspanningtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.datastructure.graph.implementation.Adjacency;
import com.datastructure.graph.implementation.Adjacency.Edge;
import com.datastructure.graph.implementation.GraphNode;

public class KruskalAlgorithm {

  public int minimumSpanningTree(Adjacency adjacency) {
    int cost = 0;

    // Create disjoint set for each vertex
    // Purpose of disjoint set is to detect cycle, which we will see later in the program
    DisjointSet<GraphNode> disjointSet = DisjointSet.makeSet(adjacency.getGraphNodes().toArray(new GraphNode[0]));

    List<Edge> edges = adjacency.getEdges();

    // Sort the edges by weight
    Collections.sort(edges, new EdgeByWeightComparator());

    List<Edge> chosenEdges = new ArrayList<>();
    for (Edge edge : edges) {
      // If the sourceNode and the targetNode are in same set, it means they are already connected somehow
      // So, we should not consider this edge, as it will create a cycle
      if(disjointSet.findSet(edge.getSourceNode()) != disjointSet.findSet(edge.getTargetNode())) {
        disjointSet.union(edge.getSourceNode(), edge.getTargetNode());
        cost += edge.getWeight();
        chosenEdges.add(edge);
      }
    }

    System.out.println("Edges: " + chosenEdges);
    return cost;
  }

  private class EdgeByWeightComparator implements Comparator<Edge> {

    @Override
    public int compare(Edge e1, Edge e2) {
      return e1.getWeight() - e2.getWeight();
    }

  }
}

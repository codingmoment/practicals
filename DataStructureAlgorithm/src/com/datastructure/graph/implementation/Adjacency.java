package com.datastructure.graph.implementation;

import java.util.Collections;
import java.util.List;

/**
 * Interface for Adjacency Matrix and Adjacency List.
 */
public interface Adjacency {

  /**
   * Adds an edge between fromNode and toNode
   */
  void addEdge(GraphNode fromNode, GraphNode toNode);

  /**
   * Adds weighted edge between fromNode and toNode
   */
  default void addWeightedEdge(GraphNode fromNode, GraphNode toNode, int weight) {
  };

  default List<Edge> getEdges() {
    return Collections.emptyList();
  }

  default int getWeightOfEdge(GraphNode fromNode, GraphNode toNode) {
    return 0;
  };

  /**
   * Gets all the nodes in the graph
   */
  List<GraphNode> getGraphNodes();

  /**
   * Sets the nodes in the graph
   */
  void setGraphNodes(List<GraphNode> graphNodes);

  /**
   * Gets the adjacent nodes for the given node Returns the neighbor nodes that can be traversed from the given node
   */
  List<GraphNode> getAdjacentNodes(GraphNode graphNode);

  /**
   * Class representing edge between two vertices
   */
  class Edge {
    private GraphNode sourceNode;
    private GraphNode targetNode;
    private int weight;

    public Edge(GraphNode sourceNode, GraphNode targetNode, int weight) {
      this.sourceNode = sourceNode;
      this.targetNode = targetNode;
      this.weight = weight;
    }

    public GraphNode getSourceNode() {
      return sourceNode;
    }

    public void setSourceNode(GraphNode sourceNode) {
      this.sourceNode = sourceNode;
    }

    public GraphNode getTargetNode() {
      return targetNode;
    }

    public void setTargetNode(GraphNode targetNode) {
      this.targetNode = targetNode;
    }

    public int getWeight() {
      return weight;
    }

    public void setWeight(int weight) {
      this.weight = weight;
    }
    
    @Override
    public String toString() {
      return sourceNode + "-" + targetNode + " " + weight;
    }

  }

}

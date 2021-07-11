package com.datastructure.graph.implementation;

/**
 * Represents a node in a graph
 */
public class GraphNode {
  // Name of the node
  private String name;
  // Whether the node is visited
  private boolean isVisited;
  // Previous node in the traversal
  // Used in Single Source Shorted Path algorithms
  private GraphNode previousNode;
  // Used in Weighted Single Source Shortest Path algorithms
  private int distanceFromSource;

  public GraphNode(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isVisited() {
    return isVisited;
  }

  public void setVisited(boolean isVisited) {
    this.isVisited = isVisited;
  }

  public GraphNode getPreviousNode() {
    return previousNode;
  }

  public void setPreviousNode(GraphNode previousNode) {
    this.previousNode = previousNode;
  }

  public int getDistanceFromSource() {
    return distanceFromSource;
  }

  public void setDistanceFromSource(int distanceFromSource) {
    this.distanceFromSource = distanceFromSource;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }

  @Override
  public boolean equals(Object node) {
    return this.getName().equals(((GraphNode) node).getName());
  }
}

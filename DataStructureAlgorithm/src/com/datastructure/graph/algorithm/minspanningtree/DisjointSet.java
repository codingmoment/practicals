package com.datastructure.graph.algorithm.minspanningtree;

import java.util.HashSet;
import java.util.Set;

public class DisjointSet<T> {

  // Internal sets containint actual elements
  private Set<CustomSet<T>> internalSets = new HashSet<>();

  private DisjointSet() {
  }

  /**
   * Create a Set for each element
   */
  public static <T> DisjointSet<T> makeSet(T... elements) {
    DisjointSet<T> disjointSet = new DisjointSet<>();

    for (T element : elements) {
      // Create a Set for each element
      // Add that Set into disjoint set
      disjointSet.internalSets.add(new CustomSet<T>(element));
    }

    return disjointSet;
  }

  /**
   * Merges the two sets where one set contains x and other contains y
   * 
   * @return
   */
  public CustomSet<T> union(T x, T y) {
    // Find internal sets for x and y
    CustomSet<T> setX = findSet(x);
    CustomSet<T> setY = findSet(y);

    if (setX == setY) {
      return setX;
    }

    if (setX.internalSet.size() > setY.internalSet.size()) {
      // Merge Y into X
      setX.internalSet.addAll(setY.internalSet);
      internalSets.remove(setY);
      return setX;
    }
    else {
      // Merge X into Y
      setY.internalSet.addAll(setX.internalSet);
      internalSets.remove(setX);
      return setY;
    }
  }

  /**
   * Finds and returns a Set that contains x
   */
  public CustomSet<T> findSet(T x) {
    for (CustomSet<T> set : internalSets) {
      if (set.internalSet.contains(x)) {
        return set;
      }
    }
    return null;
  }

  public void print() {
    for (CustomSet<T> customSet : internalSets) {
      System.out.println("Set : " + customSet);
    }
  }

  @Override
  public String toString() {
    return internalSets.toString();
  }

  private static class CustomSet<T> {

    private Set<T> internalSet = new HashSet<T>();
    private String hashCode = "";

    public CustomSet(T element) {
      internalSet.add(element);
      hashCode = element.toString();
    }

    @Override
    public String toString() {
      return internalSet.toString();
    }

    @Override
    public int hashCode() {
      return hashCode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }

      if (!(obj instanceof CustomSet)) {
        return false;
      }

      return this.internalSet.containsAll(((CustomSet) obj).internalSet);

    }
  }
}

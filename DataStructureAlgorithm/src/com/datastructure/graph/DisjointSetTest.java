package com.datastructure.graph;

import com.datastructure.graph.algorithm.minspanningtree.DisjointSet;

public class DisjointSetTest {
  public static void main(String[] args) {

    DisjointSet<Character> charDisjoinSet = DisjointSet.makeSet('A', 'B', 'C', 'D', 'E');

    System.out.println("Initial Set:");
    charDisjoinSet.print();

    charDisjoinSet.union('A', 'B');

    System.out.println("Union A & B:");
    charDisjoinSet.print();

    charDisjoinSet.union('A', 'E');

    System.out.println("Union A & E:");
    charDisjoinSet.print();

    charDisjoinSet.union('B', 'E');

    System.out.println("Union B & E:");
    charDisjoinSet.print();

  }
}

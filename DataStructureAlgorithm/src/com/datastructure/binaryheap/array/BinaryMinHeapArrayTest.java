package com.datastructure.binaryheap.array;

public class BinaryMinHeapArrayTest {
  public static void main(String[] args) {
    testExtract();
  }

  private static BinaryMinHeapArray testInsert() {
    BinaryMinHeapArray heap = new BinaryMinHeapArray(20);

    try {
      heap.insert(3);
      heap.print();
      heap.insert(5);
      heap.print();
      heap.insert(8);
      heap.print();
      heap.insert(17);
      heap.print();
      heap.insert(19);
      heap.print();
      heap.insert(36);
      heap.print();
      heap.insert(40);
      heap.print();
      heap.insert(25);
      heap.print();
      heap.insert(100);
      heap.print();
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return heap;
  }

  private static void testExtract() {
    BinaryMinHeapArray heap = testInsert();
    System.out.println("Extracting..");
    try {
      int value = heap.extractMin();
      System.out.println("Value = " + value);
      heap.print();
      value = heap.extractMin();
      System.out.println("Value = " + value);
      heap.print();
      value = heap.extractMin();
      System.out.println("Value = " + value);
      heap.print();
      value = heap.extractMin();
      System.out.println("Value = " + value);
      heap.print();
      value = heap.extractMin();
      System.out.println("Value = " + value);
      heap.print();
      value = heap.extractMin();
      System.out.println("Value = " + value);
      heap.print();
      value = heap.extractMin();
      System.out.println("Value = " + value);
      heap.print();
      value = heap.extractMin();
      System.out.println("Value = " + value);
      heap.print();
      value = heap.extractMin();
      System.out.println("Value = " + value);
      heap.print();
      value = heap.extractMin();
      System.out.println("Value = " + value);
      heap.print();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}

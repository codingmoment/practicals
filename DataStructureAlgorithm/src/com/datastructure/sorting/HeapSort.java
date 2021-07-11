package com.datastructure.sorting;

import com.datastructure.binaryheap.array.BinaryMinHeapArray;

public class HeapSort {

  public static void main(String[] args) {
    HeapSort algorithm = new HeapSort();

    int[] array = { 1 };
    algorithm.printArray(array);
    array = algorithm.sort(array);
    algorithm.printArray(array);

    array = new int[] { 2, 1 };
    algorithm.printArray(array);
    array = algorithm.sort(array);
    algorithm.printArray(array);

    array = new int[] { 2, 1, 5, 3, 9, 4 };
    algorithm.printArray(array);
    array = algorithm.sort(array);
    algorithm.printArray(array);

  }

  public int[] sort(int[] inputArray) {
    // Here we are creating binary heap from scratch using new blank array. This will requrie O(n) space complexity.
    // However, we can utilize just the given inputArray to achieve O(1) space complexity.
    BinaryMinHeapArray binaryHeap = new BinaryMinHeapArray(inputArray.length + 1);

    try {
      // Inserting all the elements into binary heap
      for (int i : inputArray) {
        binaryHeap.insert(i);
      }

      // Extracting elements from the binary heap
      for (int i = 0; i < inputArray.length; i++) {
        inputArray[i] = binaryHeap.extractMin();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return inputArray;
  }

  private void printArray(int[] array) {
    System.out.print("[");
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
      if (i < array.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.print("]\n");
  }
}

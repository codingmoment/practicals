package com.datastructure.sorting;

public class InsertionSort {

  public static void main(String[] args) {
    InsertionSort algorithm = new InsertionSort();

    int[] array = { 1 };
    algorithm.printArray(array);
    array = algorithm.sort(array);
    algorithm.printArray(array);

    array = new int[] { 2, 1 };
    algorithm.printArray(array);
    array = algorithm.sort(array);
    algorithm.printArray(array);

    array = new int[] { 2, 1, 5, 3, 9, 4};
    algorithm.printArray(array);
    array = algorithm.sort(array);
    algorithm.printArray(array);

  }

  public int[] sort(int[] inputArray) {

    for (int i = 0; i < inputArray.length; i++) { // Right part, the unsorted part
      for (int j = i - 1; j >= 0; j--) {          // Left part, the sorted part
        if (inputArray[j] > inputArray[j + 1]) {
          int temp = inputArray[j];
          inputArray[j] = inputArray[j + 1];
          inputArray[j + 1] = temp;
        }
      }
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

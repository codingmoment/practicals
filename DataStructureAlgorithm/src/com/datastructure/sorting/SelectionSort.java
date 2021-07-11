package com.datastructure.sorting;

public class SelectionSort {
  public static void main(String[] args) {
    SelectionSort algorithm = new SelectionSort();

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

    int minIndex = 0;

    for (int i = 0; i < inputArray.length - 1; i++) {
      minIndex = i;
      for (int j = i; j < inputArray.length; j++) {
        if (inputArray[minIndex] > inputArray[j]) {
          minIndex = j;
        }
      }
      int temp = inputArray[i];
      inputArray[i] = inputArray[minIndex];
      inputArray[minIndex] = temp;
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

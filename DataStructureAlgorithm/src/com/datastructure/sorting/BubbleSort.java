package com.datastructure.sorting;

public class BubbleSort {

  public static void main(String[] args) {
    BubbleSort algorithm = new BubbleSort();

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

    for (int i = 0; i < inputArray.length - 1; i++) {
      for (int j = 0; j < inputArray.length - 1 - i; j++) {
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

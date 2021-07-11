package com.datastructure.sorting;

public class QuickSort {

  public static void main(String[] args) {
    QuickSort algorithm = new QuickSort();

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

    if (inputArray.length <= 1) {
      return inputArray;
    }

    return quickSort(inputArray, 0, inputArray.length - 1);
  }

  private int[] quickSort(int[] inputArray, int startIndex, int endIndex) {
    
    if(startIndex >= endIndex) {
      return inputArray;
    }

    int pivotIndex = partition(inputArray, startIndex, endIndex);
    quickSort(inputArray, startIndex, pivotIndex - 1);
    quickSort(inputArray, pivotIndex + 1, endIndex);

    return inputArray;
  }

  private int partition(int[] inputArray, int startIndex, int endIndex) {
    int pivotIndex = endIndex;
    int i = startIndex - 1;
    
    for(int j = startIndex; j <= endIndex; j++) {
      if(inputArray[j] <= inputArray[pivotIndex]) {
        i++;
        int temp = inputArray[i];
        inputArray[i] = inputArray[j];
        inputArray[j]= temp;
      }
    }
    
    return i;
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

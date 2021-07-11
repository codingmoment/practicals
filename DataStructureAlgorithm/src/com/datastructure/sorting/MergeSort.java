package com.datastructure.sorting;

public class MergeSort {

  public static void main(String[] args) {
    MergeSort algorithm = new MergeSort();

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

    inputArray = mergeSort(inputArray, 0, inputArray.length - 1);

    return inputArray;
  }

  private int[] mergeSort(int[] inputArray, int startIndex, int endIndex) {
    // There is only single cell, we cannot divide it further
    if (startIndex >= endIndex) {
      return inputArray;
    }

    // Find the middle index
    int middleIndex = (startIndex + endIndex) / 2;
    // Divide and sort the left part of the array
    mergeSort(inputArray, startIndex, middleIndex);
    // Divide and sort the right part of the array
    mergeSort(inputArray, middleIndex + 1, endIndex);
    // Finally merge the two parts of the array
    return merge(inputArray, startIndex, middleIndex, endIndex);
  }

  private int[] merge(int[] inputArray, int startIndex, int middleIndex, int endIndex) {
    int[] leftArray = new int[middleIndex - startIndex + 1];
    int[] rightArray = new int[endIndex - middleIndex];

    // Copy values from inputArray into leftArray and rightArray
    int leftArrayIndex = 0;
    for (int i = startIndex; i <= middleIndex; i++, leftArrayIndex++) {
      leftArray[leftArrayIndex] = inputArray[i];
    }
    int rightArrayIndex = 0;
    for (int i = middleIndex + 1; i <= endIndex; i++, rightArrayIndex++) {
      rightArray[rightArrayIndex] = inputArray[i];
    }

    // Merge values of leftArray and rightArray into inputArray
    leftArrayIndex = rightArrayIndex = 0;
    for (int i = startIndex; i <= endIndex; i++) {
      if (leftArrayIndex < leftArray.length && rightArrayIndex < rightArray.length) {
        if (leftArray[leftArrayIndex] < rightArray[rightArrayIndex]) {
          inputArray[i] = leftArray[leftArrayIndex];
          leftArrayIndex++;
        }
        else {
          inputArray[i] = rightArray[rightArrayIndex];
          rightArrayIndex++;
        }
      }
      else if (leftArrayIndex < leftArray.length) {
        inputArray[i] = leftArray[leftArrayIndex];
        leftArrayIndex++;
      }
      else if (rightArrayIndex < rightArray.length) {
        inputArray[i] = rightArray[rightArrayIndex];
        rightArrayIndex++;
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

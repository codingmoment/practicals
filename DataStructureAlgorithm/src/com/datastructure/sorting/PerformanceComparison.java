package com.datastructure.sorting;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class PerformanceComparison {

  public static void main(String[] args) {
    int baseArray[] = new int[100000];

    for (int i = 0; i < baseArray.length; i++) {
      baseArray[i] = new Random().nextInt(10000000);
    }
    
    // Arrays.parallelSort Sort
    int[] inputArray = copyArray(baseArray);
    long startTime = new Date().getTime();
    Arrays.parallelSort(inputArray);
    if (!isSorted(inputArray)) {
      System.out.println("Arrays.parallelSort Failed!");
    }
    System.out.println("Arrays.parallelSort: " + (new Date().getTime() - startTime));

    // Arrays.sort Sort
    inputArray = copyArray(baseArray);
    startTime = new Date().getTime();
    Arrays.sort(inputArray);
    if (!isSorted(inputArray)) {
      System.out.println("Arrays.sort Failed!");
    }
    System.out.println("Arrays.sort: " + (new Date().getTime() - startTime));

    // Bubble Sort
    inputArray = copyArray(baseArray);
    startTime = new Date().getTime();
    inputArray = new BubbleSort().sort(inputArray);
    if (!isSorted(inputArray)) {
      System.out.println("Bubble Sort Failed!");
    }
    System.out.println("Bubble Sort: " + (new Date().getTime() - startTime));

    // Selection Sort
    inputArray = copyArray(baseArray);
    startTime = new Date().getTime();
    inputArray = new SelectionSort().sort(inputArray);
    if (!isSorted(inputArray)) {
      System.out.println("Selection Sort Failed!");
    }
    System.out.println("Selection Sort: " + (new Date().getTime() - startTime));

    // Insertion Sort
    inputArray = copyArray(baseArray);
    startTime = new Date().getTime();
    inputArray = new InsertionSort().sort(inputArray);
    if (!isSorted(inputArray)) {
      System.out.println("Insertion Sort Failed!");
    }
    System.out.println("Insertion Sort: " + (new Date().getTime() - startTime));

    // Bucket Sort
    inputArray = copyArray(baseArray);
    startTime = new Date().getTime();
    inputArray = new BucketSort().sort(inputArray);
    if (!isSorted(inputArray)) {
      System.out.println("Bucket Sort (with Quick Sort) Failed!");
    }
    System.out.println("Bucket Sort (with Quick Sort): " + (new Date().getTime() - startTime));

    // Merge Sort
    inputArray = copyArray(baseArray);
    startTime = new Date().getTime();
    inputArray = new MergeSort().sort(inputArray);
    if (!isSorted(inputArray)) {
      System.out.println("Merge Sort Failed!");
    }
    System.out.println("Merge Sort: " + (new Date().getTime() - startTime));

    // Quick Sort
    inputArray = copyArray(baseArray);
    startTime = new Date().getTime();
    inputArray = new QuickSort().sort(inputArray);
    if (!isSorted(inputArray)) {
      System.out.println("Quick Sort Failed!");
    }
    System.out.println("Quick Sort: " + (new Date().getTime() - startTime));
    
    // Heap Sort
    inputArray = copyArray(baseArray);
    startTime = new Date().getTime();
    inputArray = new HeapSort().sort(inputArray);
    if (!isSorted(inputArray)) {
      System.out.println("Heap Sort Failed!");
    }
    System.out.println("Heap Sort: " + (new Date().getTime() - startTime));
  }

  private static int[] copyArray(int[] baseArray) {
    int[] copiedArray = new int[baseArray.length];
    for (int i = 0; i < baseArray.length; i++) {
      copiedArray[i] = baseArray[i];
    }
    return copiedArray;
  }

  private static boolean isSorted(int[] sortedArray) {
    int previousInt = 0;

    if (sortedArray.length <= 1) {
      return true;
    }
    else {
      previousInt = sortedArray[0];
    }

    for (int i : sortedArray) {
      if (i < previousInt)
        return false;
      previousInt = i;
    }

    return true;
  }
}

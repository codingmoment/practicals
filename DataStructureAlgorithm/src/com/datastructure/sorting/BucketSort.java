package com.datastructure.sorting;

import java.util.ArrayList;
import java.util.List;

public class BucketSort {

  public static void main(String[] args) {
    BucketSort algorithm = new BucketSort();

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

    // Finding the number of required buckets
    int numberOfBuckets = (int) Math.ceil(Math.sqrt(inputArray.length));
    List<Integer>[] bucketList = new ArrayList[numberOfBuckets];

    // Initialize buckets
    for (int i = 0; i < bucketList.length; i++) {
      bucketList[i] = new ArrayList<Integer>();
    }

    int maxNumber = inputArray[0];

    for (int number : inputArray) {
      maxNumber = Math.max(maxNumber, number);
    }

    // Putting elements into buckets
    for (int number : inputArray) {
      int bucketNumber = (int) Math.ceil(((number * 1.0) / (maxNumber * 1.0)) * numberOfBuckets);
      if (bucketNumber == 0)
        bucketNumber = 1;
      bucketList[bucketNumber - 1].add(number);
    }

    int[][] bucketArray = new int[numberOfBuckets][];

    // Sorting buckets
    for (int i = 0; i < bucketList.length; i++) {
      bucketArray[i] = new int[bucketList[i].size()];

      for (int j = 0; j < bucketList[i].size(); j++) {
        bucketArray[i][j] = bucketList[i].get(j);
      }

      QuickSort quickSort = new QuickSort();
      quickSort.sort(bucketArray[i]);

    }

    int index = 0;

    // Merging sorted buckets
    for (int i = 0; i < bucketArray.length; i++) {
      for (int j = 0; j < bucketArray[i].length; j++) {
        inputArray[index++] = bucketArray[i][j];
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

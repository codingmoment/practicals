package com.codility.lessons.counting_elements;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * You are given an integer m (1 <= m <= 1000000) and two non-empty, zero-indexed arrays A and B
 * of n integers (0 <= a[i],b[i] <= m).
 * 
 * The goal is to check whether there is a swap operation which can be performed on these
 * arrays in such a way that the sum of elements in array A equals the sum of elements in
 * array B after the swap. By swap operation we mean picking one element from array A and
 * one element from array B and exchanging them.
 */
public class SwapElements {

  /**
   * Idea:
   * - Get the sum of both arrays.
   * - For each element in array A, check if there is an element in array B
   *   such as swapping those elements make the sums of both arrays equal.
   *   
   * - Time complexity is O(n square).
   */
  public boolean slowSolution(Integer[] a, Integer[] b, int m) {
    // Get the sum of both arrays
    int sumA = Arrays.asList(a).stream().collect(Collectors.summingInt(Integer::valueOf));
    int sumB = Arrays.asList(b).stream().collect(Collectors.summingInt(Integer::valueOf));

    // For each element in array A
    for (int i : a) {
      // Check if there is an element in array B
      for (int j : b) {
        // Sum of array A after swapping i and j
        // We take out i and add j from original sumA
        int newSumA = sumA + (j - i);
        // Sum of array B after swapping i and j
        // We take out j and add i from original sumB
        int newSumB = sumB + (i - j);

        // Check if new sums are equal
        if (newSumA == newSumB) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Idea:
   * - Get the sum of both arrays.
   * - Find the difference DIFF between the sums of two arrays.
   * - In order to make the sums of both array equal, we need to distribute the difference equally between them.
   * - For each element in array A
   *   - Check if there exists an element in array B such that difference between those elements is DIFF / 2.
   *   
   * For example:
   * A = {2, 3, 5}
   * B = {1, 2, 5}
   * 
   * Sum_A = 10
   * Sum_B = 8
   * 
   * Difference = 2
   * 
   * This means that we need to reduce 1 from array A and add 1 to array B.
   * 
   * For each element in array A, check if there an element in array B such that 1 gets deducted from A and gets added into B.
   * So, if for A[0] which is 2, check if we have (2 - 1) i.e. 1 in array B.
   * 
   */
  public boolean fastSolution(Integer[] a, Integer[] b, int m) {
    // Get the sum of both arrays
    int sumA = Arrays.asList(a).stream().collect(Collectors.summingInt(Integer::valueOf));
    int sumB = Arrays.asList(b).stream().collect(Collectors.summingInt(Integer::valueOf));

    // Find the difference DIFF between the sums of two arrays.
    int diff = sumA - sumB;

    if (diff % 2 != 0) {
      return false;
    }

    diff /= 2;

    Integer[] counterArrayB = getCounterArray(b, m);

    // For each element in array A
    for (int number : a) {
      // The expected swapping number in B that will make the difference equal
      int expectedNumberInB = number - diff;

      // Check if expectedNumberInB exists in array B
      // 0 < expectedNumberInB < m because we use expectedNumberInB as index in counterArrayB
      if (expectedNumberInB > 0 && expectedNumberInB < m && counterArrayB[expectedNumberInB] > 0) {
        return true;
      }
    }

    return false;
  }

  private Integer[] getCounterArray(Integer[] array, int maxNumber) {
    Integer[] counterArray = new Integer[maxNumber];

    for (int number : array) {
      counterArray[number]++;
    }

    return counterArray;
  }
}

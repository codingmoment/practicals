package com.codility.lessons.sorting.triangle;

import java.util.Arrays;

/**
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/6-sorting/triangle/
 * 
 * Idea:
 * Sort the array.
 * 
 * After sorting, let's say we have a, b, c. 
 * In a sequence of increasing order, it will be always like: a > b > c .
 * By default this means: 
 * a + c > b
 * b + c > a
 * So, we only need to check if: a + b > c
 * 
 * Another point is that should we just check: A[i] > A[i + 2] - A[i + 1] or should we compare A[i] with all the possible combinations?
 * 
 * Let's take an example: 2 9 19 20
 * Here, 2 9 19 does not form a triangle, but 2 19 20 does.
 * 
 * Let's say, a = 2, b = 19 and c = 20
 * Now, a + b > c which is what we want to check
 * If a + b > c, then obviously (a + x) + b > c (provided a + x < b < c)
 * That means, all the elements after a and before b can form the triangle with b and c.
 * That means, we now know if 2 can form triangle with 19 and 20
 * Then, 9 can also form triangle with 19 and 20
 * 
 * Hence, to improve performance and to get time complexity of O(1), we just compare: A[i] > A[i + 2] - A[i + 1]
 */
public class Solution {

  public static void main(String[] args) {
  }

  public int solution(int[] A) {
    // Sort the array
    Arrays.sort(A);

    for (int i = 0; i < A.length - 2; i++) {
      // It is not possible to achieve the solution with negative number in the triplet.
      // This is because adding negative only reduces the value instead of increasing the value, while we want the sum should be greater than the third value.
      if (A[i] >= 0) {
        /*
         * After sorting, let's say we have a, b, c. 
         * In a sequence of increasing order, it will be always like: a > b > c .
         * By default this means: 
         * a + c > b
         * b + c > a
         * So, we only need to check if: a + b > c
         * 
         * Another point is that should we just check: A[i] + A[i + 1] > A[i + 2] or should we compare A[i] with all the possible combinations?
         * 
         * Let's take an example: 2 9 19 20
         * Here, 2 9 19 does not form a triangle, but 2 19 20 does.
         * 
         * Let's say, a = 2, b = 19 and c = 20
         * Now, a + b > c which is what we want to check.
         * If a + b > c, then obviously (a + x) + b > c (provided a + x < b < c)
         * That means, all the elements after a and before b can form the triangle with b and c.
         * That means, we now know if 2 can form triangle with 19 and 20
         * Then, 9 can also form triangle with 19 and 20
         * 
         * Hence, to improve performance and to get time complexity of O(1), we just compare: A[i] + A[i + 1] > A[i + 2]
         */
        if (A[i] + -A[i + 1] > A[i + 2]) {
          return 1;
        }
      }
    }

    return 0;
  }

}

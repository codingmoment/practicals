package com.datastructure.divideconquer;

/**
 * @formatter:off
 * In this program, we demonstrate application of Divide and Conquer.
 * 
 * The problem is to find the number of ways we can represent the given number into sum of 1, 3 and 4.
 * 
 * For example: The number 4 can be represented as:
 * {1, 1, 1, 1}, {1, 3}, {3, 1}, {4}
 * 
 * So, the answer for 4 would be 4 ways.
 * 
 * Now, if we need to find the number of ways for 5, THEN THE TRICK IS:
 * Number of ways for 5 = Number of ways for 4 + Number of ways for 2 + Number of ways for 1
 * 
 * How?
 * We need to add 1 in each combination for 4: {1, 1, 1, 1, '1'}, {1, 3, '1'}, {3, 1, '1'}, {4, '1'} 
 * We need to add 2 in each combination for 3: {1, 1, 1, '2'}, {3, '2'}
 * We need to add 4 in each combination for 1: {1, '4'}
 * 
 * Why 4, 2 and 1?
 * Because: (5-1) = 4, (5-3) = 2 and (5-4) = 1
 * We subtract 1, 3 and 4 from 5.
 *
 * @formatter:on
 */
public class NumberFactor {
  
  public static void main(String[] args) {
    System.out.printf("The number 5 can be represented in %s ways\n", waysToGet(5));
    System.out.printf("The number 6 can be represented in %s ways\n", waysToGet(6));
    System.out.printf("The number 20 can be represented in %s ways\n", waysToGet(20));
  }

  private static int waysToGet(int number) {
    if (number == 0 || number == 1 || number == 2) {
      // 0: {} 1: {1} 2:{1, 1}
      return 1;
    }
    if (number == 3) {
      // 3: {1, 1, 1}, {3}
      return 2;
    }

    return waysToGet(number - 1) + waysToGet(number - 3) + waysToGet(number - 4);
  }
}
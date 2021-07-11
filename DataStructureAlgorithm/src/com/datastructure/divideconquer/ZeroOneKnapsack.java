package com.datastructure.divideconquer;

/**
 * @formatter:off
 * In this program, we demonstrate application of Divide and Conquer.
 * 
 * The problem is to find the which elements should be picked to get maximum profit.
 * Each element has a weight and profit associated with it. You can pick the elements whose total weight is within the given capacity.
 * 
 * 
 * For example: 
 *   weights = { 3, 1, 5, 2 };
 *   profits = { 31, 26, 72, 17 };
 *   capacity = 7
 *   
 * You have to pick the elements from above array whose total weight is less than or equal to 7 and the combination results in maximum profit.
 * 
 * How do we apply Divide and Conquer:
 * ==================================
 * When we are at any index, we have two options:
 * - Pick that element
 *   - You reduce the available capacity and move to the next element
 * - Do not pick that element
 *   - You just move to the next element
 * 
 * @formatter:on
 */
public class ZeroOneKnapsack {

  public static void main(String[] args) {
    ZeroOneKnapsack zeroOneKnapsack = new ZeroOneKnapsack();

    int[] weights = { 1, 2, 3, 4, 5 };
    int[] profits = { 10, 20, 30, 40, 50 };
    int capacity = 7;
    int maxProfit = zeroOneKnapsack.knapsack(profits, weights, capacity, 0);
    System.out.println("Max Profit = " + maxProfit);

    weights = new int[] { 3, 1, 5, 2 };
    profits = new int[] { 31, 26, 72, 17 };
    capacity = 7;
    maxProfit = zeroOneKnapsack.knapsack(profits, weights, capacity, 0);
    System.out.println("Max Profit = " + maxProfit);

  }

  private int knapsack(int[] profits, int[] weights, int capacity, int currentIndex) {

    // Base case
    if (capacity <= 0 || currentIndex < 0 || currentIndex >= weights.length) {
      return 0;
    }

    int profit1 = 0;
    if (weights[currentIndex] <= capacity) {
      // Taking current element
      profit1 = profits[currentIndex] + knapsack(profits, weights, capacity - weights[currentIndex], currentIndex + 1);
    }

    int profit2 = knapsack(profits, weights, capacity, currentIndex + 1);

    return Math.max(profit1, profit2);
  }
}

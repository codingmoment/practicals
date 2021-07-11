package com.datastructure.divideconquer;

/**
 * @formatter:off
 * In this program, we demonstrate application of Divide and Conquer.
 * 
 * We are given houses where each house contains some amount of money.
 * A thief want to steal maximum amount of money from these houses.
 * But he cannot steal the money from the adjacent houses.
 * So, we need to find the maximum amount of money that the thief can steal.
 * 
 * For example: The array {6, 7, 1, 30, 8, 2, 4} represents the amount of money in the houses.
 * For this example, the output should be {7, 30, 4} i.e. 41.
 * 
 * Applying Divide and Conquer, we can say that:
 * - Thief can choose to steal money from first house, so he cannot steal money from second house.
 *   Then he can continue from 3rd house.
 * - Thief can skip the first house, and continue from 2nd house.
 * 
 * So, we can say that:
 * Maximum Amount = Maximum (current_house + max_amount(current_house + 2), max_amount(current_house + 1))
 * 
 * @formatter:on
 */
public class HouseTheif {

  public static void main(String[] args) {
    int[] amountInHouses = { 6, 7, 1, 30, 8, 2, 4 };
    System.out.println("Maximum stolen amount = " + maxStolenAmount(amountInHouses, 0));

    amountInHouses = new int[]{ 20, 5, 1, 13, 6, 11, 40 };
    System.out.println("Maximum stolen amount = " + maxStolenAmount(amountInHouses, 0));
  }

  private static int maxStolenAmount(int[] amountInHouses, int houseIndex) {
    if (houseIndex >= amountInHouses.length) {
      return 0;
    }

    int stealCurrentHouse = amountInHouses[houseIndex] + maxStolenAmount(amountInHouses, houseIndex + 2);
    int skipCurrentHouse = maxStolenAmount(amountInHouses, houseIndex + 1);
    return Math.max(stealCurrentHouse, skipCurrentHouse);
  }
}

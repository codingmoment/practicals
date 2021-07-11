package com.datastructure.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * Algorithm to get the minimum number of coins / notes to make a change for R rupees.
 */
public class CoinChange {

  public static void main(String[] args) {
    CoinChange coinChange = new CoinChange();

    int rupees = 2000;
    List<Integer> coins = coinChange.change(rupees);
    System.out.println(rupees + ": " + coins);

    rupees = 2045;
    coins = coinChange.change(rupees);
    System.out.println(rupees + ": " + coins);

    rupees = 121;
    coins = coinChange.change(rupees);
    System.out.println(rupees + ": " + coins);

    rupees = 7473;
    coins = coinChange.change(rupees);
    System.out.println(rupees + ": " + coins);

  }

  public List<Integer> change(int rupees) {
    int[] denominations = { 1, 5, 10, 20, 50, 100, 500, 1000 };

    List<Integer> coins = new ArrayList<>();

    // Find the maximum denomination that is lesser than value of rupees
    for (int i = denominations.length - 1; i >= 0; i--) {
      if (denominations[i] <= rupees) {
        // Add that maximum denomination into the list of coins
        coins.add(denominations[i]);
        // Deduct the value of denomination from rupees
        rupees -= denominations[i];

        // If value of rupees become zero that means we have got all the coinns
        if (rupees == 0) {
          break;
        }
        i++;
      }
    }

    return coins;
  }
}

package com.datastructure.divideconquer;

/**
 * @formatter:off
 * In this program, we demonstrate application of Divide and Conquer.
 * 
 * The problem is to find the longest common subsequence between two strings.
 * A subsequence is a sequence derived from the given string by deleting some elements without changing the order of remaining elements.
 * 
 * For example, if we take a string "ABCDE".
 * Then below are some of the subsequences with length 3:
 *  - ABC
 *  - ADE
 *  - ABD
 *  - ACE
 * 
 * Below are some of the subsequences with length 4:
 *  - ABCD
 *  - ABDE
 *  - ACDE 
 *  
 * How do we apply Divide and Conquer:
 * ==================================
 * Given two strings S1 and S2, there are three scenario:
 * - The elements are matching
 *   - You proceed with next elements in both S1 and S2
 * - The elements are not matching
 *   - You proceed with next element in S1
 * - The elements are not matching
 *   - You proceed with next element in S2
 *  
 * @formatter:on
 */
public class LongestCommonSubsequence {

  public static void main(String[] args) {
    LongestCommonSubsequence lcs = new LongestCommonSubsequence();

    String s1 = "spruha";
    String s2 = "harsh";
    System.out.printf("%s %s => %s\n", s1, s2, lcs.findLCSLength(s1, s2, 0, 0));

    s1 = "temperature";
    s2 = "pressure";
    System.out.printf("%s %s => %s\n", s1, s2, lcs.findLCSLength(s1, s2, 0, 0));

  }

  private int findLCSLength(String s1, String s2, int i1, int i2) {

    if (i1 == s1.length() || i2 == s2.length()) {
      return 0;
    }

    int length1 = 0, length2 = 0, length3 = 0;
    if (s1.charAt(i1) == s2.charAt(i2)) {
      length1 = 1 + findLCSLength(s1, s2, i1 + 1, i2 + 1);
    }
    else {
      length2 = findLCSLength(s1, s2, i1, i2 + 1);
      length3 = findLCSLength(s1, s2, i1 + 1, i2);
    }

    return Math.max(Math.max(length1, length2), length3);
  }
}

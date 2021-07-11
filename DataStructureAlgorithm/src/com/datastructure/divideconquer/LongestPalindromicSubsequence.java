package com.datastructure.divideconquer;

/**
 * @formatter:off
 * In this program, we demonstrate application of Divide and Conquer.
 * 
 * The problem is to find the length of longest palindromic subsequence for a given string.
 * A subsequence is a sequence derived from the given string by deleting some elements without changing the order of remaining elements.
 * A palindrome is a string that reads the same backwards as well as forward and can be odd or even length.
 * 
 * For example, given a string "AMEEWMEA", the longest palindromic subsequence will be "AMEEMA".
 *  
 * How do we apply Divide and Conquer:
 * ==================================
 * Given a string, there are three scenario:
 * - The start and end characters (indexes) are matching
 *   - You increment the start index by 1 and decrement the end index by 1
 * - The start and end characters (indexes) are not matching
 *   - You increment the start index by 1
 * - The start and end characters (indexes) are not matching
 *   - You decrement the end index by 1
 *  
 * @formatter:on
 */
public class LongestPalindromicSubsequence {
  public static void main(String[] args) {
    LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();

    System.out.println("Max palindromic : " + lps.findLPS("temperature", 0, "temperature".length() - 1));
  }

  private int findLPS(String s, int startIndex, int endIndex) {
    if (startIndex > endIndex) {
      // If we have traversed more than half of the string, return 0
      return 0;
    }
    if (startIndex == endIndex) {
      // If both indexes are at same position then its a palindrome with single character
      return 1;
    }

    int len1 = 0, len2 = 0, len3 = 0;

    if (s.charAt(startIndex) == s.charAt(endIndex)) {
      len1 = 2 + findLPS(s, startIndex + 1, endIndex - 1);
    }
    len2 = findLPS(s, startIndex + 1, endIndex);
    len3 = findLPS(s, startIndex, endIndex - 1);

    return Math.max(Math.max(len1, len2), len3);
  }
}

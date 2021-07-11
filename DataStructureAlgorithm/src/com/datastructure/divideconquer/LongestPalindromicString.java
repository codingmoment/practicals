package com.datastructure.divideconquer;

/**
 * @formatter:off
 * In this program, we demonstrate application of Divide and Conquer.
 * 
 * The problem is to find the length of longest palindromic substring for a given string.
 * A palindrome is a string that reads the same backwards as well as forward and can be odd or even length.
 * 
 * For example, given a string "AXTBHKHBTYA", the longest palindromic subsequence will be "TBHKHBT".
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
public class LongestPalindromicString {
  public static void main(String[] args) {
    LongestPalindromicString lps = new LongestPalindromicString();

    System.out.println("Max palindromic : " + lps.findLPS("AXTBHKHBTYA", 0, "AXTBHKHBTYA".length() - 1));
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
      int remainingLength = endIndex - startIndex - 1;
      if (remainingLength == findLPS(s, startIndex + 1, endIndex - 1)) {
        len1 = remainingLength + 2;
      }
    }
    len2 = findLPS(s, startIndex + 1, endIndex);
    len3 = findLPS(s, startIndex, endIndex - 1);

    return Math.max(Math.max(len1, len2), len3);
  }
}

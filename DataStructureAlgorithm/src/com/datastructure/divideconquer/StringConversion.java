package com.datastructure.divideconquer;

/**
 * @formatter:off
 * In this program, we demonstrate application of Divide and Conquer.
 * 
 * The problem is to convert a string S2 into a string S1 with minimum number of operations.
 * Operation can be insert, delete or replace a character.
 * 
 * For example: If we want to convert 'cable' into 'table', then we can do that with single operation - replace the first character 'c' with 't'.
 * For example: If we want to convert 'ctable' into 'table', then we can do that with single operation - delete the first character 'c'.
 * 
 * The trick is to count the number of operations required to convert the remaining part of the string after doing an operation.
 * For example, S1 = "table" and S2 = "tgable"
 * If we delete 'g', then we will need to get number of operations required to convert "able" (from 3rd char of S2) into "able" ( from 2nd char of S1)
 *  
 * For example, S1 = "table" and S2 = "tble"
 * If we insert 'a', then we will need to get number of operations required to convert "ble" (from 2nd char of S2) into "ble" (from 3rd char of S1) 
 * 
 * For example, S1 = "table" and S2 = "tcble"
 * If we replace 'c' by 'a', then we will need to get number of operations required to convert "ble" (from 3rd char of S2) into "ble" (from 3rd char of S1)
 * 
 * THERE ARE THREE WAYS TO MATCH THE STRINGS - INSERT, DELETE, REPLACE.
 * WE HAVE TO FIND WHICH OPERATION RESULTS INTO MINIMUM NUMBER, SO WE NEED TO TRY ALL THREE OPERATIONS AND COMPARE THEIR RESULTS.
 *  
 * @formatter:on
 */
public class StringConversion {
  
  public static void main(String[] args) {
    System.out.println("From 'tgble' to 'table' " + minimumOperations("table", "tgble", 0, 0));
    System.out.println("From 'maximum' to 'minimum' " + minimumOperations("minimum", "maximum", 0, 0));
    System.out.println("From 'understand' to 'standover' " + minimumOperations("standover", "understand", 0, 0));
    System.out.println("From 'harsh' to 'spruha' " + minimumOperations("spruha", "harsh", 0, 0));
    System.out.println("From 'swati' to 'nilesh' " + minimumOperations("nilesh", "swati", 0, 0));
  }

  private static int minimumOperations(String target, String source, int index1, int index2) {
    if (index1 >= target.length()) {
      // If string S1 ends, that means we need to delete the rest of the characters from S2.
      return source.length() - index2;
    }
    if (index2 >= source.length()) {
      // If string S2 ends, that means we need to insert the rest of the characters from S1 into S2.
      return target.length() - index1;
    }

    if (target.charAt(index1) == source.charAt(index2)) {
      // If the characters at current indexes are matching, check the number of operations required for remaining parts.
      return minimumOperations(target, source, index1 + 1, index2 + 1);
    }

    // Finding the number of subsequent operations if we go with INSERT.
    // With INSERT: S2 index remains at index2, S2 index moves ahead by 1.
    int insertion = 1 + minimumOperations(target, source, index1 + 1, index2);
    // Finding the number of subsequent operations if we go with DELETE.
    // With DELETE: S2 index moves ahead by 1, S2 index remains at index1
    int deletion = 1 + minimumOperations(target, source, index1, index2 + 1);
    // Finding the number of subsequent operations if we go with REPLACE.
    // With REPLACE: both S1 and S2 move ahead by 1.
    int replacement = 1 + minimumOperations(target, source, index1 + 1, index2 + 1);

    int minimum = Math.min(Math.min(insertion, deletion), replacement);
    return minimum;
  }

}

package com.codility.lessons.stack_queue.fish;

import java.util.Stack;

/**
 * Solution for Codility lesson:
 * https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/
 * 
 * Idea:
 * Create a Stack to store the fishes. The Stack stores the fish index
 * .
 * A fish can either be flowing downstream or upstream.
 * 
 * If a fish is flowing upstream, then push the fish into the Stack.
 * 
 * If a fish is flowing downstream, then check if there are immediate fishes flowing upstream.
 * The fish flowing downstream will keep eating the smaller fishes flowing upstream,
 * till it gets eaten by a larger fish flowing upstream or the previous fish is flowing downstream.
 * 
 * At the end, the number of fishes in the Stack are the live fishes.
 */
public class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();
    int liveFishes = solution.solution(new int[] { 0, 1 }, new int[] { 1, 1 });
    System.out.println(liveFishes);
  }

  public int solution(int[] A, int[] B) {
    // Create a Stack to store the fishes. The Stack stores the fish index.
    Stack<Integer> liveFishes = new Stack<>();

    for (int index = A.length - 1; index >= 0; index--) {
      if (liveFishes.isEmpty()) {
        liveFishes.push(index);
        continue;
      }

      // A fish can either be flowing downstream or upstream.
      // If a fish is flowing upstream, then push the fish into the Stack.
      if (B[index] == 0) {
        liveFishes.push(index);
      }
      else {
        boolean shouldInsertNewFish = true;
        while (!liveFishes.isEmpty()) {
          Integer previousFishIndex = liveFishes.peek();
          // If a fish is flowing downstream, then check if there are immediate fishes flowing upstream.
          if (B[previousFishIndex] == 0) {
            // The fish flowing downstream will keep eating the smaller fishes flowing upstream, 
            if (A[previousFishIndex] < A[index]) {
              liveFishes.pop();
            }
            else {
              // till it gets eaten by a larger fish flowing upstream
              shouldInsertNewFish = false;
              break;
            }
          }
          // or the previous fish is flowing downstream.
          else {
            shouldInsertNewFish = false;
            liveFishes.push(index);
            break;
          }
        }

        if (shouldInsertNewFish) {
          liveFishes.push(index);
        }
      }
    }

    // At the end, the number of fishes in the Stack are the live fishes.
    return liveFishes.size();
  }

}

package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Medium;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 */
public class _062_UniquePaths implements Medium {

  /**
   * First of all you should understand that we need to do n + m - 2 movements : m - 1 down, n - 1 right, because we start from cell (1, 1).
   * Secondly, the path it is the sequence of movements( go down / go right), therefore we can say that two paths are different when there is i-th (1 .. m + n - 2) movement in path1 differ i-th movement in path2.
   * So, how we can build paths. Let's choose (n - 1) movements(number of steps to the right) from (m + n - 2), and rest (m - 1) is (number of steps down).
   * I think now it is obvious that count of different paths are all combinations (n - 1) movements from (m + n-2).
   */
  public static class Solution {
    public int uniquePaths(int m, int n) {
      double value = 1;
      for (int i = 1; i <= n - 1; i++)
        value *= ((double) (m + i - 1) / (double) i);
      return (int) Math.round(value);
    }
  }

  public static void main(String args[]) {
    System.out.println(new Solution().uniquePaths(3, 7));
  }
}

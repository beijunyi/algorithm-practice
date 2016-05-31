package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.GreedyAlgorithm;

/**
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in
 * range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches
 * required.
 *
 * Example 1:
 *  nums = [1, 3], n = 6
 *  Return 1.
 *
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 *
 * Example 2:
 *  nums = [1, 5, 10], n = 20
 *  Return 2.
 *  The two patches can be [2, 4].
 *
 * Example 3:
 *  nums = [1, 2, 2], n = 5
 *  Return 0.
 */
public class _330_PatchingArray implements Medium {

  public interface Solution {
    int minPatches(int[] nums, int n);
  }

  /**
   * Time: O(m + log(n))
   *   where m is the length of nums
   * Space: O(1)
   *
   * Let miss be the smallest sum in [0,n] that we might be missing. Meaning we already know we can build all sums in
   * [0,miss). Then if we have a number num <= miss in the given array, we can add it to those smaller sums to build all
   * sums in [0,miss+num). If we don't, then we must add such a number to the array, and it's best to add miss itself,
   * to maximize the reach.
   */
  public static class Solution1 implements Solution, GreedyAlgorithm {

    @Override
    public int minPatches(int[] nums, int n) {
      int miss = 1;
      int patches = 0;
      int i = 0;
      while(miss <= n && miss > 0) {
        if(i < nums.length && nums[i] <= miss)
          miss += nums[i++];
        else {
          miss += miss;
          patches++;
        }
      }
      return patches;
    }

  }

  public static void main(String args[]) {
    int[] nums;
    int target;
    int result;

    for(Solution s : Arrays.asList(new Solution1())) {
      nums = new int[] {1, 3};
      target = 6;
      result = s.minPatches(nums, target);
      System.out.println(result);

      nums = new int[] {1, 5, 10};
      target = 20;
      result = s.minPatches(nums, target);
      System.out.println(result);

      nums = new int[] {1, 2, 2};
      target = 5;
      result = s.minPatches(nums, target);
      System.out.println(result);

      nums = new int[] {1, 2, 4, 13, 43};
      target = 100;
      result = s.minPatches(nums, target);
      System.out.println(result);
    }
  }



}

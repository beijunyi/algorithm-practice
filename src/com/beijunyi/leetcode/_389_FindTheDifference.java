package com.beijunyi.leetcode;

import java.util.Arrays;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.category.solution.BitManipulation;

/**
 * Given two strings s and t which consist of only lowercase letters.
 *
 * String t is generated by random shuffling string s and then add one more letter at a random position.
 *
 * Find the letter that was added in t.
 *
 * Example:
 *   Input:
 *     s = "abcd"
 *     t = "abcde"
 *
 *   Output:
 *     e
 *
 *   Explanation:
 *     'e' is the letter that was added.
 */
public class _389_FindTheDifference implements Easy {

  public interface Solution {
    char findTheDifference(String s, String t);
  }

  public static class Solution1 implements Solution, BitManipulation {
    @Override
    public char findTheDifference(String s, String t) {
      int mask = 0;
      mask = mutateMask(mask, s);
      mask = mutateMask(mask, t);
      int i = 0;
      for(; i < 26; i++) {
        int check = 1 << i;
        if((check & mask) != 0) break;
      }
      return (char) (i + 'a');
    }

    private static int mutateMask(int mask, String s) {
      for(int i = 0; i < s.length(); i++) {
        int cIndex = s.charAt(i) - 'a';
        int cMask = 1 << cIndex;
        mask = mask^cMask;
      }
      return mask;
    }
  }

  public static class Solution2 implements Solution {

    @Override
    public char findTheDifference(String s, String t) {
      char c = t.charAt(s.length()); // the last char of t
      for(int i = 0; i < s.length(); ++i) c ^= s.charAt(i) ^ t.charAt(i);
      return c;
    }

  }

  public static void main(String args[]) {
    String s;
    String t;
    char result;

    for(Solution solution : Arrays.asList(new Solution1(), new Solution2())) {
      s = "abcdd";
      t = "adcdbe";
      result = solution.findTheDifference(s, t);
      System.out.println(result);
    }
  }

}

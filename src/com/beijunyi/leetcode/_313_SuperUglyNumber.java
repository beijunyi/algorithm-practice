package com.beijunyi.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

import com.beijunyi.leetcode.category.difficulty.Medium;
import com.beijunyi.leetcode.category.solution.DynamicPrograming;
import com.beijunyi.leetcode.category.solution.WithHeap;

/**
 * Write a program to find the nth super ugly number.
 *
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
 * For example:
 *   [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32]
 *   is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
 *
 * Note:
 *   (1) 1 is a super ugly number for any given primes.
 *   (2) The given numbers in primes are in ascending order.
 *   (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 */
public class _313_SuperUglyNumber implements Medium {

  public interface Solution {
    int nthSuperUglyNumber(int n, int[] primes);
  }

  /**
   * Time: O(n * m), Space: O(n + m) where
   *   m is the number of primes
   *
   * Same idea as _264_UglyNumberTwo solution 1.
   */
  public static final class Solution1 implements Solution, DynamicPrograming {
    @Override
    public int nthSuperUglyNumber(int n, int[] primes) {
      int[] uglyNumbers = new int[n];
      uglyNumbers[0] = 1;

      int[] nextMultiplyIndex = new int[primes.length]; // the next ugly number that the factor should multiply
      for(int i = 1; i < n; i++) {

        int minFactorIndex = -1;
        int minProduct = -1;
        for(int j = 0; j < primes.length; j++) {
          int product = uglyNumbers[nextMultiplyIndex[j]] * primes[j];
          if(minProduct == -1 || minProduct > product) {
            minFactorIndex = j;
            minProduct = product;
          } else if(minProduct == product) { // e.g. 6=2*3=3*2. ditch the second one
            nextMultiplyIndex[j]++;
          }
        }

        nextMultiplyIndex[minFactorIndex]++;
        uglyNumbers[i] = minProduct;
      }

      return uglyNumbers[n - 1];
    }
  }

  public static class Solution2 implements Solution, WithHeap, DynamicPrograming {
    @Override
    public int nthSuperUglyNumber(int n, int[] primes) {
      int[] ugly = new int[n];
      ugly[0] = 1;
      PriorityQueue<UglyProduct> heap = new PriorityQueue<>();
      for(int factor : primes) heap.offer(new UglyProduct(factor, 0, factor));
      for(int i = 1; i < n; i++) {
        while(true) {
          UglyProduct next = heap.poll();
          ugly[i] = next.value;
          heap.offer(new UglyProduct(next.factor, next.index + 1, next.factor * ugly[next.index + 1]));
          if(ugly[i] != ugly[i - 1]) break;
        }
      }
      return ugly[n - 1];
    }

    private static class UglyProduct implements Comparable<UglyProduct> {
      final int factor;
      final int index;
      final int value;

      public UglyProduct(int factor, int index, int value) {
        this.factor = factor;
        this.index = index;
        this.value = value;
      }

      @Override
      public int compareTo(UglyProduct o) {
        return value - o.value;
      }
    }

  }

  public static void main(String args[]) {
    int n;
    int[] primes;
    int r;

    for(Solution s : Arrays.asList(new Solution1(), new Solution2())) {
      n = 37;
      primes = new int[] {2, 7, 13, 19};
      r = s.nthSuperUglyNumber(n, primes);
      System.out.println(r);
    }
  }

}

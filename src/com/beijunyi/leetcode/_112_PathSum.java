package com.beijunyi.leetcode;

import com.beijunyi.leetcode.category.difficulty.Easy;
import com.beijunyi.leetcode.ds.TreeNode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 *
 * For example:
 * Given the below binary tree and sum = 22,
 *        5
 *       / \
 *      4   8
 *     /   / \
 *    11  13  4
 *   /  \      \
 *  7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class _112_PathSum implements Easy {

  public static class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
      if(root == null)
        return false;
      sum -= root.val;
      return sum == 0 &&  root.left == null && root.right == null
               || hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
  }

  public static void main(String args[]) {
    TreeNode root = new TreeNode(1);
    root.right = new TreeNode(2);
    System.out.println(new Solution().hasPathSum(root, 1));
  }
}

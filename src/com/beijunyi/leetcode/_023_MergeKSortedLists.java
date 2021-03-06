package com.beijunyi.leetcode;

import java.util.*;

import com.beijunyi.leetcode.category.difficulty.Hard;
import com.beijunyi.leetcode.ds.ListNode;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class _023_MergeKSortedLists implements Hard {

  public static class Solution1 {
    public ListNode mergeKLists(List<ListNode> lists) {
      if (lists==null||lists.size()==0) return null;

      PriorityQueue<ListNode> queue= new PriorityQueue<>(lists.size(), (o1, o2) -> {
        if (o1.val < o2.val)
          return -1;
        else if (o1.val == o2.val)
          return 0;
        else
          return 1;
      });

      ListNode dummy = new ListNode(0);
      ListNode tail=dummy;

      for(ListNode node : lists)
        if(node != null)
          queue.add(node);

      while(!queue.isEmpty()){
        tail.next = queue.poll();
        tail = tail.next;

        if(tail.next != null)
          queue.add(tail.next);
      }
      return dummy.next;
    }
  }

  public static class Solution2 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      if (l1 == null) return l2;
      if (l2 == null) return l1;

      ListNode head=null;
      ListNode former=null;
      while (l1!=null&&l2!=null) {
        if (l1.val>l2.val) {
          if (former==null) former=l2; else former.next=l2;
          if (head==null) head=former; else former=former.next;
          l2=l2.next;
        } else {
          if (former==null) former=l1; else former.next=l1;
          if (head==null) head=former; else former=former.next;
          l1=l1.next;
        }
      }
      if (l2!=null) l1=l2;
      former.next=l1;
      return head;

    }

    public ListNode mergeKLists(List<ListNode> lists) {
      if (lists.size()==0) return null;
      if (lists.size()==1) return lists.get(0);
      if (lists.size()==2) return mergeTwoLists(lists.get(0), lists.get(1));
      return mergeTwoLists(mergeKLists(lists.subList(0, lists.size()/2)),
                            mergeKLists(lists.subList(lists.size()/2, lists.size())));
    }
  }

  public static void main(String args[]) {
    ListNode h1 = new ListNode(0);
    h1.next = new ListNode(2);
    h1.next.next = new ListNode(4);
    h1.next.next.next = new ListNode(6);
    h1.next.next.next.next = null;
    ListNode h2 = new ListNode(1);
    h2.next = new ListNode(3);
    h2.next.next = new ListNode(5);
    h2.next.next.next = new ListNode(7);
    h2.next.next.next.next = null;
    ListNode h3 = new ListNode(1);
    h3.next = new ListNode(2);
    h3.next.next = new ListNode(5);
    h3.next.next.next = new ListNode(6);
    h3.next.next.next.next = null;
    System.out.println(new Solution1().mergeKLists(Arrays.asList(h1, h2, h3)));

    h1 = new ListNode(0);
    h1.next = new ListNode(2);
    h1.next.next = new ListNode(4);
    h1.next.next.next = new ListNode(6);
    h1.next.next.next.next = null;
    h2 = new ListNode(1);
    h2.next = new ListNode(3);
    h2.next.next = new ListNode(5);
    h2.next.next.next = new ListNode(7);
    h2.next.next.next.next = null;
    h3 = new ListNode(1);
    h3.next = new ListNode(2);
    h3.next.next = new ListNode(5);
    h3.next.next.next = new ListNode(6);
    h3.next.next.next.next = null;
    System.out.println(new Solution2().mergeKLists(Arrays.asList(h1, h2, h3)));
  }

}

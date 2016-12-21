package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Test;

public class FindKPairswithSmallestSums {

  /*
  You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
  
  Define a pair (u,v) which consists of one element from the first array and one element from the second array.
  
  Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
  
  Example 1:
  
  Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3
  
  Return: [1,2],[1,4],[1,6]
  
  The first 3 pairs are returned from the sequence:
  [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
  
  Example 2:
  
  Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
  
  Return: [1,1],[1,1]
  
  The first 2 pairs are returned from the sequence:
  [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
  
  Example 3:
  
  Given nums1 = [1,2], nums2 = [3],  k = 3 
  
  Return: [1,3],[2,3]
  
  All possible pairs are returned from the sequence:
  [1,3],[2,3]
  */

  @Test
  public void test() {
    List<int[]> expected = Arrays.asList(new int[] { 1, 2 }, new int[] { 1, 4 }, new int[] { 1, 6 }),
        actual = kSmallestPairs(new int[] { 1, 7, 11 }, new int[] { 2, 4, 6 }, 3);
    for (int i = 0; i < expected.size(); i++)
      assertArrayEquals(expected.get(i), actual.get(i));

    expected = Arrays.asList(new int[] { 1, 1 }, new int[] { 1, 1 });
    actual = kSmallestPairs(new int[] { 1, 1, 2 }, new int[] { 1, 2, 3 }, 2);
    for (int i = 0; i < expected.size(); i++)
      assertArrayEquals(expected.get(i), actual.get(i));

    expected = Arrays.asList(new int[] { 1, 3 }, new int[] { 2, 3 });
    actual = kSmallestPairs(new int[] { 1, 2 }, new int[] { 3 }, 3);
    for (int i = 0; i < expected.size(); i++)
      assertArrayEquals(expected.get(i), actual.get(i));
  }

  public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    List<int[]> result = new ArrayList<>(k);
    PriorityQueue<Node> queue = new PriorityQueue<>(nums2.length, (n1, n2) -> n1.val - n2.val);
    if (nums1.length > 0) for (int i = 0; i < nums2.length; i++)
      queue.offer(new Node(0, i, nums1[0] + nums2[i]));
    for (Node n; k-- > 0 && !queue.isEmpty(); result.add(new int[] { nums1[n.row], nums2[n.col] }))
      if ((n = queue.poll()).row != nums1.length - 1)
        queue.offer(new Node(n.row + 1, n.col, nums1[n.row + 1] + nums2[n.col]));
    return result;
  }

  class Node {
    public int row, col, val;

    public Node(int row, int col, int val) {
      this.row = row;
      this.col = col;
      this.val = val;
    }
  }
}

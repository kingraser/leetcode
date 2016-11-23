package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.PriorityQueue;

import org.junit.Test;

public class KthSmallestElementinaSortedMatrix {

  /*
  Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
  
  Note that it is the kth smallest element in the sorted order, not the kth distinct element.
  
  Example:
  
  matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
  ],
  k = 8,
  
  return 13.
  
  Note:
  You may assume k is always valid, 1 ≤ k ≤ n^2.
  */

  @Test
  public void test() {
    assertEquals(2000000000, kthSmallestI(new int[][] { { 2000000000 } }, 1));
    assertEquals(13, kthSmallestI(new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } }, 8));

    assertEquals(2000000000, kthSmallestII(new int[][] { { 2000000000 } }, 1));
    assertEquals(13, kthSmallestII(new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } }, 8));
  }

  // binary search
  public int kthSmallestI(int[][] matrix, int k) {
    int n = matrix.length, min = matrix[0][0], max = matrix[n - 1][n - 1];
    for (int mid; min <= max;)
      if (getLessEqual(matrix, mid = min + ((max - min) >> 1)) < k) min = mid + 1;
      else max = mid - 1;
    return min;
  }

  private int getLessEqual(int[][] matrix, int val) {
    int result = 0;
    for (int i = matrix.length - 1, j = 0; i >= 0 && j < matrix.length;)
      if (matrix[i][j] > val) i--;
      else {
        result += i + 1;
        j++;
      }
    return result;
  }

  // heap
  public int kthSmallestII(int[][] matrix, int k) {
    PriorityQueue<Node> pq = new PriorityQueue<Node>((n1, n2) -> n1.val - n2.val);
    for (int i = 0; i < matrix.length; i++)
      pq.offer(new Node(0, i, matrix[0][i]));
    for (Node node; --k > 0;)
      if ((node = pq.poll()).row != matrix.length - 1)
        pq.offer(new Node(node.row + 1, node.col, matrix[node.row + 1][node.col]));
    return pq.poll().val;
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

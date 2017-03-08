package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class ConvertSortedArraytoBinarySearchTree {

  //Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

  @Test
  public void test() {
    assertEquals(tree("3,2,1,n,n,n,5,4,n,n,n"), sortedArrayToBST(new int[] { 1, 2, 3, 4, 5 }));
  }

  public TreeNode sortedArrayToBST(int[] num) {
    return build(num, 0, num.length);
  }

  private TreeNode build(int[] A, int left, int right) {
    if (left >= right) return null;
    int mid = (left + right) >> 1;
    return new TreeNode(A[mid], build(A, left, mid), build(A, mid + 1, right));
  }

}

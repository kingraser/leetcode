package leetcode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VerifyPreorderSequenceinBinarySearchTree {

  /*
  Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
  You may assume each number in the sequence is unique.
  
  Follow up:
  Could you do it using only constant space complexity?
  */

  @Test
  public void test() {
    assertTrue(verifyPreorder(new int[] { 5, 2, 1, 3, 6 }));
  }

  public boolean verifyPreorder(int[] preorder) {
    int low = Integer.MIN_VALUE, i = -1;
    for (int a : preorder) {
      if (a < low) return false;
      while (i >= 0 && a > preorder[i])
        low = preorder[i--];
      preorder[++i] = a;
    }
    return true;
  }

}

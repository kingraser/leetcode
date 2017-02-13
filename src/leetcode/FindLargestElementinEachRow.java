package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import leetcode.common.TreeNode;

public class FindLargestElementinEachRow {

  /*
  You need to find the largest value in each row of a binary tree.
  
  Example:
  
  Input: 
  
            1
           / \
          3   2
         / \   \  
        5   3   9 
  
  Output: [1, 3, 9]
  */

  @Test
  public void test() {
    assertArrayEquals(new int[] { 1, 3, 9 }, findValueMostElement(tree("1,3,5,n,n,3,n,n,2,n,9,n,n")));
  }

  public int[] findValueMostElement(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    preOrder(root, 0, list);
    return list.stream().mapToInt(i -> i).toArray();
  }

  private void preOrder(TreeNode node, int row, List<Integer> list) {
    if (node == null) return;
    if (row == list.size()) list.add(node.val);
    else list.set(row, Math.max(list.get(row), node.val));
    preOrder(node.left, row + 1, list);
    preOrder(node.right, row + 1, list);
  }

}

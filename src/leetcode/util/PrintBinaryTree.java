package leetcode.util;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import leetcode.common.TreeNode;

public class PrintBinaryTree {

  /*
  Print a binary tree in an m*n 2D string array following these rules:  
    The row number m should be equal to the height of the given binary tree.
    The column number n should always be an odd number.
    The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
    Each unused space should contain an empty string "".
    Print the subtrees following the same rules.
  
  Example 1:  
  Input:
     1
    /
   2
  Output:
  [["", "1", ""],
  ["2", "", ""]]
  
  Example 2:  
  Input:
     1
    / \
   2   3
    \
     4
  Output:
  [["", "", "", "1", "", "", ""],
  ["", "2", "", "", "", "3", ""],
  ["", "", "4", "", "", "", ""]]
  
  Example 3:  
  Input:
        1
       / \
      2   5
     / 
    3 
   / 
  4 
  Output:  
  [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
  
  Note: The height of binary tree is in the range of [1, 10]. 
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(Arrays.asList("", "1", ""), Arrays.asList("2", "", "")), printTree(tree("1,2,n,n,n")));
    assertEquals(Arrays.asList(Arrays.asList("", "", "", "1", "", "", ""), Arrays.asList("", "2", "", "", "", "3", ""),
        Arrays.asList("", "", "4", "", "", "", "")), printTree(tree("1,2,n,4,n,n,3,n,n")));
    assertEquals(
        Arrays.asList(Arrays.asList("", "", "", "", "", "", "", "1", "", "", "", "", "", "", ""),
            Arrays.asList("", "", "", "2", "", "", "", "", "", "", "", "5", "", "", ""),
            Arrays.asList("", "3", "", "", "", "", "", "", "", "", "", "", "", "", ""),
            Arrays.asList("4", "", "", "", "", "", "", "", "", "", "", "", "", "", "")),
        printTree(tree("1,2,3,4,n,n,n,n,5,n,n")));
  }

  public List<List<String>> printTree(TreeNode root) {
    int height = getHeight(root), size = (1 << height) - 1;
    List<List<String>> result = new ArrayList<>(height);
    for (int i = 0, j; i < height; i++)
      for (result.add(new ArrayList<>(size)), j = 0; j < size; j++)
        result.get(i).add("");
    preOrder(root, result, 0, size, 0);
    return result;
  }

  private void preOrder(TreeNode node, List<List<String>> result, int left, int right, int height) {
    if (node == null) return;
    int idx = (left + right) >> 1;
    result.get(height).set(idx, Integer.toString(node.val));
    preOrder(node.left, result, left, idx - 1, height + 1);
    preOrder(node.right, result, idx + 1, right, height + 1);
  }

  private int getHeight(TreeNode node) {
    return node != null ? Math.max(getHeight(node.left), getHeight(node.right)) + 1 : 0;
  }

}

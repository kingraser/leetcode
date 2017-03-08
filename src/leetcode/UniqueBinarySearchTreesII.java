package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import leetcode.common.TreeNode;

public class UniqueBinarySearchTreesII {

  /*
  Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
  
  For example,
  Given n = 3, your program should return all 5 unique BST's shown below.
  
     1         3     3      2      1
      \       /     /      / \      \
       3     2     1      1   3      2
      /     /       \                 \
     2     1         2                 3
  */

  @Test
  public void test() {
    List<TreeNode> expected = Arrays.asList(tree("1,n,3,2,n,n,n"), tree("3,2,1,n,n,n,n"), tree("3,1,n,2,n,n,n"),
        tree("2,1,n,n,3,n,n"), tree("1,n,2,n,3,n,n")), actual = generateTrees(3);
    assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
  }

  public List<TreeNode> generateTrees(int n) {
    return generateTrees(1, n);
  }

  private List<TreeNode> generateTrees(int left, int right) {
    List<TreeNode> result = new ArrayList<>(), lefts, rights;
    if (left > right) result.add(null);
    else for (int rootValue = left, leftIdx; rootValue <= right; rootValue++)
      for (lefts = generateTrees(left, rootValue - 1), rights = generateTrees(rootValue + 1,
          right), leftIdx = 0; leftIdx < lefts.size(); leftIdx++)
        for (int rightIdx = 0; rightIdx < rights.size(); rightIdx++)
          result.add(new TreeNode(rootValue, lefts.get(leftIdx), rights.get(rightIdx)));
    return result;
  }
}

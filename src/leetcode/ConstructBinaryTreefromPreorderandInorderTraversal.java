package leetcode;

import static leetcode.ConstructBinaryTreefromInorderandPostorderTraversal.find;
import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeNode;

public class ConstructBinaryTreefromPreorderandInorderTraversal {

  @Test
  public void test() {
    assertEquals(tree("1,2,n,4,n,n,3,5,n,n,n"), buildTree(new int[] { 1, 2, 4, 3, 5 }, new int[] { 2, 4, 1, 5, 3 }));
  }

  public TreeNode buildTree(int[] preOrder, int[] inOrder) {
    return buildTree(preOrder, inOrder, 0, preOrder.length, 0, inOrder.length);
  }

  private TreeNode buildTree(int[] preOrder, int[] inOrder, int pFirst, int pLast, int iFirst, int iLast) {
    int inRootPos, leftSize;
    return pFirst == pLast || iFirst == iLast ? null
        : new TreeNode(preOrder[pFirst],
            buildTree(preOrder, inOrder, pFirst + 1,
                pFirst + (leftSize = (inRootPos = find(inOrder, iFirst, iLast, preOrder[pFirst])) - iFirst) + 1, iFirst,
                iFirst + leftSize),
            buildTree(preOrder, inOrder, pFirst + leftSize + 1, pLast, inRootPos + 1, iLast));
  }
}

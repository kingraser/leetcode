package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.junit.Test;

import leetcode.common.TreeNode;

public class ConstructBinaryTreefromInorderandPostorderTraversal {

  @Test
  public void test() {
    assertEquals(tree("1,2,n,4,n,n,3,5,n,n,n"), buildTree(new int[] { 2, 4, 1, 5, 3 }, new int[] { 4, 2, 5, 3, 1 }));
  }

  public TreeNode buildTree(int[] inOrder, int[] postOrder) {
    return buildTree(inOrder, postOrder, 0, inOrder.length, 0, postOrder.length);
  }

  private TreeNode buildTree(int[] inOrder, int[] postOrder, int iFirst, int iLast, int pFirst, int pLast) {
    int inRootPos, leftSize;
    return pFirst == pLast || iFirst == iLast ? null
        : new TreeNode(postOrder[pLast - 1],
            buildTree(inOrder, postOrder, iFirst, inRootPos = find(inOrder, iFirst, iLast, postOrder[pLast - 1]),
                pFirst, pFirst + (leftSize = inRootPos - iFirst)),
            buildTree(inOrder, postOrder, inRootPos + 1, iLast, pFirst + leftSize, pLast - 1));
  }

  public static int find(int[] A, int start, int end, int target) {
    return IntStream.range(start, end).filter(i -> A[i] == target).findFirst().orElse(-1);
  }

}

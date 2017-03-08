package leetcode;

import static leetcode.common.TreeLinkNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeLinkNode;

public class PopulatingNextRightPointersinEachNode {

  /* 
  For example,
  Given the following perfect binary tree,
  
           1
         /  \
        2    3
       / \  / \
      4  5  6  7
  
  After calling your function, the tree should look like:
  
           1 -> NULL
         /  \
        2 -> 3 -> NULL
       / \  / \
      4->5->6->7 -> NULL
  */

  @Test
  public void test() {
    TreeLinkNode root = tree("1,2,4,n,n,5,n,n,3,6,n,n,7,n,n#1->n,2->n,4->n,5->n,3->n,6->n,7->n");
    connect(root);
    assertEquals(root, tree("1,2,4,n,n,5,n,n,3,6,n,n,7,n,n#1->n,2->3,4->5,5->6,3->n,6->7,7->n"));
  }

  public void connect(TreeLinkNode root) {
    PopulatingNextRightPointersinEachNodeII.connect(root);
  }
}

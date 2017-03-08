package leetcode;

import static leetcode.common.TreeLinkNode.tree;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import leetcode.common.TreeLinkNode;

public class PopulatingNextRightPointersinEachNodeII {
  /*
  Follow up for problem "Populating Next Right Pointers in Each Node".    
  What if the given tree could be any binary tree? Would your previous solution still work?
  Note:    
  You may only use constant extra space.
  For example,
  Given the following binary tree,
  
       1
     /  \
    2    3
   / \    \
  4   5    7
  
  After calling your function, the tree should look like:
  
       1 -> NULL
     /  \
    2 -> 3 -> NULL
   / \    \
  4-> 5 -> 7 -> NULL
  */

  @Test
  public void test() {
    TreeLinkNode root = tree("1,2,4,n,n,5,n,n,3,n,7,n,n#1->n,2->n,4->n,5->n,3->n,7->n");
    connect(root);
    assertEquals(root, tree("1,2,4,n,n,5,n,n,3,n,7,n,n#1->n,2->3,4->5,5->7,3->n,7->n"));
  }

  public static void connect(TreeLinkNode root) {
    if (root == null) return;
    TreeLinkNode head = new TreeLinkNode(-1);
    for (TreeLinkNode current = root, previous = head; current != null; current = current.next) {
      if (current.left != null) {
        previous.next = current.left;
        previous = previous.next;
      }
      if (current.right != null) {
        previous.next = current.right;
        previous = previous.next;
      }
    }
    connect(head.next);
  }

}

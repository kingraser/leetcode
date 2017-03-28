package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BoundaryofBinaryTree {

  /*
  Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. 
  Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.  
  Left boundary is defined as the path from root to the left-most node. 
  Right boundary is defined as the path from root to the right-most node. 
  If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. 
  Note this definition only applies to the input binary tree, and not applies to any subtrees.  
  The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. 
  If not, travel to the right subtree. Repeat until you reach a leaf node.  
  The right-most node is also defined by the same way with left and right exchanged.
  
  Example 1  
  Input:
  1
   \
    2
   / \
  3   4  
  Ouput: [1, 3, 4, 2]  
  Explanation:
  The root doesn't have left subtree, so the root itself is left boundary.
  The leaves are node 3 and 4.
  The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
  So order them in anti-clockwise without duplicates and we have [1,3,4,2].
  
  Example 2  
  Input:
        ____1_____
       /          \
      2            3
     / \          / 
    4   5        6   
       / \      / \
      7   8    9  10  
       
  Ouput: [1,2,4,7,8,9,10,6,3]  
  Explanation:
  The left boundary are node 1,2,4. (4 is the left-most node according to definition)
  The leaves are node 4,7,8,9,10.
  The right boundary are node 1,3,6,10. (10 is the right-most node).
  So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].  
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 3, 4, 2), boundaryOfBinaryTree(TreeNode.tree("1,n,2,3,n,n,4,n,n")));
    assertEquals(Arrays.asList(1, 2, 4, 7, 8, 9, 10, 6, 3),
        boundaryOfBinaryTree(TreeNode.tree("1,2,4,n,n,5,7,n,n,8,n,n,3,6,9,n,n,10,n,n,n")));
  }

  public List<Integer> boundaryOfBinaryTree(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (Objects.isNull(root)) return result;
    result.add(root.val);
    left(root.left, result);
    Stream.of(root.left, root.right).forEach(node -> leaves(node, result));
    right(root.right, result);
    return result;
  }

  private void right(TreeNode node, List<Integer> result) {
    if (Objects.isNull(node) || node.isLeaf()) return;
    right(Stream.of(node.right, node.left).filter(Objects::nonNull).findFirst().get(), result);
    result.add(node.val);
  }

  private void leaves(TreeNode node, List<Integer> result) {
    if (Objects.isNull(node)) return;
    if (node.isLeaf()) result.add(node.val);
    else Stream.of(node.left, node.right).forEach(n -> leaves(n, result));
  }

  private void left(TreeNode node, List<Integer> result) {
    if (Objects.isNull(node) || node.isLeaf()) return;
    result.add(node.val);
    left(Stream.of(node.left, node.right).filter(Objects::nonNull).findFirst().get(), result);
  }

}

package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Test;

import leetcode.common.TreeNode;

public class BinaryTreeRightSideView {

  /*
  For example:
  Given the following binary tree,
  
         1            <---
       /   \
      2     3         <---
       \     \
        5     4       <---
  
  You should return [1, 3, 4]. 
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(1, 3, 4), rightSideView(tree("1,2,n,5,n,n,3,n,4,n,n")));
  }

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    rightSideView(root, list, 0);
    return list;
  }

  private void rightSideView(TreeNode root, List<Integer> list, int level) {
    if (Objects.isNull(root)) return;
    if (level == list.size()) list.add(root.val);
    rightSideView(root.right, list, level + 1);
    rightSideView(root.left, list, level + 1);
  }

}

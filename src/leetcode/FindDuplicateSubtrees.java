package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import leetcode.common.TreeNode;

public class FindDuplicateSubtrees {

  /*
  Given a binary tree, return all duplicate subtrees. 
  For each kind of duplicate subtrees, you only need to return the root node of any one of them.
  Two trees are duplicate if they have the same structure with same node values.
  
  Example 1:  
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4  
  The following are two duplicate subtrees:  
      2
     /
    4  
  and  
    4  
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList(tree("4,n,n"), tree("2,4,n,n,n")),
        findDuplicateSubtrees(tree("1,2,4,n,n,n,3,2,4,n,n,n,4,n,n")));
  }

  public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    List<TreeNode> result = new LinkedList<>();
    postorder(root, new HashMap<>(), result);
    return result;
  }

  public String postorder(TreeNode node, Map<String, Integer> map, List<TreeNode> res) {
    if (node == null) return "#";
    String hash = node.val + "," + postorder(node.left, map, res) + "," + postorder(node.right, map, res);
    if (map.merge(hash, 1, Integer::sum) == 2) res.add(node);
    return hash;
  }

}

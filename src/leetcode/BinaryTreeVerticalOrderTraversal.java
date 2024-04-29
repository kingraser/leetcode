package leetcode;

import static leetcode.common.TreeNode.tree;
import static org.junit.Assert.assertEquals;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.Test;

import leetcode.common.Pair;
import leetcode.common.TreeNode;

public class BinaryTreeVerticalOrderTraversal {

  /*
  Given a binary tree, return the vertical order traversal of its node's values. (ie, from top to bottom, column by column).  
  If two nodes are in the same row and column, the order should be from left to right.
  
  Examples:
  Given binary tree [3,9,20,null,null,15,7],
  
    3
   / \
  9  20
    /  \
   15   7
  
  return its vertical order traversal as:  
  [
  [9],
  [3,15],
  [20],
  [7]
  ]
  
  Given binary tree [3,9,20,4,5,2,7],
  
      _3_
     /   \
    9    20
   / \   / \
  4   5 2   7
  
  return its vertical order traversal as:  
  [
  [4],
  [9],
  [3,5,2],
  [20],
  [7]
  ]  
  */

    @Test
    public void test() {
        assertEquals(List.of(List.of(9), List.of(3, 15), List.of(20), List.of(7)),
                verticalOrder(tree("3,9,n,n,20,15,n,n,7,n,n")));
        assertEquals(
                Arrays.asList(List.of(4), List.of(9), List.of(3, 5, 2), List.of(20), List.of(7)),
                verticalOrder(tree("3,9,4,n,n,5,n,n,20,2,n,n,7,n,n")));
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        Deque<Pair<Integer, List<Integer>>> result = new ArrayDeque<>();
        preOrder(root, result, 0);
        return result.stream().map(pair -> pair.value).collect(Collectors.toList());
    }

    private void preOrder(TreeNode root, Deque<Pair<Integer, List<Integer>>> result, int idx) {
        if (Objects.isNull(root)) return;
        if (result.isEmpty()) result.add(new Pair<>(idx, new ArrayList<>(List.of(root.val))));
        else if (idx < result.peekFirst().key)
            result.addFirst(new Pair<>(idx, new ArrayList<>(List.of(root.val))));
        else if (idx > result.peekLast().key)
            result.addLast(new Pair<>(idx, new ArrayList<>(List.of(root.val))));
        else result.stream().filter(pair -> idx == pair.key)
                    .findFirst()
                    .ifPresent(pair -> pair.value.add(root.val));
        preOrder(root.left, result, idx - 1);
        preOrder(root.right, result, idx + 1);
    }

}

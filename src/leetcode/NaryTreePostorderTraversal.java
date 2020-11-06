package leetcode;

import leetcode.common.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Wit
 */
public class NaryTreePostorderTraversal {
    /*
    Given an n-ary tree, return the preorder traversal of its nodes' values.
    Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

    Follow up:
    Recursive solution is trivial, could you do it iteratively?

    Example 1:
         1
       / | \
      3  2  4
     / \
    5   6
    Input: root = [1,null,3,2,4,null,5,6]
    Output: [5,6,3,2,4,1]

    Example 2:
         1
       / | \      \
      2  3  4      5
        / \  \    / \
       6   7  8  9  10
           |  |  |
           11 12 13
           |
           14
    Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
    Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]

    Constraints:
    The height of the n-ary tree is less than or equal to 1000
    The total number of nodes is between [0, 10^4]
    */

    public List<Integer> postorder(Node root) {
        return Objects.isNull(root) ? new ArrayList<>() : postorder(root, new ArrayList<>());
    }

    private List<Integer> postorder(Node root, ArrayList<Integer> list) {
        root.children.forEach(child -> postorder(child, list));
        list.add(root.val);
        return list;
    }
}

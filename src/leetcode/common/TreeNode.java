/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

import java.util.Objects;
import java.util.Stack;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class TreeNode {

    public int val;

    public TreeNode left, right;

    public TreeNode(int x) {
        val = x;
    }

    /*
       1
      / \
     2   3
        / \
       4   5
    
    1,2,n,n,3,4,n,n,5,n,n 
    */
    public static TreeNode generateTree(String s) {
        String[] nodes = s.split(",");
        Stack<TreeNode> stack = new Stack<>();
        for (int i = nodes.length - 1; i >= 0; i--)
            if (nodes[i].charAt(0) == 'n') stack.push(null);
            else {
                TreeNode node = new TreeNode(Integer.parseInt(nodes[i]));
                node.left = stack.pop();
                node.right = stack.pop();
                stack.push(node);
            }
        return stack.peek();
    }

    @Override
    public String toString() {
        return new StringBuilder("{").append(String.format("val:%s", val)).append(",left:")
                .append(Objects.isNull(left) ? null : left.toString()).append(",right:")
                .append(Objects.isNull(right) ? null : right.toString()).append("}").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (Objects.isNull(o) || !(o instanceof TreeNode)) return false;
        TreeNode another = (TreeNode) o;
        if (!Objects.equals(val, another.val)) return false;
        return Objects.equals(left, another.left) && Objects.equals(right, another.right);
    }
}

/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof TreeNode)) return false;
        TreeNode another = (TreeNode) o;
        if (val != another.val) return false;
        return (left == null ? another.left == null : left.equals(another.left))
                && (right == null ? another.right == null : right.equals(another.right));
    }
}

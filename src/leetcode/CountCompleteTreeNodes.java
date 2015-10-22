/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月21日<p>
//-------------------------------------------------------
public class CountCompleteTreeNodes {
    /*
    Given a complete binary tree, count the number of nodes.
    
    Definition of a complete binary tree from Wikipedia:
    In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. 
    It can have between 1 and 2h nodes inclusive at the last level h.
    */

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int hl = 0, hr = 0;
        for (TreeNode l = root.left; l != null; hl++, l = l.left);
        for (TreeNode r = root.right; r != null; hr++, r = r.right);
        if (hl == hr) return (2 << hl) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}

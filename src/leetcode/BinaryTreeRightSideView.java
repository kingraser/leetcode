/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.List;

import com.google.common.collect.Lists;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月15日<p>
//-------------------------------------------------------
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

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = Lists.newArrayList();
        rightSideView(root, list, 0);
        return list;
    }

    private void rightSideView(TreeNode root, List<Integer> list, int i) {
        if (root == null) return;
        if (list.size() == i) list.add(root.val);
        rightSideView(root.right, list, i + 1);
        rightSideView(root.left, list, i + 1);
    }

}

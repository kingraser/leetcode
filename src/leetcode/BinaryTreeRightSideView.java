/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

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

    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList(1, 3, 4), rightSideView(TreeNode.generateTree("1,2,n,5,n,n,3,n,4,n,n")));
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = Lists.newArrayList();
        rightSideView(root, list, 0);
        return list;
    }

    private void rightSideView(TreeNode root, List<Integer> list, int level) {
        if (Objects.isNull(root)) return;
        if (Objects.equals(list.size(), level)) list.add(root.val);
        rightSideView(root.right, list, level + 1);
        rightSideView(root.left, list, level + 1);
    }

}

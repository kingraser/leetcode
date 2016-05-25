/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class BinaryTreeLevelOrderTraversalII {

    /*
    Given a binary tree, 
    return the bottom-up level order traversal of its nodes' values. 
    (ie, from left to right, level by level from leaf to root).
    
    For example:
    Given binary tree {3,9,20,#,#,15,7},
    
         3
        / \
       9  20
         /  \
        15   7
        
    return its level order traversal as:   
    [   
        [15,7],
        [9,20],
        [3]
    ]   
    */

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        traverse(root, 0, result);
        Collections.reverse(result);
        return result;
    }

    private void traverse(TreeNode root, int level, List<List<Integer>> result) {
        if (Objects.isNull(root)) return;
        if (level == result.size()) result.add(new ArrayList<>());
        result.get(level).add(root.val);
        traverse(root.left, ++level, result);
        traverse(root.right, level, result);
    }

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(Arrays.asList(15, 7), Arrays.asList(9, 20), Arrays.asList(3)),
                levelOrder(TreeNode.generateTree("3,9,n,n,20,15,n,n,7,n,n")));
    }

}

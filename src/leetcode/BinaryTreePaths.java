/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class BinaryTreePaths {

    /*
    Given a binary tree, return all root-to-leaf paths.
    
    For example, given the following binary tree:
    
       1
     /   \
    2     3
     \
      5
    
    All root-to-leaf paths are:["1->2->5", "1->3"]
    */

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> answer = new ArrayList<String>();
        if (Objects.nonNull(root)) searchBT(root, "", answer);
        return answer;
    }

    private void searchBT(TreeNode root, String path, List<String> answer) {
        if (Objects.isNull(root.left) && Objects.isNull(root.right)) answer.add(path + root.val);
        if (Objects.nonNull(root.left)) searchBT(root.left, path + root.val + "->", answer);
        if (Objects.nonNull(root.right)) searchBT(root.right, path + root.val + "->", answer);
    }

    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList("1->2->5", "1->3"),
                binaryTreePaths(TreeNode.generateTree("1,2,n,5,n,n,3,n,n")));
    }

}

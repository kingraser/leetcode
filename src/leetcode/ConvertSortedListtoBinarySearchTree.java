/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import leetcode.common.ListNode;
import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月15日<p>
//-------------------------------------------------------
public class ConvertSortedListtoBinarySearchTree {

    //Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST. 
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        for (; null != head; list.add(head.val), head = head.next);
        return build(list, 0, list.size());
    }

    private TreeNode build(List<Integer> list, int left, int right) {
        if (left >= right) return null;
        int mid = (left + right) >> 1;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = build(list, left, mid);
        root.right = build(list, mid + 1, right);
        return root;
    }

    @Test
    public void test() {
        TreeNode expected = TreeNode.generateTree("2,1,n,n,3,n,n");
        Assert.assertEquals(expected, sortedListToBST(ListNode.generateNodes(1, 2, 3)));
    }

}

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

import leetcode.common.ListNode;
import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月15日<p>
//-------------------------------------------------------
public class ConvertSortedListtoBinarySearchTree {

    //Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST. 
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = Lists.newArrayList();
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

    static ListNode node;

    public TreeNode sortedListToBSTII(ListNode head) {
        node = head;//传引用
        return build(0, getLength(head) - 1);
    }

    private int getLength(ListNode head) {//获取长度
        int len = 0;
        for (ListNode n = head; Objects.nonNull(n); len++, n = n.next);
        return len;
    }

    private TreeNode build(int start, int end) {
        if (start > end) return null;
        int mid = (start + end) >> 1;
        TreeNode left = build(start, mid - 1);
        TreeNode parent = new TreeNode(node.val);
        parent.left = left;
        node = node.next;
        parent.right = build(mid + 1, end);
        return parent;
    }

    @Test
    public void test() {
        TreeNode expected=TreeNode.generateTree("2,1,n,n,3,n,n");
        Assert.assertEquals(expected, sortedListToBST(ListNode.generateNodes(1, 2, 3)));
        Assert.assertEquals(expected, sortedListToBSTII(ListNode.generateNodes(1, 2, 3)));
    }

}

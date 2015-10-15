/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.LinkedList;

import leetcode.common.TreeLinkNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月15日<p>
//-------------------------------------------------------
public class PopulatingNextRightPointersinEachNodeII {
    /*
    Follow up for problem "Populating Next Right Pointers in Each Node".    
    What if the given tree could be any binary tree? Would your previous solution still work?
    Note:    
    You may only use constant extra space.
    For example,
    Given the following binary tree,
    
         1
       /  \
      2    3
     / \    \
    4   5    7
    
    After calling your function, the tree should look like:
    
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
    */

    /*
    要处理一个节点,可能需要最右边的兄弟节点,首先想到用广搜。
    但广搜不是常数空间的,本题要求常数空间。
    注意,这题的代码原封不动,也可以解决 Populating Next Right Pointers in Each Node I.
    */

    //bfs
    public void connect(TreeLinkNode root) {
        LinkedList<TreeLinkNode> list = new LinkedList<TreeLinkNode>();
        if (root != null) list.add(root);
        for (int length = 1; !list.isEmpty(); length = list.size()) {
            list.addLast(null);
            while (length-- > 0) {
                TreeLinkNode curr = list.poll();
                curr.next = list.peek();
                if (curr.left != null) list.addLast(curr.left);
                if (curr.right != null) list.addLast(curr.right);
            }
            list.poll();
        }
    }

    //递归
    public void connectII(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode dummy = new TreeLinkNode(-1);
        for (TreeLinkNode curr = root, prev = dummy; curr != null; curr = curr.next) {
            if (curr.left != null) {
                prev.next = curr.left;
                prev = prev.next;
            }
            if (curr.right != null) {
                prev.next = curr.right;
                prev = prev.next;
            }
        }
        connectII(dummy.next);
    }

    //迭代
    public void connectIII(TreeLinkNode root) {
        for (TreeLinkNode next = null; root != null; root = next, next = null) //the first node of next level@turn to next level
            for (TreeLinkNode prev = null; root != null; root = root.next) {//previous node on the same level
                if (next == null) next = root.left == null ? root.right : root.left;
                if (root.left != null) {
                    if (prev != null) prev.next = root.left;
                    prev = root.left;
                }
                if (root.right != null) {
                    if (prev != null) prev.next = root.right;
                    prev = root.right;
                }
            }
    }
}

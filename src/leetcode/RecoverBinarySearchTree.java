/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月14日<p>
//-------------------------------------------------------
public class RecoverBinarySearchTree {

    /*
    Two elements of a binary search tree (BST) are swapped by mistake.
    
    Recover the tree without changing its structure.
    Note:
    A solution using O(n) space is pretty straight forward. Could you devise a constant space solution? 
    */

    /*
    O(n) 空间的解法是,开一个指针数组,中序遍历,将节点指针依次存放到数组里,
    然后寻找两处逆向的位置,先从前往后找第一个逆序的位置,
    然后从后往前找第二个逆序的位置,交换这两个指针的值。
    中序遍历一般需要用到栈,空间也是 O(n) 的,如何才能不使用栈?Morris中序遍历。
    */

    private static TreeNode mistake1, mistake2, prev;

    public void recoverTree(TreeNode root) {
        inOrder(root);
        int tmp = mistake1.val;
        mistake1.val = mistake2.val;
        mistake2.val = tmp;
    }

    private void inOrder(TreeNode root) {
        if (root.left != null) inOrder(root.left);
        if (prev != null && root.val < prev.val) {
            if (mistake1 == null) {
                mistake1 = prev;
                mistake2 = root;
            } else mistake2 = root;
        }
        prev = root;
        if (root.right != null) inOrder(root.right);
    }

}

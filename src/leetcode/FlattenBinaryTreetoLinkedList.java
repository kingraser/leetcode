/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Stack;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月18日<p>
//-------------------------------------------------------
public class FlattenBinaryTreetoLinkedList {

    /*
    Given a binary tree, flatten it to a linked list in-place.
    
    For example,
    Given
    
             1
            / \
           2   5
          / \   \
         3   4   6
    
    The flattened tree should look like:
    
       1
        \
         2
          \
           3
            \
             4
              \
               5
                \
                 6
     */

    public void flatten(TreeNode root) {
        for (TreeNode pre; root != null; root = root.right)
            if (root.left != null) {
                for (pre = root.left; pre.right != null; pre = pre.right);
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
            }
    }

    public void flattenII(TreeNode root) {
        if (root == null) return;// 终止条件
        flattenII(root.left);
        flattenII(root.right);
        if (null == root.left) return;
        TreeNode p = root.left;// 三方合并,将左子树所形成的链表插入到 root 和 root->right 之间
        for (; p.right != null; p = p.right); //寻找左链表最后一个节点
        p.right = root.right;
        root.right = root.left;
        root.left = null;
    }

    public void flattenIII(TreeNode root) {
        flatten(root, null);
    }

    private TreeNode flatten(TreeNode root, TreeNode tail) { // 把 root 所代表树变成链表后,tail 跟在该链表后面
        if (null == root) return tail;
        root.right = flatten(root.left, flatten(root.right, tail));
        root.left = null;
        return root;
    }

    public void flattenIV(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (!s.empty()) {
            TreeNode p = s.pop();
            if (p.right != null) s.push(p.right);
            if (p.left != null) s.push(p.left);
            p.left = null;
            if (!s.empty()) p.right = s.peek();
        }
    }
}

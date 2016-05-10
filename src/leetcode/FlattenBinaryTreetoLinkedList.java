/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Objects;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

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

    @Test
    public void test() {
        TreeNode root = TreeNode.generateTree("1,2,3,n,n,4,n,n,5,n,6,n,n");
        flatten(root);
        Assert.assertEquals(TreeNode.generateTree("1,n,2,n,3,n,4,n,5,n,6,n,n"), root);
        root = TreeNode.generateTree("1,2,3,n,n,4,n,n,5,n,6,n,n");
        flattenII(root);
        Assert.assertEquals(TreeNode.generateTree("1,n,2,n,3,n,4,n,5,n,6,n,n"), root);
        root = TreeNode.generateTree("1,2,3,n,n,4,n,n,5,n,6,n,n");
        flattenIII(root);
        Assert.assertEquals(TreeNode.generateTree("1,n,2,n,3,n,4,n,5,n,6,n,n"), root);
        root = TreeNode.generateTree("1,2,3,n,n,4,n,n,5,n,6,n,n");
        flattenIV(root);
        Assert.assertEquals(TreeNode.generateTree("1,n,2,n,3,n,4,n,5,n,6,n,n"), root);
    }

    public void flatten(TreeNode root) {
        for (TreeNode pre; Objects.nonNull(root); root = root.right)
            if (Objects.nonNull(root.left)) {
                for (pre = root.left; Objects.nonNull(pre.right); pre = pre.right);
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
            }
    }

    public void flattenII(TreeNode root) {
        if (Objects.isNull(root)) return;//终止条件
        flattenII(root.left);
        flattenII(root.right);
        if (Objects.isNull(root.left)) return;
        TreeNode p = root.left;//三方合并,将左子树所形成的链表插入到 root 和 root->right 之间
        for (; Objects.nonNull(p.right); p = p.right); //寻找左链表最后一个节点
        p.right = root.right;
        root.right = root.left;
        root.left = null;
    }

    public void flattenIII(TreeNode root) {
        flatten(root, null);
    }

    private TreeNode flatten(TreeNode root, TreeNode tail) { //把 root所代表树变成链表后,tail跟在该链表后面
        if (Objects.isNull(root)) return tail;
        root.right = flatten(root.left, flatten(root.right, tail));
        root.left = null;
        return root;
    }

    public void flattenIV(TreeNode root) {
        if (Objects.isNull(root)) return;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (!s.empty()) {
            TreeNode p = s.pop();
            if (Objects.nonNull(p.right)) s.push(p.right);
            if (Objects.nonNull(p.left)) s.push(p.left);
            p.left = null;
            if (!s.empty()) p.right = s.peek();
        }
    }
}

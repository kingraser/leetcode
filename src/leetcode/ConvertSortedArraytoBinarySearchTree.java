/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月15日<p>
//-------------------------------------------------------
public class ConvertSortedArraytoBinarySearchTree {

    //Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

    public TreeNode sortedArrayToBST(int[] num) {
        return build(num, 0, num.length);
    }

    private TreeNode build(int[] A, int left, int right) {
        if (left >= right) return null;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(A[mid]);
        root.left = build(A, left, mid);
        root.right = build(A, mid + 1, right);
        return root;
    }

}

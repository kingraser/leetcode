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
public class ConstructBinaryTreefromPreorderandInorderTraversal {

    public TreeNode buildTree(int[] p, int[] i) {//previous order in order
        return buildTree(p, i, 0, p.length, 0, i.length);
    }

    private TreeNode buildTree(int[] p, int[] i, int pFirst, int pLast, int iFirst, int iLast) {
        if (pFirst == pLast || iFirst == iLast) return null;
        TreeNode root = new TreeNode(p[pFirst]);
        int inRootPos = find(i, iFirst, iLast, p[pFirst]), leftSize = inRootPos - iFirst;
        root.left = buildTree(p, i, pFirst + 1, pFirst + leftSize + 1, iFirst, iFirst + leftSize);
        root.right = buildTree(p, i, pFirst + leftSize + 1, pLast, inRootPos + 1, iLast);
        return root;
    }

    private int find(int[] A, int start, int end, int target) {
        for (int i = start; i < end; i++)
            if (A[i] == target) return i;
        return -1;
    }
}

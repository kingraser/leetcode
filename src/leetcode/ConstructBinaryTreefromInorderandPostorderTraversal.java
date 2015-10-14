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
public class ConstructBinaryTreefromInorderandPostorderTraversal {

    public TreeNode buildTree(int[] i, int[] p) {//in order post order
        return buildTree(i, p, 0, i.length, 0, p.length);
    }

    private TreeNode buildTree(int[] i, int[] p, int iFirst, int iLast, int pFirst, int pLast) {
        if (pFirst == pLast || iFirst == iLast) return null;
        TreeNode root = new TreeNode(p[pLast - 1]);
        int inRootPos = find(i, iFirst, iLast, p[pLast - 1]), leftSize = inRootPos - iFirst;
        root.left = buildTree(i, p, iFirst, inRootPos, pFirst, pFirst + leftSize);
        root.right = buildTree(i, p, inRootPos + 1, iLast, pFirst + leftSize, pLast - 1);
        return root;
    }

    private int find(int[] A, int start, int end, int target) {
        for (int i = start; i < end; i++)
            if (A[i] == target) return i;
        return -1;
    }

}

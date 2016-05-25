/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月14日<p>
//-------------------------------------------------------
public class ConstructBinaryTreefromPreorderandInorderTraversal {

    @Test
    public void test() {
        Assert.assertEquals(TreeNode.generateTree("1,2,n,4,n,n,3,5,n,n,n"),
                buildTree(new int[] { 1, 2, 4, 3, 5 }, new int[] { 2, 4, 1, 5, 3 }));
    }

    /**
     * @param p 先根遍历节点数组
     * @param i 中根遍历节点数组
     * @return 构造树的根节点
     */
    public TreeNode buildTree(int[] p, int[] i) {
        return buildTree(p, i, 0, p.length, 0, i.length);
    }

    /**
     * @param p 先根遍历节点数组
     * @param i 中根遍历节点数组
     * @param pFirst 子树的根节点
     * @param pLast 子树的最右节点
     * @param iFirst 子树的最左节点
     * @param iLast 子树的最右节点
     * @return 构造树的根节点
     */
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

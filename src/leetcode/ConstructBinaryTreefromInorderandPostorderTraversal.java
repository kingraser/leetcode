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
public class ConstructBinaryTreefromInorderandPostorderTraversal {

    @Test
    public void test() {
        Assert.assertEquals(TreeNode.generateTree("1,2,n,4,n,n,3,5,n,n,n"),
                buildTree(new int[] { 2, 4, 1, 5, 3 }, new int[] { 4, 2, 5, 3, 1 }));
    }

    /**
     * @param i 中根遍历节点数组
     * @param p 后根遍历节点数组
     * @return 构造树的根节点
     */
    public TreeNode buildTree(int[] i, int[] p) {//in order post order
        return buildTree(i, p, 0, i.length, 0, p.length);
    }

    /**
     * @param i 中根遍历节点数组
     * @param p 后根遍历节点数组
     * @param iFirst 子树的最左叶子节点
     * @param iLast 子树的最右叶子节点
     * @param pFirst 子树的最左叶子节点
     * @param pLast 子树的根节点
     * @return 构造树的根节点
     */
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

/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

import leetcode.common.TreeNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月14日<p>
//-------------------------------------------------------
public class UniqueBinarySearchTreesII {
    /*
    Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
    
    For example,
    Given n = 3, your program should return all 5 unique BST's shown below.
    
       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3
     */

    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n); // 从1作为root开始，到n作为root结束
    }

    private List<TreeNode> generateTrees(int left, int right) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if (left > right) {
            res.add(null);
            return res;
        }
        for (int i = left; i <= right; i++) {
            List<TreeNode> lefts = generateTrees(left, i - 1); // 以i作为根节点，左子树由[1,i-1]构成
            List<TreeNode> rights = generateTrees(i + 1, right); // 右子树由[i+1, n]构成
            for (int j = 0; j < lefts.size(); j++)
                for (int k = 0; k < rights.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = lefts.get(j);
                    root.right = rights.get(k);
                    res.add(root); // 存储所有可能行
                }
        }
        return res;
    }
}

/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class GenerateParentheses {

    /*
    For example, given n = 3, a solution set is:
    "((()))", "(()())", "(())()", "()(())", "()()()"
    
            二叉树
    */

    @Test
    public void test() {
        List<String> expected = Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()");
        Assert.assertEquals(new HashSet<>(expected), new HashSet<>(generateParenthesis(3)));
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        dfs(list, new char[n << 1], n, n);
        return list;
    }

    /**
     * @param list 结果集
     * @param A 生成中间结果
     * @param left 剩余左括号数
     * @param right 剩余右括号数
     */
    public void dfs(List<String> list, char[] A, int left, int right) {
        if (left == 0 && right == 0 && A.length > 0) list.add(new String(A));
        else {
            if (left > 0) {
                A[A.length - left - right] = '(';
                dfs(list, A, left - 1, right);
            }
            if (left < right) {
                A[A.length - left - right] = ')';
                dfs(list, A, left, right - 1);
            }
        }
    }

}

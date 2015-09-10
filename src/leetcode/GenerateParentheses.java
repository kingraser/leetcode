/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.List;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class GenerateParentheses {

    /*
    For example, given n = 3, a solution set is:
    "((()))", "(()())", "(())()", "()(())", "()()()"
    
    二叉树
     */

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        dfs(list, "", n, n);
        return list;
    }

    /**
     * @param list      结果集
     * @param temp      生成中间结果
     * @param left      剩余左括号数
     * @param right     剩余右括号数
     */
    public void dfs(List<String> list, String temp, int left, int right) {
        if (left == 0 && right == 0 && !temp.equals("")) {
            list.add(temp);
            return;
        }
        if (left > 0) dfs(list, temp + "(", left - 1, right);
        if (left < right) dfs(list, temp + ")", left, right - 1);
    }
}

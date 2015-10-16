/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class PalindromePartitioning {
    /*
    Given a string s, partition s such that every substring of the partition is a palindrome.    
    Return all possible palindrome partitioning of s.    
    For example, given s = "aab",
    Return    
      [
        ["aa","b"],
        ["a","a","b"]
      ]
    */

    /*
    在每一步都可以判断中间结果是否为合法结果,用回溯法。
    一个长度为 n 的字符串,有 n − 1 个地方可以砍断,每个地方可断可不断,因此复杂度为O(2^n−1)
    */

    @SuppressWarnings("unchecked")
    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList(Lists.newArrayList("a", "a", "b"), Lists.newArrayList("aa", "b")),
                partition("aab"));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new LinkedList<>();
        char[] c = s.toCharArray();
        for (int end = s.length() - 1, i = end; i >= 0; i--)
            if (isPalindrome(c, i, s.length() - 1)) {
                List<List<String>> resultTemp = partition(s.substring(0, i));
                if (resultTemp.isEmpty()) resultTemp.add(new ArrayList<>(s.length()));
                for (List<String> list : resultTemp) {
                    list.add(s.substring(i));
                    result.add(list);
                }
            }
        return result;
    }

    private boolean isPalindrome(char[] c, int start, int end) {
        while (start < end)
            if (c[start++] != c[end--]) return false;
        return true;
    }
}

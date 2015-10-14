/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月14日<p>
//-------------------------------------------------------
public class RestoreIPAddresses {
    /*
    Given a string containing only digits, restore it by returning all possible valid IP address combinations.
    
    For example:
    Given "25525511135",
    
    return ["255.255.11.135", "255.255.111.35"]. (Order does not matter) 
    */

    //只有到末尾才知道是否有效,所以深搜

    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList("255.255.11.135", "255.255.111.35"), restoreIpAddresses("25525511135"));
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = Lists.newArrayList();
        dfs(result, s.toCharArray(), Lists.newArrayList(0));
        return result;
    }

    private void dfs(List<String> result, char[] s, List<Integer> list) {
        int idx = list.get(list.size() - 1), len = s.length - idx, todo = 5 - list.size();
        if (len == 0 && todo == 0) {
            result.add(getIp(s, list));
            return;
        }
        if (len > todo * 3 || len < todo) return;
        for (int num = 0, end = idx + Math.min(3, len); idx < end; idx++) {
            num = num * 10 + (s[idx] - '0');
            if (num < 256) {
                list.add(idx + 1);
                dfs(result, s, list);
                list.remove(list.size() - 1);
            }
            if (num == 0) break;
        }
    }

    private String getIp(char[] s, List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size() - 1; sb.append(Arrays.copyOfRange(s, list.get(i), list.get(++i))).append('.'));
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

}

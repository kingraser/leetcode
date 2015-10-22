/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月16日<p>
//-------------------------------------------------------
public class ExpressionAddOperators {

    /*
    Given a string that contains only digits 0-9 and a target value, 
    return all possibilities to add operators +, -, or * between the digits so they evaluate to the target value.
    
    Examples:
    
    "123", 6 -> ["1+2+3", "1*2*3"] 
    "232", 8 -> ["2*3+2", "2+3*2"]
    "00", 0 -> ["0+0", "0-0", "0*0"]
    */

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num.length() == 1) {
            if (num.charAt(0) - '0' == target) return Arrays.asList(num);
            else return result;
        }
        char c = num.charAt(num.length() - 1);
        int n = c - '0';
        result.addAll(add(c, addOperators(num.substring(0, num.length() - 1), target - n), '+'));
        result.addAll(add(c, addOperators(num.substring(0, num.length() - 1), target + n), '-'));
        if (target % n == 0) result.addAll(add(c, addOperators(num.substring(0, num.length() - 1), target / n), '*'));
        return result;
    }

    /**
     * @param c 后续字符
     * @param List 前缀字符
     * @param op 操作符
     * @return
     */
    private List<String> add(char c, List<String> list, char op) {
        for (int i = 0; i < list.size(); list.set(i,
                new StringBuilder(list.get(i)).append(op).append(c).toString()), i++);
        return list;
    }

    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList("1+2+3", "1*2*3"), addOperators("123", 6));
        Assert.assertEquals(Lists.newArrayList("2*3+2", "2+3*2"), addOperators("232", 8));
        Assert.assertEquals(Lists.newArrayList("0+0", "0-0", "0*0"), addOperators("00", 0));
    }

}

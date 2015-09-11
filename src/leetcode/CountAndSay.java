/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class CountAndSay {

    public String countAndSay(int n) {
        String s = "1";
        for (; n-- > 1; s = getNext(s));
        return s;
    }

    private String getNext(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 1; i < s.length(); sb.append(j - i).append(s.charAt(i)), i = j, j++)
            for (; j < s.length() && s.charAt(i) == s.charAt(j); j++);
        return sb.toString();
    }

    @Test
    public void test() {
        String[] expected = new String[] { "1", "11", "21", "1211", "111221", "312211", "13112221", "1113213211",
                "31131211131221", "13211311123113112211" };
        String[] actual = new String[10];
        for (int i = 1; i < 11; actual[i - 1] = countAndSay(i), i++);
        Assert.assertArrayEquals(expected, actual);
    }

}

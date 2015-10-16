/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class ReverseWordsinaString {
    /*
    Given an input string, reverse the string word by word.
    
    For example,
    Given s = "the sky is blue",
    return "blue is sky the". 
    */

    @Test
    public void test() {
        Assert.assertEquals("a", reverseWords("a"));
        Assert.assertEquals("1", reverseWords("1 "));
        Assert.assertEquals("blue is sky the", reverseWords("the sky is blue"));
    }

    public String reverseWords(String s) {
        int rIdx = s.length() - 1, sIdx = 0, size = rIdx;
        char[] result = new char[s.length()], stack = new char[s.length()], array = s.toCharArray();
        for (char c : array)
            if (c == ' ') {
                if (sIdx == 0) continue;
                if (rIdx != size) result[rIdx--] = ' ';
                for (; sIdx > 0; result[rIdx--] = stack[--sIdx]);
                sIdx = 0;
            } else stack[sIdx++] = c;
        if (sIdx != 0) {
            if (rIdx != size) result[rIdx--] = ' ';
            for (; sIdx > 0; result[rIdx--] = stack[--sIdx]);
        }
        return new String(result, ++rIdx, s.length() - rIdx);
    }
}

/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        return sort(s).equals(sort(t));
    }

    private String sort(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    @Test
    public void test() {
        Assert.assertTrue(isAnagram("anagram", "nagaram"));
        Assert.assertFalse(isAnagram("rat", "car"));
    }

}

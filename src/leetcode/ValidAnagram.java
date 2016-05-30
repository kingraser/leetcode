/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        char[] sArray = s.toCharArray(), tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        return Objects.deepEquals(sArray, tArray);
    }

    @Test
    public void test() {
        Assert.assertTrue(isAnagram("anagram", "nagaram"));
        Assert.assertFalse(isAnagram("rat", "car"));
    }

}

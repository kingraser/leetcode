/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class IsomorphicStrings {

    /*
    For example,
    Given "egg", "add", return true.    
    Given "foo", "bar", return false.    
    Given "paper", "title", return true.
    */

    @Test
    public void test() {
        Assert.assertTrue(isIsomorphic("egg", "add"));
        Assert.assertTrue(isIsomorphic("paper", "title"));
        Assert.assertFalse(isIsomorphic("foo", "bar"));
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> sMap = new HashMap<>(), tMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character sc = s.charAt(i), tc = t.charAt(i), tc1 = sMap.get(sc), sc1 = tMap.get(tc);
            if (sc1 == null && tc1 == null) {
                sMap.put(sc, tc);
                tMap.put(tc, sc);
            } else if (sc != sc1 || tc != tc1) return false;
        }
        return true;
    }
}

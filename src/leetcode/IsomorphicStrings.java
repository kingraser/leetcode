/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Map;

import com.google.common.collect.Maps;

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

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> map = Maps.newHashMap();
        for (int i = 0; i < s.length(); i++)
            if (map.containsKey(s.charAt(i))) {
                if (t.charAt(i) != map.get(s.charAt(i))) return false;
            } else if (map.containsValue(t.charAt(i))) return false;
            else map.put(s.charAt(i), t.charAt(i));
        return true;
    }
}

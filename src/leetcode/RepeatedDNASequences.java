/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class RepeatedDNASequences {

    private static Map<Character, Integer> map = new HashMap<Character, Integer>();

    static {
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Map<Integer, Integer> repeat = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length() - 9; i++) {
            int hash = hashCode(i, s);
            if (repeat.containsKey(hash) && repeat.get(hash) == 1) list.add(s.substring(i, i + 10));
            else repeat.put(hash, 1 + (repeat.containsKey(hash) ? repeat.get(hash) : 0));
        }
        return list;
    }

    private int hashCode(int i, String s) {
        int k = 0;
        for (int j = 0; j < 10; j++) {
            k <<= 2;
            k += map.get(s.charAt(i + j));
        }
        return k;
    }
}

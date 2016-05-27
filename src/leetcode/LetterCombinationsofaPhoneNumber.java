/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月9日<p>
//-------------------------------------------------------
public class LetterCombinationsofaPhoneNumber {

    /*
    Given a digit string, return all possible letter combinations that the number could represent.
    
    A mapping of digit to letters (just like on the telephone buttons) is given below.
        2=>abc  3=>def  4=>ghi
        5=>jkl  6=>mno  7=>pqrs
        8=>tuv  9=>wxyz   
    Input:Digit string "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    
    Note:
    Although the above answer is in lexicographical order, your answer could be in any order you want. 
    */

    String[] map = new String[] { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String s) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        LinkedList<String> ans = new LinkedList<>(Arrays.asList(""));
        for (int i = 0; i < s.length(); i++)
            for (String t; (t = ans.peekFirst()).length() == i; t = ans.pollFirst())
                for (char c : map[s.charAt(i) - '2'].toCharArray())
                    ans.add(t + c);
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(Sets.newHashSet("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"),
                new HashSet<>(letterCombinations("23")));
    }
}

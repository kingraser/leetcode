/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
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
    public List<String> letterCombinations(String digits) {
        if (StringUtils.isBlank(digits)) return Lists.newArrayList();
        LinkedList<String> ans = Lists.newLinkedList(Lists.newArrayList(""));
        List<String> map = Lists.newArrayList("abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz");
        for (int i = 0; i < digits.length(); i++)
            for (String t = ans.peek(); ans.peek().length() == i; t = ans.remove(), t = ans.peek())
                for (int j = 0; j < map.get(digits.charAt(i) - '2').length(); ans
                        .add(t + map.get(digits.charAt(i) - '2').charAt(j)), j++);
        return ans;
    }

    @Test
    public void test() {
        Assert.assertEquals(Sets.newTreeSet(Lists.newArrayList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")),
                Sets.newTreeSet(letterCombinations("23")));
    }
}

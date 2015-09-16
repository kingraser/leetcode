/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableMap;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月15日<p>
//-------------------------------------------------------
public class ValidNumber {

    /*
    Validate if a given string is numeric.
    
    Some examples:
    "0" => true
    " 0.1 " => true
    "abc" => false
    "1 a" => false
    "2e10" => true
    
    Note: It is intended for the problem statement to be ambiguous. 
    You should gather all requirements up front before implementing one. 
    
    1正则
    2java作弊
    3DFA
    */

    public boolean isNumber(String s) {
        String regex = "^[\\+-]?((\\d+(\\.\\d*)?)|(\\.\\d+))([Ee][\\+-]?\\d+)?$";
        return s.trim().matches(regex);
    }

    public boolean isNumberII(String s) {
        try {
            if (s.indexOf("f") != -1 || s.indexOf("D") != -1) return false;
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public enum Type {
        SIGN, DIGIT, DOT, EXPONENT, INVALID;
    }

    Map<Predicate<Character>, Type> map = ImmutableMap.of(new Sign(), Type.SIGN, new Digit(), Type.DIGIT, new Dot(),
            Type.DOT, new Exponent(), Type.EXPONENT);

    //0 初始状态
    //1 接收过符号
    //2 接收过数字(结束状态)
    //3 接收过点
    //4 case 2 && 接收过点 || case3 && 接收过数字(结束状态)
    //5 接收过e|E
    //6 case 5 && 接收过符号
    //7 case 5 && 接收过数字(结束状态)
    int[][] transTable = new int[][] { { 1, 2, 3, -1 }, { -1, 2, 3, -1 }, { -1, 2, 4, 5 }, { -1, 4, -1, -1 },
            { -1, 4, -1, 5 }, { 6, 7, -1, -1 }, { -1, 7, -1, -1 }, { -1, 7, -1, -1 } };

    public boolean isNumberIII(String s) {
        s = s.trim();
        int state = 0;
        for (int i = 0; state != -1 && i < s.length(); i++) {
            Type type = getType(s.charAt(i));
            if (type == Type.INVALID) return false;
            state = transTable[state][type.ordinal()];
        }
        return state == 2 || state == 4 || state == 7;
    }

    private Type getType(char c) {
        for (Predicate<Character> predicate : map.keySet())
            if (predicate.apply(c)) return map.get(predicate);
        return Type.INVALID;
    }

    public class Sign implements Predicate<Character> {

        @Override
        public boolean apply(Character input) {
            return input == '+' || input == '-';
        }
    }

    public class Digit implements Predicate<Character> {

        @Override
        public boolean apply(Character input) {
            return Character.isDigit(input);
        }
    }

    public class Dot implements Predicate<Character> {

        @Override
        public boolean apply(Character input) {
            return '.' == input;
        }
    }

    public class Exponent implements Predicate<Character> {

        @Override
        public boolean apply(Character input) {
            return 'e' == input || 'E' == input;
        }
    }

    @Test
    public void test() {
        Assert.assertTrue(isNumber("0"));
        Assert.assertTrue(isNumber(" 0.1 "));
        Assert.assertTrue(isNumber("+2.e10"));
        Assert.assertTrue(isNumber("+.2e10"));
        Assert.assertFalse(isNumber("abc"));
        Assert.assertFalse(isNumber("1 a"));
    }

}

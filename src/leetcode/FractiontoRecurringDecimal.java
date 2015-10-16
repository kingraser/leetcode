/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class FractiontoRecurringDecimal {
    /*
    Given two integers representing the numerator and denominator of a fraction, 
    return the fraction in string format.    
    If the fractional part is repeating, enclose the repeating part in parentheses.    
    For example,    
    Given numerator = 1, denominator = 2, return "0.5".
    Given numerator = 2, denominator = 1, return "2".
    Given numerator = 2, denominator = 3, return "0.(6)".
    */

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        StringBuilder res = new StringBuilder(((numerator > 0) ^ (denominator > 0)) ? "-" : "");// "+" or "-"
        long num = Math.abs((long) numerator), den = Math.abs((long) denominator);
        res.append(num / den); // integral part
        num %= den;
        if (num == 0) return res.toString();
        res.append(".");// fractional part
        java.util.HashMap<Long, Integer> map = new java.util.HashMap<Long, Integer>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            } else map.put(num, res.length());
        }
        return res.toString();
    }

}

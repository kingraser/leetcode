/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

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
    */

    public boolean isNumber(String s) {
        return s.trim().matches("^[\\+-]?((\\d+(\\.\\d*)?)|(\\.\\d+))([Ee][\\+-]?\\d+)?$");
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

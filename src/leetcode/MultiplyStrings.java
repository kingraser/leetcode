/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.math.BigDecimal;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月10日<p>
//-------------------------------------------------------
public class MultiplyStrings {

    /*
    Given two numbers represented as strings, return multiplication of the numbers as a string.    
    Note: The numbers can be arbitrarily large and are non-negative.
    */

    public String multiply(String num1, String num2) {
        return new BigDecimal(num1).multiply(new BigDecimal(num2)).toString();
    }
}

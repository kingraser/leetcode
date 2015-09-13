/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class AddBinary {

    /*
    For example,
    a = "11"
    b = "1"
    Return "100".
    */

    public String addBinary(String a, String b) {
        return new java.math.BigInteger(a, 2).add(new java.math.BigInteger(b, 2)).toString(2);
    }

    public String addBinaryII(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = a.length() - 1, j = b.length() - 1, ia, ib, c = 0, r = 0; i >= 0 || j >= 0 || c == 1;) {
            ia = (i >= 0) ? a.charAt(i--) - '0' : 0;
            ib = (j >= 0) ? b.charAt(j--) - '0' : 0;
            r = ia ^ ib ^ c;
            c = ((ia + ib + c) > 1) ? 1 : 0;
            result.append(r);
        }
        return result.reverse().toString();
    }
}

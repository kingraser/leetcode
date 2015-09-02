/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月1日<p>
//-------------------------------------------------------
public class AddDigits {

    public int addDigits(int num) {
        if (num == 0) return 0;
        int result = num % 9;
        if (result == 0) return 9;
        return result;
    }

}

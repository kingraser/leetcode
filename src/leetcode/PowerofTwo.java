package leetcode;
/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class PowerofTwo {

    /*
    Given an integer, write a function to determine if it is a power of two
    */

    public boolean isPowerOfTwo(int n) {
        return (n <= 0) ? false : (n & (n - 1)) == 0;
    }

}

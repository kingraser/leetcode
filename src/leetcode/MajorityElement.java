/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class MajorityElement {
    /*    
    Given an array of size n, find the majority element. 
    The majority element is the element that appears more than n/2 times.
    */

    public int majorityElement(int[] num) {
        int a = num[0];
        for (int i = 1, sum = 1; sum <= num.length >> 1 && i < num.length; i++)
            if (num[i] != a) {
                sum--;
                if (sum == 0) {
                    a = num[++i];
                    sum++;
                }
            } else sum++;
        return a;
    }
}

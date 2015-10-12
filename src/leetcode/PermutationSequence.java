/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class PermutationSequence {
    /*
    The set [1,2,3,…,n] contains a total of n! unique permutations.
    
    By listing and labeling all of the permutations in order,
    We get the following sequence (ie, for n = 3):
    
    "123"
    "132"
    "213"
    "231"
    "312"
    "321"
    
    Given n and k, return the kth permutation sequence.
    
    Note: Given n will be between 1 and 9 inclusive.
    */

    /*
    简单的,可以用暴力枚举法,调用k−1次next_permutation()。
    暴力枚举法把前k个排列都求出来了,比较浪费,而我们只需要第k个排列。
    利用康托编码的思路,假设有n个不重复的元素,第k个排列是a1,a2,a3,...,an,
    那么a1是哪一个位置呢?
    我们把a1去掉,那么剩下的排列为a2,a3,...,an, 共计n−1个元素,
    n−1个元素共有(n−1)!个排列,于是就可以知道 a1=k/(n − 1)!。
    同理,a2,a3,...,an 的值推导如下:
    k2=k%(n−1)!
    a2=k2/(n−2)!
    ···
    kn−1=kn−2%2!
    an−1=kn−1/1!
    an=0
    */

    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder(), ret = new StringBuilder();
        for (int i = 1; i <= n; sb.append(i++));
        int[] factor = new int[n];
        factor[0] = 1;
        for (int i = 1; i < n; factor[i] = factor[i - 1] * i, i++);
        k--;
        for (int i = n - 1; i > -1; i--) {
            int code = k / factor[i];
            k = k % factor[i];
            ret.append(sb.charAt(code));
            sb.deleteCharAt(code);
        }

        return ret.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("321", getPermutation(3, 6));
    }
}

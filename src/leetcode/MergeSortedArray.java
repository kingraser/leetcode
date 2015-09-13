/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月13日<p>
//-------------------------------------------------------
public class MergeSortedArray {

    public void merge(int A[], int m, int B[], int n) {
        int a = m - 1, b = n - 1, c = m + n - 1;
        for (; a >= 0 && b >= 0; A[c--] = A[a] >= B[b] ? A[a--] : B[b--]);
        for (; b >= 0; A[c--] = B[b--]);
    }
}

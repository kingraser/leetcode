package leetcode;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class PascalsTriangleII {
    /*
    Given an index k, return the kth row of the Pascal's triangle.
    
    For example, given k = 3,
    Return [1,3,3,1]. 
    */

    public List<Integer> getRow(int k) {
        Integer[] arr = new Integer[k + 1];
        Arrays.fill(arr, 1, arr.length, 0);;
        arr[0] = 1;
        for (int i = 1; i <= k; i++)
            for (int j = i; j > 0; j--)
                arr[j] += arr[j - 1];
        return Arrays.asList(arr);
    }

    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList(1, 3, 3, 1), getRow(3));
    }
}

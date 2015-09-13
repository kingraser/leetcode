package leetcode;

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
        List<Integer> res = Lists.newArrayList(1);
        for (int j = 1; j <= k; j++)
            res.add((int) ((double) res.get(res.size() - 1) * (k - j + 1) / j));
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList(1, 3, 3, 1), getRow(3));
    }
}

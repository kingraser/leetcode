/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class PascalsTriangle {
    /*
    Given numRows, generate the first numRows of Pascal’s triangle.
    For example, given numRows = 5,
    Return
    [
            [1],
           [1,1],
          [1,2,1],
         [1,3,3,1],
        [1,4,6,4,1]
    ]
    */

    List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>(numRows);
        for (int i = 0, j; i < numRows; i++)
            for (res.add(new ArrayList<>(i + 1)), j = 0; j < i + 1; j++)
                res.get(i).add(j == 0 || j == i ? 1 : res.get(i - 1).get(j) + res.get(i - 1).get(j - 1));
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(Arrays.asList(1), Arrays.asList(1, 1), Arrays.asList(1, 2, 1),
                Arrays.asList(1, 3, 3, 1), Arrays.asList(1, 4, 6, 4, 1)), generate(5));
    }
}

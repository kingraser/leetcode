/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.List;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class Triangle {

    /*
    Given a triangle, find the minimum path sum from top to bottom. 
    Each step you may move to adjacent numbers on the row below.
    
    For example, given the following triangle
    
    [
         [2],
        [3,4],
       [6,5,7],
      [4,1,8,3]
    ]
    
    The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11). 
    */

    public int minimumTotal(List<List<Integer>> triangle) {
        for (int i = triangle.size() - 2; i > -1; i--)
            for (int j = 0; j < triangle.get(i).size(); j++)
                triangle.get(i).set(j,
                        triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
        return triangle.get(0).get(0);
    }

}

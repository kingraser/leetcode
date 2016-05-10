/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月10日<p>
//-------------------------------------------------------
public class Candy {
    /*
    There are N children standing in a line. Each child is assigned a rating value.
    
    You are giving candies to these children subjected to the following requirements:
    
    Each child must have at least one candy.
    Children with a higher rating get more candies than their neighbors.
    
    What is the minimum candies you must give? 
    */

    @Test
    public void test() {
        Assert.assertEquals(9, candy(new int[] { 1, 3, 5, 2, 4 }));
    }

    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        Arrays.fill(candy, 1);
        for (int i = 1; i < ratings.length; i++)
            if (ratings[i] > ratings[i - 1]) candy[i] = candy[i - 1] + 1;
        for (int i = ratings.length - 2; i > -1; i--)
            if (ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]) candy[i] = candy[i + 1] + 1;
        return sum(candy);
    }

    private int sum(int[] candy) {
        int sum = 0;
        for (int i = 0; i < candy.length; sum += candy[i++]);
        return sum;
    }

}

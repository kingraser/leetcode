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
// <p>@author wit Initial Created at 2015年9月10日<p>
//-------------------------------------------------------
public class ThreeSumClosest {

    /*
    16 3Sum Closest
    给一int数组及target,求三下标使和最接近target.有且只有一组解
    map法不适用了,转为2sum+水位法
     */

    @SuppressWarnings("unused")
    public int threeSumClosest(int[] nums, int target) {
        int result = 0, min = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++)
            for (int j = i + 1, k = nums.length - 1, s = nums[i] + nums[j] + nums[k], d = Math
                    .abs(s - target), x; j < k; result = d < min ? s : result, min = Math.min(min, d), x = s < target
                            ? j++ : k--, s = nums[i] + nums[j] + nums[k], d = Math.abs(s - target))
                if (d == 0) return target;
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, threeSumClosest(new int[] { -1, 2, 1, -4 }, 1));
    }

}

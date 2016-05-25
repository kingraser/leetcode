/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月9日<p>
//-------------------------------------------------------
public class ContainerWithMostWater {
    /*
            给定 int[] 求两个板能存住的最大水量
            思路:两个指针头,尾.移动矮的一方至比起始移动时高或不能移动为止.水位法保存水量
    */

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, max = Integer.MIN_VALUE, lastL = max, lastR = max;
        while (l < r)
            if (height[l] < lastL) l++;
            else if (height[r] < lastR) r--;
            else {
                max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
                lastL = height[l];
                lastR = height[r];
                if (lastL < lastR) l++;
                else r--;
            }
        return max;
    }

}

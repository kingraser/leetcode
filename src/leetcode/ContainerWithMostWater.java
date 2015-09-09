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
        int i = 0, j = height.length - 1, min = Math.min(height[i], height[j]), max = min * (j - i);
        for (; i < j; min = Math.min(height[i], height[j]), max = Math.max(max, min * (j - i)))
            if (height[i] < height[j]) do
                i++;
            while (i < j && height[i] <= min);
            else do
                j--;
            while (i < j && height[j] <= min);
        return max;
    }

}

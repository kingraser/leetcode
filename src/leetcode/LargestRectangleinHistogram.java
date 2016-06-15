/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月13日<p>
//-------------------------------------------------------
public class LargestRectangleinHistogram {
    /*
    Given n non-negative integers representing the histogram's bar height 
    where the width of each bar is 1, find the area of largest rectangle in the histogram.    
    
    
              6
            5 _
            _| |
           | | |  3
        2  | | |2 _
        _ 1| | |_| |
       | |_| | | | |
       | | | | | | |
    Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
              6
            5 _
            _| |
           |@|@|  3
        2  |@|@|2 _
        _ 1|@|@|_| |
       | |_|@|@| | |
       | | |@|@| | |
    The largest rectangle is shown in the shaded area, which has area = 10 unit.
    
    For example,
    Given height = [2,1,5,6,2,3],
    return 10. 
    */

    /*
            简单的,类似于Container With Most Water,对每个柱子,左右扩展,直到碰到比自己矮的,
            计算这个矩形的面积,用一个变量记录最大的面积,复杂度 O(n^2),会超时。
            如图所示,从左到右处理直方,当i=4时,小于当前栈顶(即直方3),对于直方3,
            无论后面还是前面的直方,都不可能得到比目前栈顶元素更高的高度了,
            处理掉直方3(计算从直方3到直方4之间的矩形的面积,然后从栈里弹出)
            对于直方2也是如此;直到碰到比直方4更矮的直方1。
            这就意味着,可以维护一个递增的栈,每次比较栈顶与当前元素。
            如果当前元素大于栈顶元素,则入栈,否则合并现有栈,直至栈顶元素小于当前元素。
            结尾时入栈元素0,重复合并一次。
    */

    public int largestRectangleArea(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0, v = 0; i < height.length + 1; v = (i < height.length ? height[i] : 0))
            if (stack.empty() || height[stack.peek()] <= v) stack.push(i++);
            else while (!stack.isEmpty() && height[stack.peek()] > v)
                max = Math.max(max, height[stack.pop()] * (stack.empty() ? i : i - stack.peek() - 1));
        return max;
    }

    @Test
    public void test() {
        Assert.assertEquals(10, largestRectangleArea(new int[] { 2, 1, 5, 6, 2, 3 }));
    }
}

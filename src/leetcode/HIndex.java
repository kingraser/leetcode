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
// <p>@author wit Initial Created at 2015年9月6日<p>
//-------------------------------------------------------
public class HIndex {
    /*
    给定一个非负整数数组,每个元素代表一篇论文的引用数。
    计算该数组的引用因子
    所谓引用因子x即有x篇文章的引用数大于等于x，其余文章引用数小于x
    
    eg{3, 0, 6, 1, 5}的引用因子是3
    因为3,5,6，有3篇文章的引用数至少为3，其余0,1 引用数小于3 
    
    解法
    
    1O(n*log(n))
        1排序
        2顺序查找（优化：二分查找）
     
     2O(n)
         1one pass获取各引用数下的文章数
         2one pass查找
     */

    //1 O(n*log(n))
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        for (int i = 0; i < citations.length; i++)
            if (citations[i] >= citations.length - i) return citations.length - i;
        return 0;
    }

    //1 O(n*log(n)) optimization
    public int hIndexI(int[] citations) {
        Arrays.sort(citations);
        int l = 0, r = citations.length - 1;
        for (int m = (l + r) / 2; l <= r; m = (l + r) / 2)
            if (citations[m] < citations.length - m) l = m + 1;
            else r = m - 1;
        return citations.length - l;
    }

    //2 O(n)
    public int hIndexII(int[] citations) {
        int[] array = new int[citations.length + 1];
        for (int i : citations)
            if (i > citations.length) array[citations.length] += 1;
            else array[i] += 1;
        for (int i = citations.length, t = array[i]; i >= 0; i--, t += array[i])
            if (t >= i) return i;
        return 0;
    }

    @Test
    public void test() {
        Assert.assertEquals(3, hIndex(new int[] { 3, 0, 6, 1, 5 }));
        Assert.assertEquals(1, hIndex(new int[] { 100 }));
    }
}

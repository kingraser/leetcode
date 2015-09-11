/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月11日<p>
//-------------------------------------------------------
public class NextPermutation {

    /*
    1,2,3 → 1,3,2
    3,2,1 → 1,2,3
    1,1,5 → 1,5,1
    
    考虑排列1237456，它的下一个排列是1243567，
    找需要重排的子集的方法其实就是：从尾到头遍历，找到升序的拐点
    num[i~size-1] 其满足一个条件：前两个元素递增，后面都是递减或者后面已经没有元素。
    特殊情况是：如果找不到这样的存在递增关系的 num[i] 和 num[i+1]，说明整个序列都是降序，
    也就是没有更大的排列了，根据题目要求，直接将序列逆序即可。
    重新排列的方式就是从num[i+1 ~ size-1]中选一个比num[i] 大的最小元素，将其和num[i] 交换
    然后将num[i+1 ~ size-1]逆序。
    */

    public void nextPermutation(int[] num) {
        int i = num.length - 1, j = num.length - 1;
        for (int max = Integer.MIN_VALUE; i > -1; i--)
            if (num[i] >= max) max = num[i];
            else break;
        if (i != -1) {
            for (; j > -1 && num[j] <= num[i]; j--);
            if (j != -1) swap(i, j, num);
        }
        reverse(num, i + 1, num.length - 1);
    }

    private void reverse(int[] num, int i, int j) {
        for (; i < j; swap(i, j, num), i++, j--);
    }

    private void swap(int i, int j, int[] num) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
}

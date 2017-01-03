/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class SearchinRotatedSortedArrayII {

  /*
  Follow up for ”Search in Rotated Sorted Array”: What if duplicates are allowed?
  Would this affect the run-time complexity? How and why?
  Write a function to determine if a given target is in the array.
  */

  /*
  分析
  允许重复元素,则上一题中如果 A[m]>=A[l], 那么 [l,m] 为递增序列的假设就不能成立了,
  比如 [1,3,1,1,1]。
  如果 A[m]>=A[l] 不能确定递增,那就把它拆分成两个条件:
      • 若 A[m]>A[l],则区间 [l,m] 一定递增
      • 若 A[m]==A[l] 确定不了,那就 l++,往下看一步即可。
  算法复杂性O(n)所以直接匹配也能过.
  */

  public boolean search(int[] A, int target) {
    for (int i = 0; i < A.length; i++)
      if (A[i] == target) return true;
    return false;
  }

  public boolean searchII(int A[], int target) {
    int first = 0, last = A.length;
    while (first != last) {
      int mid = first + (last - first) / 2;
      if (A[mid] == target) return true;
      if (A[first] < A[mid]) {
        if (A[first] <= target && target < A[mid]) last = mid;
        else first = mid + 1;
      } else if (A[first] > A[mid]) {
        if (A[mid] < target && target <= A[last - 1]) first = mid + 1;
        else last = mid;//skip duplicate one
      } else first++;
    }
    return false;
  }
}

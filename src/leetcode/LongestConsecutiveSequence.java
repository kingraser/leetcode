package leetcode;
/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */

import java.util.Set;

import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class LongestConsecutiveSequence {

    /*
    Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
    
    For example,
    Given [100, 4, 200, 1, 3, 2],
    The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
    
    Your algorithm should run in O(n) complexity. 
    */

    /*
    分析
    如果允许 O(n log n) 的复杂度,那么可以先排序,可是本题要求 O(n)。
    由于序列里的元素是无序的,又要求 O(n),首先要想到用哈希表。
    用一个哈希表 unordered_map<int, bool> used 记录每个元素是否使用,
    对每个元素,以该元素为中心,往左右扩张,直到不连续为止,记录下最长的长度。
    */

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = Sets.newHashSet();
        for (int e : nums)
            set.add(e);
        int max = 1;
        for (int e : nums) {
            int count = 1;
            for (int left = e - 1; set.contains(left); count++, set.remove(left--));
            for (int right = e + 1; set.contains(right); count++, set.remove(right++));
            max = Math.max(count, max);
        }
        return max;
    }

}

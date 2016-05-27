/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import leetcode.common.Interval;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class InsertInterval {
    /*
    Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
    
    You may assume that the intervals were initially sorted according to their start times.
    
    Example 1:
    Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
    
    Example 2:
    Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
    
    This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10]. 
    */

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        for (ListIterator<Interval> it = intervals.listIterator(); it.hasNext();) {
            Interval interval = it.next();
            if (newInterval.end < interval.start) {
                it.previous();
                it.add(newInterval);
                return intervals;
            } else if (interval.end < newInterval.start) continue;
            else {
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
                it.remove();
            }
        }
        intervals.add(newInterval);
        return intervals;
    }

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(new Interval(1, 5), new Interval(6, 9)),
                insert(Lists.newArrayList(new Interval(1, 3), new Interval(6, 9)), new Interval(2, 5)));
        Assert.assertEquals(Arrays.asList(new Interval(1, 2), new Interval(3, 10), new Interval(12, 16)),
                insert(Lists.newArrayList(new Interval(1, 2), new Interval(3, 5), new Interval(6, 7),
                        new Interval(8, 10), new Interval(12, 16)), new Interval(4, 9)));
    }
}

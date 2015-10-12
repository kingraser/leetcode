/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import leetcode.common.Interval;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class MergeIntervals {
    /*
    Given a collection of intervals, merge all overlapping intervals.
    
    For example,
    Given [1,3],[2,6],[8,10],[15,18],
    return [1,6],[8,10],[15,18]. 
    */

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) return intervals;
        Collections.sort(intervals, new Comparator<Interval>() {

            @Override
            public int compare(Interval interval1, Interval interval2) {
                return interval1.start - interval2.start;
            }
        });

        // merge
        List<Interval> result = Lists.newArrayList();
        Iterator<Interval> iterator = intervals.iterator();
        Interval mover = iterator.next();
        while (iterator.hasNext()) {
            Interval current = iterator.next();
            if (mover.end < current.start) {
                result.add(mover);
                mover = current;
            } else mover.end = Math.max(mover.end, current.end);
        }
        result.add(mover);
        return result;
    }

    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList(new Interval(1, 6), new Interval(8, 10), new Interval(15, 18)), merge(
                Lists.newArrayList(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18))));
    }

}

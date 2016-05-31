/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

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
        List<Interval> r = new ArrayList<>(intervals.size());
        intervals.stream().sorted((i1, i2) -> i1.start - i2.start).forEach(i -> {
            if (r.isEmpty() || i.start > r.get(r.size() - 1).end) r.add(i);
            else if (i.end > r.get(r.size() - 1).end) r.get(r.size() - 1).end = i.end;
        });
        return r;
    }

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(new Interval(1, 6), new Interval(8, 10), new Interval(15, 18)), merge(
                Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18))));
    }

}

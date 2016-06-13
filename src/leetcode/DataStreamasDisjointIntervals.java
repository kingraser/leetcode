/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

import leetcode.common.Interval;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年6月1日;
//-------------------------------------------------------
public class DataStreamasDisjointIntervals {

    @Test
    public void test() {
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);
        Assert.assertEquals(Arrays.asList(new Interval(1, 1)), summaryRanges.getIntervals());
        summaryRanges.addNum(3);
        Assert.assertEquals(Arrays.asList(new Interval(1, 1), new Interval(3, 3)), summaryRanges.getIntervals());
        summaryRanges.addNum(7);
        Assert.assertEquals(Arrays.asList(new Interval(1, 1), new Interval(3, 3), new Interval(7, 7)),
                summaryRanges.getIntervals());
        summaryRanges.addNum(2);
        Assert.assertEquals(Arrays.asList(new Interval(1, 3), new Interval(7, 7)), summaryRanges.getIntervals());
        summaryRanges.addNum(6);
        Assert.assertEquals(Arrays.asList(new Interval(1, 3), new Interval(6, 7)), summaryRanges.getIntervals());
    }

    public class SummaryRanges {

        TreeMap<Integer, Interval> map = new TreeMap<>();

        public void addNum(int val) {
            if (map.containsKey(val)) return;
            Entry<Integer, Interval> l = map.lowerEntry(val), h = map.higherEntry(val);
            if (l != null && h != null && l.getValue().end + 1 == val && h.getKey() == val + 1) {
                l.getValue().end = h.getValue().end;
                map.remove(h.getKey());
            } else if (l != null && l.getValue().end + 1 >= val) l.getValue().end = Math.max(l.getValue().end, val);
            else if (h != null && h.getKey() == val + 1) {
                h.getValue().start = val;
                map.put(val, h.getValue());
                map.remove(h.getKey());
            } else map.put(val, new Interval(val, val));
        }

        public List<Interval> getIntervals() {
            return new ArrayList<>(map.values());
        }
    }
}

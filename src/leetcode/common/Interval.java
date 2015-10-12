/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class Interval {

    public int start, end;

    public Interval() {
        start = 0;
        end = 0;
    }

    public Interval(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Interval.class).add("start", start).add("end", end).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Interval)) return false;
        Interval another = (Interval) o;
        return Objects.equal(start, another.start) && Objects.equal(end, another.end);
    }

}

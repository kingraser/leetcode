/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class Interval {

    public Integer start, end;

    public Interval() {
    }

    public Interval(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (null == o || !(o instanceof Interval)) return false;
        Interval another = (Interval) o;
        return start == another.start && end == another.end;
    }

}

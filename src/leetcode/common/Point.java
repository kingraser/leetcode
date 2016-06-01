/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月16日<p>
//-------------------------------------------------------
public class Point {

    public int x = 0, y = 0;

    public Point() {
    }

    public Point(int a, int b) {
        x = a;
        y = b;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

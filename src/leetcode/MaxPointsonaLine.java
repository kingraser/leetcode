/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import leetcode.common.Point;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月16日<p>
//-------------------------------------------------------
public class MaxPointsonaLine {

    /*
    Given n points on a 2D plane, 
    find the maximum number of points that lie on the same straight line.
    */

    static Integer s, m, v;//相同点数same,当前循环最多点数max,临时变量val

    public int maxPoints(Point[] p) {
        int l = p.length, r = Math.min(1, l), i = 0;//r为结果result
        Map<Double, Integer> k = new HashMap<>(l);
        for (s = 1, m = 0; i + r < l; r = Math.max(r, m + s), k.clear(), i++, s = 1, m = 0)
            for (int j = i + 1; j < l && l - j + m >= r; getK(p[i], p[j++], k));
        return r;
    }

    private void getK(Point p1, Point p2, Map<Double, Integer> map) {
        double x = p1.x - p2.x, y = p1.y - p2.y, k = x == 0 ? Double.NaN : y == 0 ? 0 : y / x;
        if (x == 0 && y == 0) s++;
        else {
            v = map.get(k);
            v = v == null ? 1 : ++v;
            map.put(k, v);
            if (v > m) m = v;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(2, maxPoints(new Point[] { new Point(), new Point() }));
        Assert.assertEquals(2, maxPoints(new Point[] { new Point(), new Point(0, 1) }));
        Assert.assertEquals(3,
                maxPoints(new Point[] { new Point(-4, 1), new Point(-7, 7), new Point(-1, 5), new Point(9, -25) }));
        Assert.assertEquals(1, maxPoints(new Point[] { new Point() }));
    }

}

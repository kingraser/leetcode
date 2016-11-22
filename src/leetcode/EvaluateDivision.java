/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import leetcode.common.Pair;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月12日;
//-------------------------------------------------------
public class EvaluateDivision {

  @Test
  public void test() {
    String[][] equations = { { "a", "b" }, { "b", "c" } },
        queries = { { "a", "c" }, { "b", "a" }, { "a", "e" }, { "a", "a" }, { "x", "x" } };
    double[] values = { 2.0, 3.0 };
    Assert.assertArrayEquals(new double[] { 6.0, 0.5, -1.0, 1.0, -1.0 }, calcEquation(equations, values, queries),
        Double.MIN_NORMAL);
  }

  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    Map<String, Pair<Integer, Double>> map = new HashMap<>();
    Map<Integer, List<String>> bases = new HashMap<>();
    for (int i = 0; i < values.length; i++) {
      String aString = equations[i][0], bString = equations[i][1];
      Pair<Integer, Double> a = map.get(aString), b = map.get(bString);
      if (Objects.isNull(a) && Objects.isNull(b)) {
        map.put(aString, new Pair<>(bases.size(), values[i]));
        map.put(bString, new Pair<>(bases.size(), 1d));
        bases.put(bases.size(), Lists.newArrayList(aString, bString));
      } else if (Objects.nonNull(a) && Objects.nonNull(b)) {
        if (Objects.equals(a.key, b.key)) continue;
        double val = values[i] * b.value;
        bases.get(a.key).stream().map(name -> map.get(name)).forEach(pair -> {
          pair.value *= val;
          pair.key = b.key;
        });
        bases.get(b.key).addAll(bases.remove(a.key));
      } else if (Objects.isNull(a)) {
        map.put(aString, new Pair<>(b.key, b.value * values[i]));
        bases.get(b.key).add(aString);
      } else {
        map.put(bString, new Pair<>(a.key, a.value / values[i]));
        bases.get(a.key).add(bString);
      }
    }
    return getQueryResults(queries, map);
  }

  private double[] getQueryResults(String[][] queries, Map<String, Pair<Integer, Double>> map) {
    List<Double> list = new ArrayList<>(queries.length);
    Pair<Integer, Double> a, b;
    for (String[] q : queries)
      list.add(Objects.isNull(a = map.get(q[0])) || Objects.isNull(b = map.get(q[1])) || a.key != b.key ? -1d
          : a.value / b.value);
    return list.stream().mapToDouble(d -> d.doubleValue()).toArray();
  }

}

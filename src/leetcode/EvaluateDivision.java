/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.Test;

import com.google.common.collect.Lists;

import leetcode.util.Pair;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月12日;
//-------------------------------------------------------
public class EvaluateDivision {
  /*
  Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
  
  Example:
  Given a / b = 2.0, b / c = 3.0.
  queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
  return [6.0, 0.5, -1.0, 1.0, -1.0 ].
  
  The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
  
  According to the example above:
  
  equations = [ ["a", "b"], ["b", "c"] ],
  values = [2.0, 3.0],
  queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
  
  The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction. 
  */

  @Test
  public void test() {
    String[][] equations = { { "a", "b" }, { "b", "c" } },
        queries = { { "a", "c" }, { "b", "a" }, { "a", "e" }, { "a", "a" }, { "x", "x" } };
    double[] values = { 2.0, 3.0 };
    assertArrayEquals(new double[] { 6.0, 0.5, -1.0, 1.0, -1.0 }, calcEquation(equations, values, queries),
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
    return Arrays.stream(queries).mapToDouble(q -> {
      Pair<Integer, Double> a, b;
      if (Objects.isNull(a = map.get(q[0])) || Objects.isNull(b = map.get(q[1])) || a.key != b.key) return -1d;
      return a.value / b.value;
    }).toArray();
  }

}

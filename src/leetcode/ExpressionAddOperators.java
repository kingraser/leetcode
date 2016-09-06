/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月16日<p>
//-------------------------------------------------------
public class ExpressionAddOperators {

  /*
  Given a string that contains only digits 0-9 and a target value, 
  return all possibilities to add operators +, -, or * between the digits so they evaluate to the target value.
  
  Examples:
  
  "123", 6 -> ["1+2+3", "1*2*3"] 
  "232", 8 -> ["2*3+2", "2+3*2"]
  "00", 0 -> ["0+0", "0-0", "0*0"]
  "105", 5 -> ["1*0+5", "10-5"]
  "3456237490", 9191 -> []
  */

  public List<String> addOperators(String num, int target) {
    List<String> res = new ArrayList<>();
    dfs(num, target, 0, 0, "", res);
    return res;
  }

  private void dfs(String num, int target, long diff, long curNum, String out, List<String> res) {
    if (num.length() == 0 && curNum == target) res.add(out);
    for (int i = 1; i <= num.length(); ++i) {
      String cur = num.substring(0, i);
      long curVal = Long.parseLong(cur);
      if (cur.length() > 1 && cur.charAt(0) == '0') return;
      String next = num.substring(i);
      if (out.length() > 0) {
        dfs(next, target, curVal, curNum + curVal, out + "+" + cur, res);
        dfs(next, target, -curVal, curNum - curVal, out + "-" + cur, res);
        dfs(next, target, diff * curVal, (curNum - diff) + diff * curVal, out + "*" + cur, res);
      } else dfs(next, target, curVal, curVal, cur, res);
    }
  }

  @Test
  public void test() {
    Assert.assertEquals(Sets.newHashSet("1*0+5", "10-5"), new HashSet<>(addOperators("105", 5)));
    Assert.assertEquals(Sets.newHashSet("1+2+3", "1*2*3"), new HashSet<>(addOperators("123", 6)));
    Assert.assertEquals(Sets.newHashSet("2*3+2", "2+3*2"), new HashSet<>(addOperators("232", 8)));
    Assert.assertEquals(Sets.newHashSet("0+0", "0-0", "0*0"), new HashSet<>(addOperators("00", 0)));
  }

}

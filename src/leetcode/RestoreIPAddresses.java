/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月14日<p>
//-------------------------------------------------------
public class RestoreIPAddresses {
  /*
  Given a string containing only digits, restore it by returning all possible valid IP address combinations.
  
  For example:
  Given "25525511135",
  
  return ["255.255.11.135", "255.255.111.35"]. (Order does not matter) 
  */

  //只有到末尾才知道是否有效,所以深搜

  @Test
  public void test() {
    assertEquals(Arrays.asList("255.255.11.135", "255.255.111.35"), restoreIpAddresses("25525511135"));
  }

  public List<String> restoreIpAddresses(String s) {
    List<String> result = new ArrayList<>();
    dfs(result, s.toCharArray(), new char[s.length() + 4], 0, 4);//s.length + 4 for 4 possible '.'
    return result;
  }

  /**
   * @param result
   *          结果集
   * @param s
   *          字串
   * @param l
   *          带'.'的字串
   * @param i
   *          s的下标
   * @param todo
   *          还剩几部分要处理
   */
  private void dfs(List<String> result, char[] s, char[] l, int i, int todo) {
    if (s.length - i > todo * 3 || s.length - i < todo) return;//不可能满足条件
    if (i == s.length && todo == 0) result.add(new String(l, 0, i + 3));//保存结果 3 for 3 '.'
    else for (int n = 0, end = Math.min(i + 3, s.length); i < end; i++) {
      n = n * 10 + (s[i] - '0');
      if (n < 256) {
        l[i + 4 - todo] = s[i];//4-todo for num of '.' already added
        l[i + 5 - todo] = '.';//add '.'
        dfs(result, s, l, i + 1, todo - 1);
      }
      if (n == 0) break;//format like 05 is illegal
    }
  }

}

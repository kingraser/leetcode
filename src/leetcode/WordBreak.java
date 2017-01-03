/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class WordBreak {
  /*
  Given a string s and a dictionary of words dict, 
  determine if s can be segmented into a space-separated sequence of one or more dictionary words.
  
  For example, given
  s = "leetcode",
  dictionary = ["leet", "code"].
  
  Return true because "leetcode" can be segmented as "leet code". 
  */

  /*
          设状态为cuts(i),表示 s[0,i)是否可以分词,则状态转移方程为
  cuts(i) = any_of(cuts(j) && dictionary.contains(s[j, i))), 0 ≤ j < i
  */

  @Test
  public void test() {
    Set<String> dict = Sets.newHashSet("leet", "code");
    assertTrue(wordBreak("leetcode", dict));
  }

  public boolean wordBreak(String s, Set<String> dict) {
    boolean[] cuts = new boolean[s.length() + 1];//长度为n的字符串有n+1个隔板
    cuts[0] = true;//空字符串
    for (int i = 1; i <= s.length(); i++)
      for (int j = i - 1; !cuts[i] && j >= 0; j--)
        if (cuts[j] && dict.contains(s.substring(j, i))) cuts[i] = true;
    return cuts[s.length()];
  }
}

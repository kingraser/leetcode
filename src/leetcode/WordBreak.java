/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertTrue;

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
  Let cuts(i) represents whether s[0,i) can break, thus
  cuts(i) = any_of(cuts(j) && dictionary.contains(s[j, i))), 0 ≤ j < i
  */

  @Test
  public void test() {
    Set<String> dict = Sets.newHashSet("leet", "code");
    assertTrue(wordBreak("leetcode", dict));
  }

  public boolean wordBreak(String s, Set<String> dict) {
    boolean[] cuts = new boolean[s.length() + 1];
    cuts[0] = true;
    for (int i = 1; i <= s.length(); i++)
      for (int j = i - 1; !cuts[i] && j >= 0; j--)
        if (cuts[j] && dict.contains(s.substring(j, i))) cuts[i] = true;
    return cuts[s.length()];
  }
}

/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class WordBreakII {

  /*
  Given a string s and a dictionary of words dict, 
  add spaces in s to construct a sentence where each word is a valid dictionary word.
  
  Return all such possible sentences.
  
  For example, given
  s = "catsanddog",
  dict = ["cat", "cats", "and", "sand", "dog"]. 
  A solution is ["cats and dog", "cat sand dog"]. 
  */

  @Test
  public void test() {
    Set<String> dict = Sets.newHashSet("cat", "cats", "and", "sand", "dog");
    List<String> expected = Arrays.asList("cat sand dog", "cats and dog");
    assertEquals(Sets.newHashSet(expected), new HashSet<>(wordBreak("catsanddog", dict)));
  }

  public List<String> wordBreak(String s, Set<String> dic) {
    boolean[] cuts = new boolean[s.length() + 1];
    boolean[][] words = new boolean[s.length()][s.length() + 1];
    cuts[0] = true;
    for (int i = 1; i <= s.length(); i++)
      for (int j = i - 1; j >= 0; j--)
        if (cuts[j] && dic.contains(s.substring(j, i))) {
          cuts[i] = true;
          words[j][i] = true;
        }
    List<String> result = new ArrayList<>();
    dfs(s, words, 0, new ArrayList<>(), result);
    return result;
  }

  private void dfs(String s, boolean[][] words, int start, List<String> path, List<String> result) {
    if (start == s.length()) {
      result.add(String.join(" ", path));
      return;
    }
    for (int i = start + 1; i <= s.length(); i++)
      if (words[start][i]) {
        path.add(s.substring(start, i));
        dfs(s, words, i, path, result);
        path.remove(path.size() - 1);
      }
  }
}

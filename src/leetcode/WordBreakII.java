/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

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
    boolean[] cuts = new boolean[s.length() + 1];//长度为n的字符串有n+1个隔板
    boolean[][] words = new boolean[s.length()][s.length() + 1];//words[i][j]为true,表示s[i, j)是一个合法单词且可以从i处切开
    cuts[0] = true;
    for (int i = 1; i <= s.length(); i++)
      for (int j = i - 1; j >= 0; j--)
        if (cuts[j] && dic.contains(s.substring(j, i))) {
          cuts[i] = true;
          words[j][i] = true;
        }
    List<String> result = new ArrayList<>();
    genPath(s, words, s.length(), new ArrayList<>(), result);
    return result;
  }

  // DFS 遍历树,生成路径
  private void genPath(String s, boolean[][] words, int tail, List<String> path, List<String> result) {
    if (tail == 0) {
      StringBuilder sb = new StringBuilder();
      for (int i = path.size() - 1; i >= 0; i--)
        sb.append(path.get(i)).append(" ");
      result.add(sb.deleteCharAt(sb.length() - 1).toString());
      return;
    }
    for (int i = tail - 1; i >= 0; i--)
      if (words[i][tail]) {
        path.add(s.substring(i, tail));
        genPath(s, words, i, path, result);
        path.remove(path.size() - 1);
      }
  }
}

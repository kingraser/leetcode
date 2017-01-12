/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月12日<p>
//-------------------------------------------------------
public class SubstringwithConcatenationofAllWords {

  /*
  You are given a string, S, and a list of words, L, that are all of the same length. 
  Find all starting indices of substring(s) in S 
  that is a concatenation of each word in L exactly once and without any intervening characters.
  For example, given:
  S: "barfoothefoobarman"
  L: ["foo", "bar"]
  You should return the indices: [0,9].(order does not matter).
  */

  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> result = new ArrayList<>();
    if (words == null || words.length == 0 || words[0].length() * words.length > s.length()) return result;
    Map<String, Integer> dictionary = new HashMap<>();
    Arrays.stream(words).forEach(str -> dictionary.compute(str, (k, v) -> v == null ? 1 : v + 1));
    A: for (int i = 0, size = words[0].length(), end = s.length() - size * words.length; i <= end; i++) {
      Map<String, Integer> current = new HashMap<>(dictionary);
      for (Integer j = 0, v; j < words.length; j++) {
        String word = s.substring(i + j * size, i + (j + 1) * size);
        if ((v = current.get(word)) == null) continue A;
        else if (v == 1) current.remove(word);
        else current.put(word, v - 1);
      }
      if (current.isEmpty()) result.add(i);
    }
    return result;
  }

  @Test
  public void test() {
    assertEquals(Arrays.asList(0, 9), findSubstring("barfoothefoobarman", new String[] { "foo", "bar" }));
  }
}

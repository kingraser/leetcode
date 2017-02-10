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

import com.google.common.collect.ImmutableMap;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class RepeatedDNASequences {

  /*
  All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". 
  When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.  
  Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
  
  For example,  
  Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
  Return:
  ["AAAAACCCCC", "CCCCCAAAAA"].
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList("AAAAACCCCC", "CCCCCAAAAA"),
        findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
  }

  private Map<Character, Integer> map = ImmutableMap.of('A', 0, 'C', 1, 'G', 2, 'T', 3);

  public List<String> findRepeatedDnaSequences(String s) {
    Map<Integer, Integer> map = new HashMap<>();
    List<String> result = new ArrayList<>();
    for (int i = 0; i < s.length() - 9; i++)
      if (map.compute(hashCode(i, s), (k, v) -> v == null ? 1 : v + 1) == 2) result.add(s.substring(i, i + 10));
    return result;
  }

  private int hashCode(int idx, String s) {
    int hashCode = 0;
    for (int j = 0; j < 10; hashCode = (hashCode << 2) | map.get(s.charAt(idx + j++)));
    return hashCode;
  }
}

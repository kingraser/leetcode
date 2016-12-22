/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月12日<p>
//-------------------------------------------------------
public class GroupAnagrams {

  /*
  Given an array of strings, group anagrams together.
  
  For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
  Return:
  
  [
    ["ate", "eat","tea"],
    ["nat","tan"],
    ["bat"]
  ]
  
  Note:
  
  For the return value, each inner list's elements must follow the lexicographic order.
  All inputs will be in lower-case.    
  */

  public List<List<String>> anagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    Arrays.stream(strs).forEach(s -> map
        .computeIfAbsent(new String(s.chars().sorted().toArray(), 0, s.length()), k -> new ArrayList<>()).add(s));
    map.values().forEach(Collections::sort);
    return new ArrayList<>(map.values());
  }

  @Test
  public void test() {
    assertEquals(Stream.of(Arrays.asList("ate", "eat", "tea"), Arrays.asList("nat", "tan"), Arrays.asList("bat"))
        .collect(Collectors.toSet()),
        new HashSet<>(anagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" })));
  }
}

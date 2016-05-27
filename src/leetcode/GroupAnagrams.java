/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
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

    //first 26 primes for 26 letters
    private static final BigInteger[] PRIMES = Arrays.stream(new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
            43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101 }).mapToObj(i -> BigInteger.valueOf(i))
            .toArray(size -> new BigInteger[size]);

    public List<List<String>> anagrams(String[] strs) {
        Map<BigInteger, List<String>> map = new HashMap<>();
        Arrays.stream(strs).forEach(s -> map.computeIfAbsent(getHashCode(s), k -> new ArrayList<>()).add(s));
        map.values().forEach(Collections::sort);
        return new ArrayList<>(map.values());
    }

    private BigInteger getHashCode(String s) {
        BigInteger hashCode = BigInteger.ONE;
        for (char c : s.toCharArray())
            hashCode = hashCode.multiply(PRIMES[c - 'a']);
        return hashCode;
    }

    @Test
    public void test() {
        Assert.assertEquals(
                Stream.of(Arrays.asList("ate", "eat", "tea"), Arrays.asList("nat", "tan"), Arrays.asList("bat"))
                        .collect(Collectors.toSet()),
                new HashSet<>(anagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" })));
    }
}

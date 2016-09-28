/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月19日;
//-------------------------------------------------------
public class BinaryWatch {

    /*
    A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).    
    Each LED represents a zero or one, with the least significant bit on the right.
    ●●○○
    ●○○●●○
    For example, the above binary watch reads "3:25".    
    Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.    
    Example:    
    Input: n = 1
    Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"] 
    Note:    
    The order of output does not matter.
    The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
    The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
    */

    private static final Map<Integer, List<String>> RESULT = new HashMap<>();

    static {
        for (int i = 0; i < 12; i++)
            for (int j = 0; j < 60; j++)
                RESULT.computeIfAbsent(Integer.bitCount(j | i << 6), k -> new ArrayList<>())
                        .add(String.format("%d:%02d", i, j));
    }

    public List<String> readBinaryWatch(int num) {
        return RESULT.getOrDefault(num, new ArrayList<>());
    }

    @Test
    public void test() {
        Assert.assertEquals(
                Sets.newHashSet("1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"),
                new HashSet<>(readBinaryWatch(1)));
    }
}

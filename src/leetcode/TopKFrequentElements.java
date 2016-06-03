/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年5月3日;
//-------------------------------------------------------
public class TopKFrequentElements {

    @Test
    public void test() {
        Assert.assertEquals(Arrays.asList(1, 2), topKFrequent(new int[] { 1, 1, 1, 2, 2, 3 }, 2));
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length << 1);
        Arrays.stream(nums).forEach(i -> map.compute(i, (key, v) -> v == null ? 1 : v + 1));
        return map.keySet().stream().sorted((k1, k2) -> map.get(k2) - map.get(k1)).limit(k)
                .collect(Collectors.toList());
    }
}

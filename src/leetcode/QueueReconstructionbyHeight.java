/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年9月26日;
//-------------------------------------------------------
public class QueueReconstructionbyHeight {

  public int[][] reconstructQueue(int[][] people) {
    Arrays.sort(people, (p1, p2) -> p2[0] == p1[0] ? p1[1] - p2[1] : p2[0] - p1[0]);
    List<int[]> result = new LinkedList<>();
    Arrays.stream(people).forEach(p -> result.add(p[1], p));
    return result.toArray(people);
  }
}

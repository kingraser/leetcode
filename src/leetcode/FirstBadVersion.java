/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月8日<p>
//-------------------------------------------------------
public class FirstBadVersion {
  /*
  You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
  
  Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
  
  You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API. 
  */

  @Test
  public void test() {
    Version version = new Version(10);
    assertEquals(version.bad, version.firstBadVersion(version.size));
  }

  public class Version {
    public int bad, size;
    private Random random = new Random();

    public Version(int size) {
      this.size = size;
      this.bad = random.nextInt(size) + 1;
    }

    public boolean isBadVersion(int version) {
      return bad <= version;
    }

    public int firstBadVersion(int n) {
      int l = 1, r = n, m;
      while (l < r)
        if (isBadVersion(m = l + ((r - l) >> 1))) r = m;
        else l = m + 1;
      return l;
    }
  }

}

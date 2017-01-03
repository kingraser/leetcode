/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月15日<p>
//-------------------------------------------------------
public class SimplifyPath {

  /*
  Given an absolute path for a file (Unix-style), simplify it.
  For example,
  path = "/home/", => "/home"
  path = "/a/./b/../../c/", => "/c"
  Corner Cases:
      Did you consider the case where path = "/../"? In this case, you should return "/".
      Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
      In this case, you should ignore redundant slashes and return "/home/foo".
  */

  public String simplifyPath(String path) {
    Deque<String> deque = new ArrayDeque<>();
    Arrays.stream(path.split("/")).map(String::trim).filter(s -> s.length() > 0 && !s.equals(".")).forEach(s -> {
      if ("..".equals(s)) deque.pollLast();
      else deque.addLast(s);
    });
    StringBuffer sb = new StringBuffer("/");
    deque.forEach(s -> sb.append(s).append("/"));
    return sb.substring(0, sb.length() - (sb.length() > 1 ? 1 : 0));
  }

  @Test
  public void test() {
    assertEquals("/", simplifyPath("/.."));
    assertEquals("/home", simplifyPath("/home/"));
    assertEquals("/c", simplifyPath("/a/./b/../../c/"));
  }

}

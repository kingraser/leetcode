package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;

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
    for (String s : path.split("/"))
      if ("..".equals(s = s.trim())) deque.pollLast();
      else if (s.length() > 0 && !".".equals(s)) deque.addLast(s);
    return "/" + String.join("/", deque);
  }

  @Test
  public void test() {
    assertEquals("/", simplifyPath("/.."));
    assertEquals("/home", simplifyPath("/home/"));
    assertEquals("/c", simplifyPath("/a/./b/../../c/"));
  }

}

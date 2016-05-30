/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import org.junit.Assert;
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
        path = path.replace(" ", "");
        Deque<String> deque = new ArrayDeque<>();
        Arrays.stream(path.split("/")).filter(s -> s.length() > 0 && !s.equals(".")).forEach(s -> {
            if ("..".equals(s)) deque.pollLast();
            else deque.addLast(s);
        });
        if (deque.isEmpty()) return "/";
        StringBuffer sb = new StringBuffer();
        deque.forEach(s -> sb.append("/").append(s));
        return sb.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("/", simplifyPath("/.."));
    }

}

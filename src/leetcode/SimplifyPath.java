/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

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
        String[] paths = path.split("/");
        StringBuffer sb = new StringBuffer("/");
        List<String> list = Lists.newArrayListWithCapacity(paths.length + 1);
        for (int i = 0; i < paths.length; i++)
            if (paths[i].length() == 0 || paths[i].equals(".")) continue;
            else if ("..".equals(paths[i])) {
                if (list.size() > 0) list.remove(list.size() - 1);
            } else list.add(paths[i]);
        for (int i = 0; i < list.size(); sb.append(list.get(i++)).append("/"));
        if (sb.length() > 1) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("/", simplifyPath("/.."));
    }

}

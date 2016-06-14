/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月18日<p>
//-------------------------------------------------------
public class CourseSchedule {

    /*
    There are a total of n courses you have to take, labeled from 0 to n - 1.    
    Some courses may have prerequisites, 
    for example to take course 0 you have to first take course 1, 
    which is expressed as a pair: [0,1]
        
    Given the total number of courses and a list of prerequisite pairs, 
    is it possible for you to finish all courses?
    
    For example:    
    2, [[1,0]]    
    There are a total of 2 courses to take. 
    To take course 1 you should have finished course 0. So it is possible.
    
    2, [[1,0],[0,1]]    
    There are a total of 2 courses to take. 
    To take course 1 you should have finished course 0, 
    and to take course 0 you should also have finished course 1.
    So it is impossible.   
    */

    @Test
    public void test() {
        Assert.assertTrue(canFinish(2, new int[][] { { 1, 0 } }));
        Assert.assertFalse(canFinish(2, new int[][] { { 1, 0 }, { 0, 1 } }));
    }

    //Topological Sort
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> courses = new HashMap<>();
        Arrays.stream(prerequisites).forEach(pair -> {
            indegree[pair[0]]++;
            courses.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        });
        Deque<Integer> zeroDegrees = new ArrayDeque<>();
        for (int i = 0; i < indegree.length; i++)
            if (indegree[i] == 0) zeroDegrees.add(i);
        for (Integer node; (node = zeroDegrees.poll()) != null; numCourses--)
            if (courses.containsKey(node)) for (int next : courses.get(node))
                if (--indegree[next] == 0) zeroDegrees.add(next);
        return numCourses == 0;
    }

}

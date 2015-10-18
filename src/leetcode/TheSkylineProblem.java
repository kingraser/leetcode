/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月18日<p>
//-------------------------------------------------------
public class TheSkylineProblem {
    /*
    A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. 
    Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo, 
    write a program to output the skyline formed by these buildings collectively.
        
    The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
    where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. 
    It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. 
    You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
    
    For instance, the dimensions of all buildings are recorded as: 
    [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .    
    The output is a list of "key points" in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. 
    A key point is the left endpoint of a horizontal line segment. 
    Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. 
    Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
    For instance, the skyline should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
    
    Notes:    
    The number of buildings in any input list is guaranteed to be in the range [0, 10000].
    The input list is already sorted in ascending order by the left x position Li.
    The output list must be sorted by the x position.
    There must be no consecutive horizontal lines of equal height in the output skyline. 
    For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; 
    the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
    */

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();
        for (int[] building : buildings) {
            nodes.add(new Node(building[0], building[2], 0));
            nodes.add(new Node(building[1], building[2], 1));
        }
        Collections.sort(nodes, new Comparator<Node>() {

            @Override
            public int compare(Node n1, Node n2) {
                if (n1.x != n2.x) return n1.x - n2.x;
                if (n1.isLeft + n2.isLeft == 0) return n2.y - n1.y;
                if (n1.isLeft + n2.isLeft == 2) return n1.y - n2.y;
                return n1.isLeft == 0 ? -1 : 1;
            }
        });
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer i1, Integer i2) {
                return -i1.compareTo(i2);
            }
        });
        heap.add(0);
        for (Node node : nodes) {
            int pre = heap.peek();
            if (node.isLeft == 0) heap.add(node.y);
            else heap.remove(node.y);
            if (pre != heap.peek()) res.add(new int[] { node.x, heap.peek() });
        }
        return res;
    }

    public class Node {

        int x, y, isLeft;

        public Node(int X, int Y, int left) {
            x = X;
            y = Y;
            isLeft = left;
        }
    }
}

/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

import java.util.ArrayList;
import java.util.List;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class UndirectedGraphNode {

    public int label;

    public List<UndirectedGraphNode> neighbors = new ArrayList<>();

    public UndirectedGraphNode(int x) {
        label = x;
    }
}

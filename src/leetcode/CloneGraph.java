/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;

import leetcode.common.UndirectedGraphNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月16日<p>
//-------------------------------------------------------
public class CloneGraph {
    /*
    Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
    
    OJ's undirected graph serialization:
    
    Nodes are labeled uniquely.
    We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
    
    As an example, consider the serialized graph {0,1,2#1,2#2,2}.
    
    The graph has a total of three nodes, and therefore contains three parts as separated by #.
    
    First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
    Second node is labeled as 1. Connect node 1 to node 2.
    Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
    
    Visually, the graph looks like the following:
    
       1
      / \
     /   \
    0 --- 2
         / \
         \_/
    */

    //dfs or bfs
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> hm = new HashMap<>();
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        hm.put(node, head);
        dfs(hm, node);
        return head;
    }

    public void dfs(Map<UndirectedGraphNode, UndirectedGraphNode> hm, UndirectedGraphNode node) {
        if (node == null) return;
        for (UndirectedGraphNode aneighbor : node.neighbors) {
            if (!hm.containsKey(aneighbor)) {
                UndirectedGraphNode newneighbor = new UndirectedGraphNode(aneighbor.label);
                hm.put(aneighbor, newneighbor);
                dfs(hm, aneighbor);
            }
            hm.get(node).neighbors.add(hm.get(aneighbor));
        }
    }
}

/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.HashMap;
import java.util.Map;

import leetcode.common.RandomListNode;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月15日<p>
//-------------------------------------------------------
public class CopyListwithRandomPointer {

  /*
  A linked list is given such that each node contains an additional random pointer 
  which could point to any node in the list or null.
  
  Return a deep copy of the list. 
  */

  public RandomListNode copyRandomList(RandomListNode head) {
    Map<RandomListNode, RandomListNode> map = new HashMap<>();
    for (RandomListNode n = head; n != null; map.put(n, new RandomListNode(n.label)), n = n.next);
    for (RandomListNode n = head; n != null; map.get(n).next = map.get(n.next), map.get(n).random = map
        .get(n.random), n = n.next);
    return map.get(head);
  }

}

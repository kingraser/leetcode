/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月15日<p>
//-------------------------------------------------------
public class RandomListNode {
  public int label;
  public RandomListNode next, random;

  public RandomListNode(int x) {
    this.label = x;
  }

  @Override
  public String toString() {
    return Integer.toString(label);
  }

  @Override
  public boolean equals(Object o) {
    if (Objects.isNull(o) || !(o instanceof RandomListNode)) return false;
    List<RandomListNode> these = new ArrayList<>(), those = new ArrayList<>();
    dfs(these, this);
    dfs(those, (RandomListNode) o);
    if (these.size() != those.size()) return false;
    for (int i = 0; i < these.size(); i++)
      if (!equals(these.get(i), these.get(i), true)) return false;
    return true;
  }

  private void dfs(List<RandomListNode> history, RandomListNode node) {
    if (Objects.isNull(history)) throw new RuntimeException("history can not be null");
    if (isReached(history, node)) return;
    history.add(node);
    if (Objects.nonNull(node)) {
      dfs(history, node.next);
      dfs(history, node.random);
    }
  }

  private boolean isReached(List<RandomListNode> history, RandomListNode node) {
    if (Objects.isNull(history) || history.isEmpty()) return false;
    for (RandomListNode n : history)
      if (n == node) return true;
    return false;
  }

  private boolean equals(RandomListNode n1, RandomListNode n2, boolean checkNextAndRandom) {
    if (n1 == n2) return true;
    if (Objects.isNull(n1) || Objects.isNull(n2)) return false;
    if (n1.label != n2.label) return false;
    if (!checkNextAndRandom) return true;
    if (!equals(n1.next, n2.next, false) || !equals(n1.random, n2.random, false)) return false;
    return true;
  }
}

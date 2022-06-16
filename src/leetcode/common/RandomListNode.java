
package leetcode.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
      if (!equals(these.get(i), those.get(i), true)) return false;
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
    return Objects.nonNull(history) && history.stream().anyMatch(past -> past == node);
  }

  private boolean equals(RandomListNode n1, RandomListNode n2, boolean checkNextAndRandom) {
    if (n1 == n2) return true;
    if (Objects.isNull(n1) || Objects.isNull(n2)) return false;
    if (n1.label != n2.label) return false;
    if (!checkNextAndRandom) return true;
    return equals(n1.next, n2.next, false) && equals(n1.random, n2.random, false);
  }

  public RandomListNode clone() {
    Map<RandomListNode, RandomListNode> map = new HashMap<>();
    for (RandomListNode n = this; n != null; map.put(n, new RandomListNode(n.label)), n = n.next);
    for (RandomListNode n = this; n != null; map.get(n).next = map.get(n.next), map.get(n).random = map
        .get(n.random), n = n.next);
    return map.get(this);
  }
}

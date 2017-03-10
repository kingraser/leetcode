
package leetcode.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UndirectedGraphNode {
  public int label;
  public List<UndirectedGraphNode> neighbors = new ArrayList<>();

  public UndirectedGraphNode(int x) {
    label = x;
  }

  @Override
  public String toString() {
    return Integer.toString(label);
  }

  @Override
  public boolean equals(Object o) {
    if (Objects.isNull(o) || !(o instanceof UndirectedGraphNode)) return false;
    List<UndirectedGraphNode> these = new ArrayList<>(), those = new ArrayList<>();
    dfs(these, this);
    dfs(those, (UndirectedGraphNode) o);
    if (these.size() != those.size()) return false;
    for (int i = 0; i < these.size(); i++)
      if (!equals(these.get(i), those.get(i), true)) return false;
    return true;
  }

  private static boolean equals(UndirectedGraphNode n1, UndirectedGraphNode n2, boolean checkNeighbor) {
    if (n1 == n2) return true;
    if (Objects.isNull(n1) || Objects.isNull(n2)) return false;
    if (n1.label != n2.label) return false;
    if (!checkNeighbor) return true;
    if (n1.neighbors.size() != n2.neighbors.size()) return false;
    for (int i = 0; i < n1.neighbors.size(); i++)
      if (!equals(n1.neighbors.get(i), n2.neighbors.get(i), false)) return false;
    return true;
  }

  private static void dfs(List<UndirectedGraphNode> history, UndirectedGraphNode node) {
    if (Objects.isNull(history)) throw new RuntimeException("history can not be null");
    if (isReached(history, node)) return;
    history.add(node);
    if (Objects.nonNull(node)) node.neighbors.forEach(neighbor -> dfs(history, neighbor));
  }

  private static boolean isReached(List<UndirectedGraphNode> history, UndirectedGraphNode node) {
    return Objects.nonNull(history) && history.stream().anyMatch(past -> past == node);
  }

  public UndirectedGraphNode clone() {
    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    dfs(map, this);
    return map.get(this);
  }

  private void dfs(Map<UndirectedGraphNode, UndirectedGraphNode> map, UndirectedGraphNode node) {
    if (map.containsKey(node)) return;
    map.put(node, new UndirectedGraphNode(node.label));
    node.neighbors.forEach(neighbor -> {
      dfs(map, neighbor);
      map.get(node).neighbors.add(map.get(neighbor));
    });
  }
}

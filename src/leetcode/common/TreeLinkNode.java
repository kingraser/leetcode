/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class TreeLinkNode {
  public int val;
  public TreeLinkNode left, right, next;

  public TreeLinkNode(int x) {
    val = x;
  }

  public TreeLinkNode(int x, TreeLinkNode left, TreeLinkNode right) {
    val = x;
    this.left = left;
    this.right = right;
  }

  @Override
  public String toString() {
    return toString(this) + "#" + next(this, new ArrayList<>());
  }

  private String next(TreeLinkNode node, List<String> history) {
    dfs(node, history);
    return String.join(",", history);
  }

  private void dfs(TreeLinkNode node, List<String> list) {
    if (node == null) return;
    list.add(node.val + "->" + (node.next == null ? "n" : node.next.val));
    dfs(node.left, list);
    dfs(node.right, list);
  }

  public static TreeLinkNode tree(String s) {
    String[] array = s.split("#");
    String s1 = array[0], s2 = array[1];
    array = s1.split(",");
    Stack<TreeLinkNode> stack = new Stack<>();
    for (int i = array.length - 1; i >= 0; i--)
      if (Objects.equals("n", array[i])) stack.push(null);
      else stack.push(new TreeLinkNode(Integer.parseInt(array[i]), stack.pop(), stack.pop()));
    TreeLinkNode root = stack.peek();
    Arrays.stream(s2.split(",")).forEach(pair -> next(pair, root));
    return root;
  }

  private static void next(String pair, TreeLinkNode root) {
    String[] array = pair.split("->");
    find(array[0], root).next = find(array[1], root);
  }

  private static TreeLinkNode find(String s, TreeLinkNode root) {
    if (s.equals("n") || root == null) return null;
    if (root.val == Integer.parseInt(s)) return root;
    TreeLinkNode node = find(s, root.left);
    return node == null ? find(s, root.right) : node;
  }

  private String toString(TreeLinkNode node) {
    if (Objects.isNull(node)) return "n";
    return String.join(",", Integer.toString(node.val), toString(node.left), toString(node.right));
  }

  /**
   * make sure there is no circle in the tree
   */
  @Override
  public boolean equals(Object o) {
    if (Objects.isNull(o) || !(o instanceof TreeLinkNode)) return false;
    TreeLinkNode other = (TreeLinkNode) o;
    return val == other.val && Objects.equals(left, other.left) && Objects.equals(right, other.right)
        && Objects.equals(next, other.next);
  }
}

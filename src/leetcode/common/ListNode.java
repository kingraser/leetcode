
package leetcode.common;

import java.util.Objects;

public class ListNode {
  public int val;
  public ListNode next = null;

  public ListNode(int val) {
    this.val = val;
  }

  public ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  public ListNode last() {
    ListNode node = this;
    while (node.next != null)
      node = node.next;
    return node;
  }

  public ListNode next(int n) {
    ListNode node = this;
    while (n-- > 0 && node != null)
      node = node.next;
    return node;
  }

  public int size() {
    int count = 0;
    for (ListNode node = this; node != null; node = node.next, count++);
    return count;
  }

  @Override
  public boolean equals(Object o) {
    if (null == o || !(o instanceof ListNode)) return false;
    ListNode another = (ListNode) o;
    return val == another.val && Objects.equals(next, another.next);
  }

  public static ListNode list(int... array) {
    if (Objects.isNull(array) || array.length == 0) return null;
    ListNode head = new ListNode(array[0]), march = head;
    for (int i = 1; i < array.length; march = march.next = new ListNode(array[i++]));
    return head;
  }

  @Override
  public String toString() {
    return toString(this);
  }

  private String toString(ListNode node) {
    if (Objects.isNull(node)) return "null";
    return node.val + "->" + toString(node.next);
  }

  public Pair<ListNode, ListNode> breakFromMiddle() {
    if (this.next == null) return new Pair<ListNode, ListNode>(this, null);
    ListNode prev = null, slow = this, fast = this;
    while (fast != null && fast.next != null) {
      prev = slow;
      fast = fast.next.next;
      slow = slow.next;
    }
    prev.next = null;
    return new Pair<ListNode, ListNode>(this, slow);
  }
}

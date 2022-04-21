
package leetcode.common;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

public class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;
        this.left = left;
        this.right = right;
    }

    public TreeNode(TreeNode node) {
        val = node.val;
        if (Objects.nonNull(node.left)) left = new TreeNode(node.left);
        if (Objects.nonNull(node.right)) right = new TreeNode(node.right);
    }

    @Override
    public boolean equals(Object o) {
        if (Objects.isNull(o) || !(o instanceof TreeNode)) return false;
        TreeNode another = (TreeNode) o;
        return val == another.val && Objects.equals(left, another.left) && Objects.equals(right, another.right);
    }

    /*
       1
      / \
     2   3
        / \
       4   5

    1,2,n,n,3,4,n,n,5,n,n
    */
    public static TreeNode tree(String s) {
        String[] nodes = s.split(",");
        Stack<TreeNode> stack = new Stack<>();
        for (int i = nodes.length - 1; i >= 0; i--)
            if (Objects.equals("n", nodes[i])) stack.push(null);
            else stack.push(new TreeNode(Integer.parseInt(nodes[i]), stack.pop(), stack.pop()));
        return stack.peek();
    }

    /*
       1
      / \
     2   3
        / \
       4   5

    1,2,3,n,n,4,5
    */

    public static TreeNode treeLevel(String s) {
        return treeLevel(s, "null");
    }

    public static TreeNode treeLevel(String s, String nullString) {
        String[] nodes = s.split(",");
        if (nodes.length < 1) return null;
        TreeNode root = getNode(nodes[0], nullString), node;
        if (root == null) return root;
        LinkedList<TreeNode> queue = new LinkedList<>() {{add(root);}};
        for (int size, i = 1; !queue.isEmpty(); )
            for (size = queue.size(); size-- > 0; ) {
                node = queue.pollFirst();
                if (i >= nodes.length) return root;
                if ((node.left = getNode(nodes[i++], nullString)) != null) queue.add(node.left);
                if (i >= nodes.length) return root;
                if ((node.right = getNode(nodes[i++], nullString)) != null) queue.add(node.right);
            }
        return root;
    }

    static TreeNode getNode(String node, String nullString) {
        return nullString.equalsIgnoreCase(node) ? null : new TreeNode(Integer.parseInt(node));
    }

    public static void main(String[] args) {
        System.out.println(TreeNode.treeLevel("4,2,null,1,1,3,null,null,1").toString());
    }

    @Override
    public String toString() {
        return toString(this);
    }

    private String toString(TreeNode node) {
        if (Objects.isNull(node)) return "n";
        return String.join(",", Integer.toString(node.val), toString(node.left), toString(node.right));
    }

    public static int getDepth(TreeNode node) {
        if (Objects.isNull(node)) return 0;
        return 1 + Math.max(getDepth(node.left), getDepth(node.right));
    }

    public boolean isLeaf() {
        return Objects.isNull(left) && Objects.isNull(right);
    }
}

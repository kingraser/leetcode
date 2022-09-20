package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wit
 */
public class RecoverATreeFromPreorderTraversal {
	/*
	We run a preorder depth-first search (DFS) on the root of a binary tree.
	At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of this node.  If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.
	If a node has only one child, that child is guaranteed to be the left child.
	Given the output traversal of this traversal, recover the tree and return its root.

	Example 1:
	Input: traversal = "1-2--3--4-5--6--7"
	Output: [1,2,5,3,4,6,7]

	Example 2:
	Input: traversal = "1-2--3---4-5--6---7"
	Output: [1,2,5,3,null,6,null,4,null,7]

	Example 3:
	Input: traversal = "1-401--349---90--88"
	Output: [1,401,null,349,88,90]

	Constraints:
	The number of nodes in the original tree is in the range [1, 1000].
	1 <= Node.val <= 10^9
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TreeNode.treeLevel("1,2,5,3,4,6,7"), "1-2--3--4-5--6--7"},
				{TreeNode.treeLevel("1,2,5,3,null,6,null,4,null,7"), "1-2--3---4-5--6---7"},
				{TreeNode.treeLevel("1,401,null,349,88,90"), "1-401--349---90--88"},
		});
	}

	public TreeNode recoverFromPreorder(String traversal) {
		char[] chars = traversal.toCharArray();
		List<TreeNode> list = new ArrayList<>(1000);
		for (int i = 0, j = 0, level, val; i < chars.length; j = i) {
			while (chars[i] == '-') i++;
			for (level = i - j, val = 0; i < chars.length && chars[i] != '-'; ) val = val * 10 + chars[i++] - '0';
			add(list, level, new TreeNode(val));
			if (level > 0) setChild(list.get(level - 1), list.get(level));
		}
		return list.get(0);
	}

	<T> void add(List<T> list, int index, T t) {
		if (list.size() <= index) list.add(t);
		else list.set(index, t);
	}

	void setChild(TreeNode parent, TreeNode child) {
		if (parent.left == null) parent.left = child;
		else parent.right = child;
	}
}

package leetcode;

import leetcode.common.TreeNode;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;

/**
 * @author Wit
 */
public class MinimumNumberOfOperationsToSortaBinaryTreeByLevel {
	/*
	You are given the root of a binary tree with unique values.
	In one operation, you can choose any two nodes at the same level and swap their values.
	Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.
	The level of a node is the number of edges along the path between it and the root node.

	Example 1:
	Input: root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
	Output: 3
	Explanation:
	- Swap 4 and 3. The 2nd level becomes [3,4].
	- Swap 7 and 5. The 3rd level becomes [5,6,8,7].
	- Swap 8 and 7. The 3rd level becomes [5,6,7,8].
	We used 3 operations so return 3.
	It can be proven that 3 is the minimum number of operations needed.

	Example 2:
	Input: root = [1,3,2,7,6,5,4]
	Output: 3
	Explanation:
	- Swap 3 and 2. The 2nd level becomes [2,3].
	- Swap 7 and 4. The 3rd level becomes [4,6,5,7].
	- Swap 6 and 5. The 3rd level becomes [4,5,6,7].
	We used 3 operations so return 3.
	It can be proven that 3 is the minimum number of operations needed.

	Example 3:
	Input: root = [1,2,3,4,5,6]
	Output: 0
	Explanation: Each level is already sorted in increasing order so return 0.

	Constraints:
	The number of nodes in the tree is in the range [1, 10^5].
	1 <= Node.val <= 10^5
	All the values of the tree are unique.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{3, TreeNode.treeLevel("1,4,3,7,6,8,5,null,null,null,null,9,null,10")},
				{3, TreeNode.treeLevel("1,3,2,7,6,5,4")},
				{0, TreeNode.treeLevel("1,2,3,4,5,6")},
		});
	}

	//bfs
	public int minimumOperations(TreeNode root) {
		int result = 0;
		for (ArrayDeque<TreeNode> queue = new ArrayDeque<>() {{add(root);}}; !queue.isEmpty(); ) {
			int size = queue.size(), level[][] = new int[size][2];
			TreeNode first;
			for (int i = 0; i < size; level[i] = new int[]{first.val, i++}) {
				if ((first = queue.pollFirst()).left != null) queue.add(first.left);
				if (first.right != null) queue.add(first.right);
			}
			Arrays.sort(level, Comparator.comparingInt(a -> a[0]));
			for (int i = 0, temp; i < size; )
				if (level[i][1] != i) {
					level[i][1] = level[temp = level[i][1]][1];
					level[temp][1] = temp;
					result++;
				} else i++;
		}
		return result;
	}

	//dfs
	public int minimumOperationsII(TreeNode root) {
		int result = 0;
		List<List<Integer>> levelList = new ArrayList<>();
		dfs(root, levelList, 0);
		for (List<Integer> level : levelList) result += count(level);
		return result;
	}

	private int count(List<Integer> levelList) {
		int result = 0, level[][] = new int[levelList.size()][2];
		for (int i = 0; i < levelList.size(); i++) level[i] = new int[]{levelList.get(i), i};
		Arrays.sort(level, Comparator.comparingInt(a -> a[0]));
		for (int i = 0, temp; i < level.length; )
			if (level[i][1] != i) {
				level[i][1] = level[temp = level[i][1]][1];
				level[temp][1] = temp;
				result++;
			} else i++;
		return result;
	}

	private void dfs(TreeNode node, List<List<Integer>> levelList, int levelIndex) {
		if (node == null) return;
		if (levelIndex == levelList.size()) levelList.add(new ArrayList<>());
		levelList.get(levelIndex).add(node.val);
		dfs(node.left, levelList, levelIndex + 1);
		dfs(node.right, levelList, levelIndex + 1);
	}
}
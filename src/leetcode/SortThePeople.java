package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @author Wit
 */
public class SortThePeople {
	/*
	You are given an array of strings names, and an array heights that consists of distinct positive integers. Both arrays are of length n.
	For each index i, names[i] and heights[i] denote the name and height of the ith person.
	Return names sorted in descending order by the people's heights.

	Example 1:
	Input: names = ["Mary","John","Emma"], heights = [180,165,170]
	Output: ["Mary","Emma","John"]
	Explanation: Mary is the tallest, followed by Emma and John.

	Example 2:
	Input: names = ["Alice","Bob","Bob"], heights = [155,185,150]
	Output: ["Bob","Alice","Bob"]
	Explanation: The first Bob is the tallest, followed by Alice and the second Bob.

	Constraints:
	n == names.length == heights.length
	1 <= n <= 103
	1 <= names[i].length <= 20
	1 <= heights[i] <= 10^5
	names[i] consists of lower and upper case English letters.
	All the values of heights are distinct.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{new String[]{"Mary", "Emma", "John"}, new String[]{"Mary", "John", "Emma"}, new int[]{180, 165, 170}},
				{new String[]{"Bob", "Alice", "Bob"}, new String[]{"Alice", "Bob", "Bob"}, new int[]{155, 185, 150}},
		});
	}

	public String[] sortPeople(String[] names, int[] heights) {
		return IntStream.range(0, names.length).boxed().sorted((a, b) -> heights[b] - heights[a]).map(i -> names[i]).toArray(String[]::new);
	}
}

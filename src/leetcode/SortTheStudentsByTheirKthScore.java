package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SortTheStudentsByTheirKthScore {
	/*
	There is a class with m students and n exams. You are given a 0-indexed m x n integer matrix score, where each row represents one student and score[i][j] denotes the score the ith student got in the jth exam. The matrix score contains distinct integers only.
	You are also given an integer k. Sort the students (i.e., the rows of the matrix) by their scores in the kth (0-indexed) exam from the highest to the lowest.
	Return the matrix after sorting it.

	Example 1:
	Input: score = [[10,6,9,1],[7,5,11,2],[4,8,3,15]], k = 2
	Output: [[7,5,11,2],[10,6,9,1],[4,8,3,15]]
	Explanation: In the above diagram, S denotes the student, while E denotes the exam.
	- The student with index 1 scored 11 in exam 2, which is the highest score, so they got first place.
	- The student with index 0 scored 9 in exam 2, which is the second highest score, so they got second place.
	- The student with index 2 scored 3 in exam 2, which is the lowest score, so they got third place.

	Example 2:
	Input: score = [[3,4],[5,6]], k = 0
	Output: [[5,6],[3,4]]
	Explanation: In the above diagram, S denotes the student, while E denotes the exam.
	- The student with index 1 scored 5 in exam 0, which is the highest score, so they got first place.
	- The student with index 0 scored 3 in exam 0, which is the lowest score, so they got second place.

	Constraints:
	m == score.length
	n == score[i].length
	1 <= m, n <= 250
	1 <= score[i][j] <= 10^5
	score consists of distinct integers.
	0 <= k < n
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{TestUtil.getArray("[[7,5,11,2],[10,6,9,1],[4,8,3,15]]"), TestUtil.getArray("[[10,6,9,1],[7,5,11,2],[4,8,3,15]]"), 2},
				{TestUtil.getArray("[[5,6],[3,4]]"), TestUtil.getArray("[[3,4],[5,6]]"), 0},
		});
	}

	public int[][] sortTheStudents(int[][] score, int k) {
		sortTheStudents(score, k, 0, score.length - 1);
		return score;
	}

	private void sortTheStudents(int[][] score, int k, int from, int to) {
		if (from >= to) return;
		int flagElement = score[from][k], idx = from;
		for (int row = from + 1; row <= to; row++)
			if (flagElement < score[row][k]) swap(score, ++idx, row);
		swap(score, from, idx);
		sortTheStudents(score, k, from, idx - 1);
		sortTheStudents(score, k, idx + 1, to);
	}

	private void swap(int[][] score, int row1, int row2) {
		if (row1 == row2) return;
		int[] temp = score[row1];
		score[row1] = score[row2];
		score[row2] = temp;
	}
}

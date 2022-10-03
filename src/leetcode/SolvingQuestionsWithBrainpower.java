package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class SolvingQuestionsWithBrainpower {
	/*
	You are given a 0-indexed 2D integer array questions where questions[i] = [points_i, brainpower_i].
	The array describes the questions of an exam, where you have to process the questions in order (i.e., starting from question 0) and make a decision whether to solve or skip each question. Solving question i will earn you points_i points but you will be unable to solve each of the next brainpower_i questions. If you skip question i, you get to make the decision on the next question.
	For example, given questions = [[3, 2], [4, 3], [4, 4], [2, 5]]:
	If question 0 is solved, you will earn 3 points but you will be unable to solve questions 1 and 2.
	If instead, question 0 is skipped and question 1 is solved, you will earn 4 points, but you will be unable to solve questions 2 and 3.
	Return the maximum points you can earn for the exam.

	Example 1:
	Input: questions = [[3,2],[4,3],[4,4],[2,5]]
	Output: 5
	Explanation: The maximum points can be earned by solving questions 0 and 3.
	- Solve question 0: Earn 3 points, will be unable to solve the next 2 questions
	- Unable to solve questions 1 and 2
	- Solve question 3: Earn 2 points
	Total points earned: 3 + 2 = 5. There is no other way to earn 5 or more points.

	Example 2:
	Input: questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
	Output: 7
	Explanation: The maximum points can be earned by solving questions 1 and 4.
	- Skip question 0
	- Solve question 1: Earn 2 points, will be unable to solve the next 2 questions
	- Unable to solve questions 2 and 3
	- Solve question 4: Earn 5 points
	Total points earned: 2 + 5 = 7. There is no other way to earn 7 or more points.

	Constraints:
	1 <= questions.length <= 10^5
	questions[i].length == 2
	1 <= points_i, brainpower_i <= 10^5
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{5L, TestUtil.getArray("[[3,2],[4,3],[4,4],[2,5]]")},
				{7L, TestUtil.getArray("[[1,1],[2,2],[3,3],[4,4],[5,5]]")},
		});
	}

	//For each question, we can either solve it or skip it. How can we use Dynamic Programming to decide the most optimal option for each problem?
	//We store for each question the maximum points we can earn if we started the exam on that question.
	//If we skip a question, then the answer for it will be the same as the answer for the next question.
	//If we solve a question, then the answer for it will be the points of the current question plus the answer for the next solvable question.
	//The maximum of these two values will be the answer to the current question.
	public long mostPoints(int[][] questions) {
		long[] maxPointsStartFrom = new long[questions.length];
		int length = questions.length, end = length - 1;
		maxPointsStartFrom[end] = questions[end][0];
		for (int i = end - 1, next; i >= 0; i--)
			maxPointsStartFrom[i] = Math.max(questions[i][0] + ((next = i + questions[i][1] + 1) < questions.length ? maxPointsStartFrom[next] : 0), maxPointsStartFrom[i + 1]);
		return maxPointsStartFrom[0];
	}
}

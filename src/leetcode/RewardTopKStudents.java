package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wit
 */
public class RewardTopKStudents {
	/*
	You are given two string arrays positive_feedback and negative_feedback, containing the words denoting positive and negative feedback, respectively. Note that no word is both positive and negative.
	Initially every student has 0 points. Each positive word in a feedback report increases the points of a student by 3, whereas each negative word decreases the points by 1.
	You are given n feedback reports, represented by a 0-indexed string array report and a 0-indexed integer array student_id, where student_id[i] represents the ID of the student who has received the feedback report report[i]. The ID of each student is unique.
	Given an integer k, return the top k students after ranking them in non-increasing order by their points. In case more than one student has the same points, the one with the lower ID ranks higher.

	Example 1:
	Input: positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is studious","the student is smart"], student_id = [1,2], k = 2
	Output: [1,2]
	Explanation:
	Both the students have 1 positive feedback and 3 points but since student 1 has a lower ID he ranks higher.

	Example 2:
	Input: positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report = ["this student is not studious","the student is smart"], student_id = [1,2], k = 2
	Output: [2,1]
	Explanation:
	- The student with ID 1 has 1 positive feedback and 1 negative feedback, so he has 3-1=2 points.
	- The student with ID 2 has 1 positive feedback, so he has 3 points.
	Since student 2 has more points, [2,1] is returned.

	Constraints:
	1 <= positive_feedback.length, negative_feedback.length <= 10^4
	1 <= positive_feedback[i].length, negative_feedback[j].length <= 100
	Both positive_feedback[i] and negative_feedback[j] consists of lowercase English letters.
	No word is present in both positive_feedback and negative_feedback.
	n == report.length == student_id.length
	1 <= n <= 10^4
	report[i] consists of lowercase English letters and spaces ' '.
	There is a single space between consecutive words of report[i].
	1 <= report[i].length <= 100
	1 <= student_id[i] <= 10^9
	All the values of student_id[i] are unique.
	1 <= k <= n
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{List.of(1, 2), new String[]{"smart", "brilliant", "studious"}, new String[]{"not"}, new String[]{"this student is studious", "the student is smart"}, new int[]{1, 2}, 2},
				{List.of(2, 1), new String[]{"smart", "brilliant", "studious"}, new String[]{"not"}, new String[]{"this student is not studious", "the student is smart"}, new int[]{1, 2}, 2},
		});
	}

	Set<String> positiveSet, negativeSet;

	public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
		positiveSet = Arrays.stream(positive_feedback).collect(Collectors.toSet());
		negativeSet = Arrays.stream(negative_feedback).collect(Collectors.toSet());
		Map<Integer, Integer> idPointsMap = new HashMap<>();
		for (int i = 0; i < report.length; i++) idPointsMap.merge(student_id[i], getPoints(report[i]), Integer::sum);
		return idPointsMap.entrySet().stream().sorted((e1, e2) -> Objects.equals(e1.getValue(), e2.getValue()) ? e1.getKey() - e2.getKey() : e2.getValue() - e1.getValue()).limit(k).map(Map.Entry::getKey).collect(Collectors.toList());
	}

	int getPoints(String s) {
		int result = 0;
		for (String word : s.split(" "))
			if (positiveSet.contains(word)) result += 3;
			else if (negativeSet.contains(word)) result--;
		return result;
	}
}

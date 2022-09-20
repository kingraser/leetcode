package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Wit
 */
public class RearrangeWordsInASentence {
	/*
	Given a sentence text (A sentence is a string of space-separated words) in the following format:
	First letter is in upper case.
	Each word in text are separated by a single space.
	Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths. If two words have the same length, arrange them in their original order.
	Return the new text following the format shown above.

	Example 1:
	Input: text = "Leetcode is cool"
	Output: "Is cool leetcode"
	Explanation: There are 3 words, "Leetcode" of length 8, "is" of length 2 and "cool" of length 4.
	Output is ordered by length and the new first word starts with capital letter.

	Example 2:
	Input: text = "Keep calm and code on"
	Output: "On and keep calm code"
	Explanation: Output is ordered as follows:
	"On" 2 letters.
	"and" 3 letters.
	"keep" 4 letters in case of tie order by position in original text.
	"calm" 4 letters.
	"code" 4 letters.

	Example 3:
	Input: text = "To be or not to be"
	Output: "To be or to be not"

	Constraints:
	text begins with a capital letter and then contains lowercase letters and single space between words.
	1 <= text.length <= 10^5
	*/

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"Is cool leetcode", "Leetcode is cool"},
				{"On and keep calm code", "Keep calm and code on"},
				{"To be or to be not", "To be or not to be"},
		});
	}

	public String arrangeWords(String text) {
		char[] chars = text.toCharArray();
		StringBuilder sb = new StringBuilder(chars.length);
		List<int[]> words = new ArrayList<>(chars.length);
		chars[0] += 'a' - 'A';
		int left = 0;
		for (int right = 1; right < chars.length; right++)
			if (chars[right] == ' ') {
				words.add(new int[]{left, right - left});
				left = ++right;
			}
		if (left < chars.length) words.add(new int[]{left, chars.length - left});
		words.sort(Comparator.comparingInt(a -> a[1]));
		words.forEach(word -> sb.append(chars, word[0], word[1]).append(' '));
		sb.setCharAt(0, (char) (sb.charAt(0) - 'a' + 'A'));
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
}

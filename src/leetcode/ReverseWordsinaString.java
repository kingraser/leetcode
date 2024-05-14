package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import static leetcode.util.ArrayUtil.reverse;

public class ReverseWordsinaString {
  /*
  Given an input string, reverse the string word by word.
  
  For example,
  Given s = "the sky is blue",
  return "blue is sky the". 
  */

	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{"", " "},
				{"blue is sky the", "the sky is blue"},
				{"1", "1"},
				{"a", " a "},
		});
	}

	public String reverseWords(String s) {
		String[] result = s.trim().split(" +");
		reverse(result);
		return String.join(" ", result);
	}

	static final char EMPTY = ' ';

	public String reverseWordsII(String s) {
		char[] result = s.toCharArray();
		int last = result.length - 1, end = last;
		for (int i = end, wordStart, wordEnd; i >= 0; end -= wordEnd - wordStart + 2) {
			while (i >= 0 && result[i] == EMPTY) i--;
			wordEnd = i--;
			while (i >= 0 && result[i] != EMPTY) i--;
			if ((wordStart = i-- + 1) < 0) break;
			reverse(result, wordStart, end);
		}
		if (end == last) return "";
		reverse(result, end += 2, result.length - 1);
		return new String(result, end, result.length - end);
	}

	public String reverseWordsIII(String s) {
		int rIdx = s.length() - 1, sIdx = 0, size = rIdx;
		char[] result = new char[s.length()], stack = new char[s.length()], array = s.toCharArray();
		for (char c : array)
			if (c == ' ') {
				if (sIdx == 0) continue;
				if (rIdx != size) result[rIdx--] = ' ';
				for (; sIdx > 0; result[rIdx--] = stack[--sIdx]);
				sIdx = 0;
			} else stack[sIdx++] = c;
		if (sIdx != 0) {
			if (rIdx != size) result[rIdx--] = ' ';
			for (; sIdx > 0; result[rIdx--] = stack[--sIdx]);
		}
		return new String(result, ++rIdx, s.length() - rIdx);
	}

}

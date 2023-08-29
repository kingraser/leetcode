package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class CheckIfWordCanBePlacedInCrossword {
	/*
	You are given an m x n matrix board, representing the current state of a crossword puzzle. The crossword contains lowercase English letters (from solved words), ' ' to represent any empty cells, and '#' to represent any blocked cells.
	A word can be placed horizontally (left to right or right to left) or vertically (top to bottom or bottom to top) in the board if:
	It does not occupy a cell containing the character '#'.
	The cell each letter is placed in must either be ' ' (empty) or match the letter already on the board.
	There must not be any empty cells ' ' or other lowercase letters directly left or right of the word if the word was placed horizontally.
	There must not be any empty cells ' ' or other lowercase letters directly above or below the word if the word was placed vertically.
	Given a string word, return true if word can be placed in board, or false otherwise.

	Example 1:
	Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", "c", " "]], word = "abc"
	Output: true
	Explanation: The word "abc" can be placed as shown above (top to bottom).

	Example 2:
	Input: board = [[" ", "#", "a"], [" ", "#", "c"], [" ", "#", "a"]], word = "ac"
	Output: false
	Explanation: It is impossible to place the word because there will always be a space/letter above or below it.

	Example 3:
	Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", " ", "c"]], word = "ca"
	Output: true
	Explanation: The word "ca" can be placed as shown above (right to left).

	Constraints:
	m == board.length
	n == board[i].length
	1 <= m * n <= 2 * 10^5
	board[i][j] will be ' ', '#', or a lowercase English letter.
	1 <= word.length <= max(m, n)
	word will contain only lowercase English letters.
	*/
	@Test
	public void test() {
		TestUtil.testEquals(new Object[][]{
				{true, new char[][]{"# #".toCharArray(), "  #".toCharArray(), "#c ".toCharArray()}, "abc"},
				{false, new char[][]{" #a".toCharArray(), " #c".toCharArray(), " #a".toCharArray()}, "ac"},
				{true, new char[][]{"# #".toCharArray(), "  #".toCharArray(), "# c".toCharArray()}, "ca"},
		});
	}

	public boolean placeWordInCrossword(char[][] board, String word) {
		int rowSize = board.length, colSize = board[0].length, wordLength = word.length(), wordLast = wordLength - 1;
		A:
		for (int row = 0, col, start, direction, c; row < rowSize; row++) {
			for (col = 0, start = 0, direction = 0; col < colSize; col++)
				if ((c = board[row][col]) == '#') {
					if (start != -1 && col - start == wordLength) return true;
					if (colSize - (start = col + 1) < wordLength) continue A;
				} else if (start == -1) continue;
				else if (col - start + 1 > wordLength) start = -1;
				else if (c == ' ') continue;
				else if (direction == 0) {
					if (c == word.charAt(col - start)) {
						if (c != word.charAt(wordLast - col + start)) direction = 1;
					} else if (c == word.charAt(wordLast - col + start)) direction = -1;
					else start = -1;
				} else if (direction == 1) {
					if (c != word.charAt(col - start)) start = -1;
				} else if (c != word.charAt(wordLast - col + start)) start = -1;
			if (start != -1 && colSize - start == wordLength) return true;
		}
		for (int col = 0, row, start, direction, c; col < colSize; col++) {
			for (row = 0, start = 0, direction = 0; row < rowSize; row++)
				if ((c = board[row][col]) == '#') {
					if (start != -1 && row - start == wordLength) return true;
					if (rowSize - (start = row + 1) < wordLength) break;
				} else if (start == -1) continue;
				else if (row - start + 1 > wordLength) start = -1;
				else if (c == ' ') continue;
				else if (direction == 0) {
					if (c == word.charAt(row - start)) {
						if (c != word.charAt(wordLast - row + start)) direction = 1;
					} else if (c == word.charAt(wordLast - row + start)) direction = -1;
					else start = -1;
				} else if (direction == 1) {
					if (c != word.charAt(row - start)) start = -1;
				} else if (c != word.charAt(wordLast - row + start)) start = -1;
			if (start != -1 && rowSize - start == wordLength) return true;
		}
		return false;
	}
}

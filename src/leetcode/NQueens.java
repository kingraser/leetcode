package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class NQueens {
  /*
  The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
  Given an integer n, return all distinct solutions to the n-queens puzzle.  
  Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
  
  For example,
  There exist two distinct solutions to the 4-queens puzzle:  
  [
  [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],
  
  ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
  ]  
  */

  public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    dfs(result, new ArrayList<>(), n);
    return result;
  }

  private void dfs(List<List<String>> result, List<Integer> queens, int size) {
    if (queens.size() == size) {
      result.add(parse(size, queens));
      return;
    }
    for (int col = 0; col < size; col++)
      if (isOk(col, queens)) {
        queens.add(col);
        dfs(result, queens, size);
        queens.remove(queens.size() - 1);
      }
  }

  private List<String> parse(int size, List<Integer> queens) {
    List<String> result = new ArrayList<>(queens.size());
    for (Integer queen : queens) {
      char[] array = new char[size];
      Arrays.fill(array, '.');
      array[queen] = 'Q';
      result.add(new String(array));
    }
    return result;
  }

  public boolean isOk(int col, List<Integer> queens) {
    for (int i = 0, size = queens.size(); i < size; i++)
      if (queens.get(i) == col || Math.abs(i - size) == Math.abs(queens.get(i) - col)) return false;
    return true;
  }

  @Test
  public void test() {
    List<List<String>> expected = Arrays.asList(Arrays.asList(".Q..", "...Q", "Q...", "..Q."),
        Arrays.asList("..Q.", "Q...", "...Q", ".Q.."));
    assertEquals(expected, solveNQueens(4));
  }

}

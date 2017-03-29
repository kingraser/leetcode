package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class NQueens {
  /*
  The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
  Given an integer n, return all distinct solutions to the n-queens puzzle.  
  Each solution contains a distinct board configuration of the n-queens' placement, 
  where 'Q' and '.' both indicate a queen and an empty space respectively.
  
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
    if (queens.size() == size) result.add(parse(size, queens));
    else for (int col = 0; col < size; col++)
      if (isOk(col, queens)) {
        queens.add(col);
        dfs(result, queens, size);
        queens.remove(queens.size() - 1);
      }
  }

  private List<String> parse(int size, List<Integer> queens) {
    return queens.stream().map(queen -> {
      char[] result = new char[size];
      Arrays.fill(result, '.');
      result[queen] = 'Q';
      return new String(result);
    }).collect(Collectors.toList());
  }

  public static boolean isOk(int col, List<Integer> queens) {
    return IntStream.range(0, queens.size())
        .noneMatch(i -> queens.get(i) == col || Math.abs(i - queens.size()) == Math.abs(queens.get(i) - col));
  }

  @Test
  public void test() {
    List<List<String>> expected = Arrays.asList(Arrays.asList(".Q..", "...Q", "Q...", "..Q."),
        Arrays.asList("..Q.", "Q...", "...Q", ".Q.."));
    assertEquals(expected, solveNQueens(4));
  }

}

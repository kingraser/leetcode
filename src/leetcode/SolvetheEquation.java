package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SolvetheEquation {

  /*
  Solve a given equation and return the value of x in the form of string "x=#value". 
  The equation contains only '+', '-' operation, the variable x and its coefficient.
  
  If there is no solution for the equation, return "No solution".  
  If there are infinite solutions for the equation, return "Infinite solutions".  
  If there is exactly one solution for the equation, we ensure that the value of x is an integer.
  
  Example 1:  
  Input: "x+5-3+x=6+x-2"
  Output: "x=2"
  
  Example 2:  
  Input: "x=x"
  Output: "Infinite solutions"
  
  Example 3:  
  Input: "2x=x"
  Output: "x=0"
  
  Example 4:  
  Input: "2x+3x-6x=x+2"
  Output: "x=-1"
  
  Example 5:  
  Input: "x=x+2"
  Output: "No solution"  
  */

  @Test
  public void test() {
    assertEquals("x=1", solveEquation("-x=-1"));

    assertEquals("x=2", solveEquation("x+5-3+x=6+x-2"));
    assertEquals("Infinite solutions", solveEquation("x=x"));
    assertEquals("x=0", solveEquation("2x=x"));
    assertEquals("x=-1", solveEquation("2x+3x-6x=x+2"));
    assertEquals("No solution", solveEquation("x=x+2"));
  }

  public String solveEquation(String equation) {
    String[] A = equation.split("=");
    int B[] = solve(A[0]), C[] = solve(A[1]), a = B[0] - C[0], b = C[1] - B[1];
    return a == 0 ? b == 0 ? "Infinite solutions" : "No solution" : "x=" + b / a;
  }

  private int[] solve(String s) {
    int result[] = new int[2], start = 0, c, l;
    List<String> list = new ArrayList<>();
    for (int idx = 0; idx < s.length(); idx++)
      if ((c = s.charAt(idx)) == '-' || c == '+') list.add(s.substring(start, start = idx));
    if (start != s.length()) list.add(s.substring(start));
    for (String str : list)
      if ((l = str.length()) == 0) continue;
      else if (str.charAt(l - 1) == 'x') result[0] += l == 1 || (c = str.charAt(l - 2)) == '+' ? 1
          : c == '-' ? -1 : Integer.parseInt(str.substring(0, l - 1));
      else result[1] += Integer.parseInt(str);
    return result;
  }

}

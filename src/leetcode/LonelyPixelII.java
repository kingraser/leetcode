package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Test;

public class LonelyPixelII {

  /*
  Given a picture consisting of black and white pixels, and a positive integer N, 
  find the number of black pixels located at some specific row R and column C that align with all the following rules:
    Row R and column C both contain exactly N black pixels.
    For all rows that have a black pixel at column C, they should be exactly the same as row R  
  The picture is represented by a 2D char array consisting of 'B' and 'W', 
  which means black and white pixels respectively.
  
  Example:  
  Input:                                            
  [
    ['W', 'B', 'W', 'B', 'B', 'W'],    
    ['W', 'B', 'W', 'B', 'B', 'W'],    
    ['W', 'B', 'W', 'B', 'B', 'W'],    
    ['W', 'W', 'B', 'W', 'B', 'W']
  ]   
  N = 3
  Output: 6
  Explanation: All the lower case 'B' are the black pixels we need (all 'B's at column 1 and 3).
          0    1    2    3    4    5         column index                                            
  0    [['W', 'b', 'W', 'b', 'B', 'W'],    
  1     ['W', 'b', 'W', 'b', 'B', 'W'],    
  2     ['W', 'b', 'W', 'b', 'B', 'W'],    
  3     ['W', 'W', 'B', 'W', 'B', 'W']]    
  row index
  
  Take 'B' at row R = 0 and column C = 1 as an example:
  Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 
  Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. 
  They are exactly the same as row R = 0.
  
  Note:  
    The range of width and height of the input 2D array is [1,200].  
  */

  @Test
  public void test() {
    assertEquals(6, findBlackPixel(
        Stream.of("WBWBBW", "WBWBBW", "WBWBBW", "WWBWBW").map(s -> s.toCharArray()).toArray(l -> new char[l][]), 3));
  }

  public int findBlackPixel(char[][] picture, int N) {
    int result = 0;
    Map<String, Integer> map = new HashMap<>();
    for (char[] row : picture)
      if (hasNBlackPixelsInARow(row, N)) map.compute(new String(row), (k, v) -> v == null ? 1 : v + 1);
    for (String row : map.keySet())
      if (map.get(row) == N) for (int col = 0; col < picture[0].length; col++)
        if (row.charAt(col) == 'B' && hasNBlackPixelsInACol(picture, col, N)) result += N;
    return result;
  }

  private boolean hasNBlackPixelsInARow(char[] row, int N) {
    int count = 0;
    for (int i = 0; i < row.length; i++)
      if (row[i] == 'B' && ++count > N) return false;
    return count == N;
  }

  private boolean hasNBlackPixelsInACol(char[][] picture, int col, int N) {
    int result = 0;
    for (int row = 0; row < picture.length; row++)
      if (picture[row][col] == 'B' && ++result > N) return false;
    return result == N;
  }
}

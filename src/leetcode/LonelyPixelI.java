package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.junit.Test;

public class LonelyPixelI {

  /*
  Given a picture consisting of black and white pixels, find the number of black lonely pixels.  
  The picture is represented by a 2D char array consisting of 'B' and 'W', 
  which means black and white pixels respectively.  
  A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.
  
  Example:  
  Input: 
  [['W', 'W', 'B'],
   ['W', 'B', 'W'],
   ['B', 'W', 'W']]
  
  Output: 3
  Explanation: All the three 'B's are black lonely pixels.
  
  Note:  
    The range of width and height of the input 2D array is [1,500].  
  */

  @Test
  public void test() {
    assertEquals(3,
        findLonelyPixel(Stream.of("WWB", "WBW", "BWW").map(s -> s.toCharArray()).toArray(l -> new char[l][])));
  }

  public int findLonelyPixel(char[][] picture) {
    int count = 0, rowCount[][] = new int[picture.length][2], colCount[] = new int[picture[0].length];
    for (int row = 0; row < picture.length; row++)
      for (int col = 0; col < picture[0].length; col++)
        if (picture[row][col] == 'B') {
          rowCount[row][0]++;
          rowCount[row][1] = col;
          colCount[col]++;
        }
    for (int row = 0; row < picture.length; row++)
      if (rowCount[row][0] == 1 && colCount[rowCount[row][1]] == 1) count++;
    return count;
  }

}

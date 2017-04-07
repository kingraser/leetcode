package leetcode;

import static leetcode.BattleshipsinaBoard.DIRS;
import static leetcode.TheMaze.canRoll;
import static org.junit.Assert.assertEquals;

import java.util.PriorityQueue;

import org.junit.Test;

public class TheMazeII {

  /*
  There is a ball in a maze with empty spaces and walls. 
  The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 
  When the ball stops, it could choose the next direction.  
  Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. 
  The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). 
  If the ball cannot stop at the destination, return -1.  
  The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. 
  You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
  
  Example 1  
  Input 1: a maze represented by a 2D array
  
  0 0 1 0 0
  0 0 0 0 0
  0 0 0 1 0
  1 1 0 1 1
  0 0 0 0 0
  
  Input 2: start coordinate (rowStart, colStart) = (0, 4)
  Input 3: destination coordinate (rowDest, colDest) = (4, 4)  
  Output: 12
  Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
               The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
  
  Example 2  
  Input 1: a maze represented by a 2D array
  
  0 0 1 0 0
  0 0 0 0 0
  0 0 0 1 0
  1 1 0 1 1
  0 0 0 0 0
  
  Input 2: start coordinate (rowStart, colStart) = (0, 4)
  Input 3: destination coordinate (rowDest, colDest) = (3, 2)  
  Output: -1
  Explanation: There is no way for the ball to stop at the destination.
  
  Note:  
      There is only one ball and one destination in the maze.
      Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
      The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
      The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
  */

  @Test
  public void test() {
    int[][] maze = new int[][] { { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0 }, { 1, 1, 0, 1, 1 },
        { 0, 0, 0, 0, 0 } };
    assertEquals(12, shortestDistance(maze, new int[] { 0, 4 }, new int[] { 4, 4 }));
    assertEquals(-1, shortestDistance(maze, new int[] { 0, 4 }, new int[] { 3, 2 }));
  }

  public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    int result = Integer.MAX_VALUE;
    boolean[][] reached = new boolean[maze.length][maze[0].length];
    PriorityQueue<Position> queue = new PriorityQueue<>();
    for (queue.add(new Position(start[0], start[1], 0)); !queue.isEmpty();) {
      Position position = queue.poll(), next;
      reached[position.row][position.column] = true;
      for (int[] direction : DIRS)
        if (reached[(next = roll(maze, position, direction)).row][next.column]) continue;
        else if (next.row == destination[0] && next.column == destination[1]) result = Math.min(result, next.distance);
        else if (next.distance < result) queue.add(next);
    }
    return result == Integer.MAX_VALUE ? -1 : result;
  }

  private Position roll(int[][] maze, Position start, int[] direction) {
    Position next = new Position(start);
    while (canRoll(maze, next.row + direction[0], next.column + direction[1])) {
      next.row += direction[0];
      next.column += direction[1];
      next.distance += 1;
    }
    return next;
  }

  class Position implements Comparable<Position> {
    public int row, column, distance;

    public Position(Position position) {
      this(position.row, position.column, position.distance);
    }

    public Position(int row, int column, int distance) {
      this.row = row;
      this.column = column;
      this.distance = distance;
    }

    @Override
    public int compareTo(Position position) {
      return distance - position.distance;
    }
  }
}

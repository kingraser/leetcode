package leetcode;

import static leetcode.TheMaze.canRoll;
import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

public class TheMazeIII {

  /*
  There is a ball in a maze with empty spaces and walls. 
  The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. 
  When the ball stops, it could choose the next direction. There is also a hole in this maze. 
  The ball will drop into the hole if it rolls on to the hole.  
  Given the ball position, the hole position and the maze, 
  your job is to find out how the ball could drop into the hole by moving shortest distance in the maze. 
  The distance is defined by the number of empty spaces the ball go through from the start position (exclude) to the hole (include). 
  Output the moving directions by using 'u', 'd', 'l' and 'r'. 
  Since there may have several different shortest ways, you should output the lexicographically smallest way. 
  If the ball cannot reach the hole, output "impossible".  
  The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. 
  You may assume that the borders of the maze are all walls. 
  The ball and hole coordinates are represented by row and column indexes.
  
  Example 1  
  Input 1: a maze represented by a 2D array
  
  0 0 0 0 0
  1 1 0 0 1
  0 0 0 0 0
  0 1 0 0 1
  0 1 0 0 0
  
  Input 2: ball coordinate (rowBall, colBall) = (4, 3)
  Input 3: hole coordinate (rowHole, colHole) = (0, 1)  
  Output: "lul"
  Explanation: There are two shortest ways for the ball to drop into the hole.
  The first way is left -> up -> left, represented by "lul".
  The second way is up -> left, represented by 'ul'.
  Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".
  
  Example 2  
  Input 1: a maze represented by a 2D array
  
  0 0 0 0 0
  1 1 0 0 1
  0 0 0 0 0
  0 1 0 0 1
  0 1 0 0 0
  
  Input 2: ball coordinate (rowBall, colBall) = (4, 3)
  Input 3: hole coordinate (rowHole, colHole) = (3, 0)
  Output: "impossible"
  Explanation: The ball cannot reach the hole.
  
  Note:  
      There are only one ball and one hole in the maze.
      The ball and hole will only exist in the empty space, and they will not at the same position initially.
      The given maze doesn't contain border (like the red rectangle in the example pictures), but you should assume the border of the maze are all walls.
      The maze contains at least 2 empty spaces, and the length and width of the maze won't exceed 30.
  */

  @Test
  public void test() {
    int[][] maze = new int[][] { { 0, 0, 0, 0, 0 }, { 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1 },
        { 0, 1, 0, 0, 0 } };
    assertEquals("lul", findShortestWay(maze, new int[] { 4, 3 }, new int[] { 0, 1 }));
    assertEquals("impossible", findShortestWay(maze, new int[] { 4, 3 }, new int[] { 3, 0 }));
  }

  public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
    Position start = new Position(ball[0], ball[1], 0, ""), result = new Position(0, 0, Integer.MAX_VALUE, "");
    boolean[][] reached = new boolean[maze.length][maze[0].length];
    PriorityQueue<Position> queue = new PriorityQueue<>();
    for (queue.add(start); !queue.isEmpty();) {
      Position position = queue.poll(), next;
      reached[position.row][position.column] = true;
      for (Entry<String, int[]> direction : DIRECTION_MAP.entrySet())
        if (reached[(next = roll(maze, hole, position, direction)).row][next.column]) continue;
        else if (next.row == hole[0] && next.column == hole[1] && (next.distance < result.distance
            || (next.distance == result.distance && next.path.compareTo(result.path) < 0))) {
          result.distance = next.distance;
          result.path = next.path;
        } else if (next.distance < result.distance) queue.add(next);
    }
    return result.distance == Integer.MAX_VALUE ? "impossible" : result.path;
  }

  private static final Map<String, int[]> DIRECTION_MAP = ImmutableMap.of("u", new int[] { -1, 0 }, "d",
      new int[] { 1, 0 }, "l", new int[] { 0, -1 }, "r", new int[] { 0, 1 });

  private Position roll(int[][] maze, int[] hole, Position start, Entry<String, int[]> direction) {
    Position next = new Position(start);
    while ((next.row != hole[0] || next.column != hole[1])
        && canRoll(maze, next.row + direction.getValue()[0], next.column + direction.getValue()[1])) {
      next.row += direction.getValue()[0];
      next.column += direction.getValue()[1];
      next.distance += 1;
    }
    if (next.distance > start.distance) next.path += direction.getKey();
    return next;
  }

  class Position implements Comparable<Position> {
    public int row, column, distance;
    public String path;

    public Position(Position position) {
      this(position.row, position.column, position.distance, position.path);
    }

    public Position(int row, int column, int distance, String path) {
      this.row = row;
      this.column = column;
      this.distance = distance;
      this.path = path;
    }

    @Override
    public int compareTo(Position position) {
      return distance == position.distance ? path.compareTo(position.path) : distance - position.distance;
    }
  }
}

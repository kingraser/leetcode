package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

public class DesignSnakeGame {

  /*
  Design a Snake game that is played on a device with screen size = width x height. 
  Play the game online if you are not familiar with the game.  
  The snake is initially positioned at the top left corner (0,0) with length = 1 unit.  
  You are given a list of food's positions in row-column order. 
  When a snake eats the food, its length and the game's score both increase by 1.  
  Each food appears one by one on the screen. 
  For example, the second food will not appear until the first food was eaten by the snake.  
  When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
  
  Example:  
  Given width = 3, height = 2, and food = [[1,2],[0,1]].  
  Snake snake = new Snake(width, height, food);  
  Initially the snake appears at position (0,0) and the food at (1,2).
  
  |S| | |
  | | |F|
  
  snake.move("R"); -> Returns 0
  
  | |S| |
  | | |F|
  
  snake.move("D"); -> Returns 0
  
  | | | |
  | |S|F|
  
  snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
  
  | |F| |
  | |S|S|
  
  snake.move("U"); -> Returns 1
  
  | |F|S|
  | | |S|
  
  snake.move("L"); -> Returns 2 (Snake eats the second food)
  
  | |S|S|
  | | |S|
  
  snake.move("U"); -> Returns -1 (Game over because snake collides with border)
  */

  @Test
  public void test() {
    SnakeGame snake = new SnakeGame(3, 2, new int[][] { { 1, 2 }, { 0, 1 } });
    assertEquals(0, snake.move("R"));
    assertEquals(0, snake.move("D"));
    assertEquals(1, snake.move("R"));
    assertEquals(1, snake.move("U"));
    assertEquals(2, snake.move("L"));
    assertEquals(-1, snake.move("U"));
  }

  public class SnakeGame {
    public int move(String direction) {
      if (score == -1) return score;
      set.remove(body.peekLast());
      rowHead = body.peekFirst() >> 16;
      colHead = body.peekFirst() & 0xFFFF;
      DIRECTION_MAP.get(direction).run();
      if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width
          || !set.add(head = (rowHead << 16) | colHead))
        return score = -1;
      body.addFirst(head);
      if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
        set.add(body.peekLast());
        foodIndex++;
        return ++score;
      }
      body.pollLast();
      return score;
    }

    Set<Integer> set = Stream.of(0).collect(Collectors.toSet());
    Deque<Integer> body = new ArrayDeque<>(set);
    int width, height, food[][], score = 0, foodIndex = 0, rowHead, colHead, head;

    public SnakeGame(int width, int height, int[][] food) {
      this.width = width;
      this.height = height;
      this.food = food;
    }

    final Map<String, Runnable> DIRECTION_MAP = ImmutableMap.of("U", () -> rowHead--, "D", () -> rowHead++, "L",
        () -> colHead--, "R", () -> colHead++);
  }
}

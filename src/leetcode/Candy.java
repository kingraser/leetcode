package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class Candy {
  /*
  There are N children standing in a line. Each child is assigned a rating value.
  
  You are giving candies to these children subjected to the following requirements:
  
  Each child must have at least one candy.
  Children with a higher rating get more candies than their neighbors.
  
  What is the minimum candies you must give? 
  */

  @Test
  public void test() {
    assertEquals(9, candy(new int[] { 1, 3, 5, 2, 4 }));
  }

  public int candy(int[] ratings) {
    int[] candy = new int[ratings.length];
    Arrays.fill(candy, 1);
    for (int i = 1; i < ratings.length; i++)
      if (ratings[i] > ratings[i - 1]) candy[i] = candy[i - 1] + 1;
    for (int i = ratings.length - 2; i >= 0; i--)
      if (ratings[i] > ratings[i + 1] && candy[i] <= candy[i + 1]) candy[i] = candy[i + 1] + 1;
    return Arrays.stream(candy).sum();
  }
}

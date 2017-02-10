/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年10月14日<p>
//-------------------------------------------------------
public class NimGame {

  /*
  You are playing the following Nim Game with your friend: 
  There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. 
  The one who removes the last stone will be the winner. You will take the first turn to remove the stones.    
  Both of you are very clever and have optimal strategies for the game. 
  Write a function to determine whether you can win the game given the number of stones in the heap.    
  For example, if there are 4 stones in the heap, then you will never win the game: 
  no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend. 
  */

  /*
  It is obvious that 1,2,3 first one will win, 4 second one will win
  5,6,7 will enter first win case
  8 the second one will win
  If move 1...n,then times of n are second win cases
  */

  @Test
  public void test() {
    Stream.of(1, 2, 3).forEach(i -> assertTrue(canWinNim(i)));
    assertFalse(canWinNim(4));
  }

  public boolean canWinNim(int n) {
    return n % 4 != 0;
  }

}

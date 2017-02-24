package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FindtheCelebrity {

  /*
  Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. 
  The definition of a celebrity is that all the other n - 1people know him/her but he/she does not know any of them.
  Now you want to find out who the celebrity is or verify that there is not one. 
  The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. 
  You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
  You are given a helper function bool knows(a, b) which tells you whether A knows B. 
  Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
  Note: There will be exactly one celebrity if he/she is in the party. 
  Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
  */

  Party party;

  @Test
  public void test() {
    party = new Party(new boolean[][] { { true, false, false }, { true, true, true }, { true, false, true } });
    assertEquals(0, findCelebrity(3));
  }

  public int findCelebrity(int n) {
    int candidate = 0;
    for (int i = 1; i < n; i++)
      if (party.knows(candidate, i)) candidate = i;
    for (int i = 0; i < n; i++)
      if (i != candidate && (party.knows(candidate, i) || !party.knows(i, candidate))) return -1;
    return candidate;
  }

  class Party {
    private boolean[][] map;

    public Party(boolean[][] map) {
      this.map = map;
    }

    /**
     * @param candidate
     * @param somebody
     * @return Whether candidate knows somebody
     */
    public boolean knows(int candidate, int somebody) {
      return map[candidate][somebody];
    }
  }

}

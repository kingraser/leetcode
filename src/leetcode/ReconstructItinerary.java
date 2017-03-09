package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.junit.Test;

public class ReconstructItinerary {

  /*
  Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], 
  reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, 
  the itinerary must begin with JFK.
  
  Note:
  
  If there are multiple valid itineraries, 
  you should return the itinerary that has the smallest lexical order when read as a single string. 
  For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
  All airports are represented by three capital letters (IATA code).
  You may assume all tickets form at least one valid itinerary.
  
  Example 1: 
  tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
  Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
  
  Example 2:
  tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
  Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
  */

  @Test
  public void test() {
    assertEquals(Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC"),
        findItinerary(new String[][] { { "MUC", "LHR" }, { "JFK", "MUC" }, { "SFO", "SJC" }, { "LHR", "SFO" } }));
    assertEquals(Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO"), findItinerary(
        new String[][] { { "JFK", "SFO" }, { "JFK", "ATL" }, { "SFO", "ATL" }, { "ATL", "JFK" }, { "ATL", "SFO" } }));
    assertEquals(Arrays.asList("JFK", "NRT", "JFK", "KUL"),
        findItinerary(new String[][] { { "JFK", "KUL" }, { "JFK", "NRT" }, { "NRT", "JFK" } }));
  }

  public List<String> findItinerary(String[][] tickets) {
    Map<String, PriorityQueue<String>> map = new HashMap<>();
    LinkedList<String> route = new LinkedList<>();
    Arrays.stream(tickets).forEach(s -> map.computeIfAbsent(s[0], k -> new PriorityQueue<>()).add(s[1]));
    visit("JFK", map, route);
    return route;
  }

  void visit(String airport, Map<String, PriorityQueue<String>> map, LinkedList<String> route) {
    for (PriorityQueue<String> queue = map.get(airport); queue != null && !queue.isEmpty();)
      visit(queue.poll(), map, route);
    route.addFirst(airport);
  }

}

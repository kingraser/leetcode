package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Test;

public class WordLadderII {
  /*
  Given two words (beginWord and endWord), and a dictionary's word list, 
  find all shortest transformation sequence(s) from beginWord to endWord, such that:
  
  Only one letter can be changed at a time
  Each intermediate word must exist in the word list
  
  For example,
  
  Given:
  beginWord = "hit" endWord = "cog"
  wordList = ["hot","dot","dog","lot","log"]
  
  Return    
    [
      ["hit","hot","dot","dog","cog"],
      ["hit","hot","lot","log","cog"]
    ]
  */

  @Test
  public void test() {
    List<List<String>> expected = List.of(Arrays.asList("a", "c"));
    Set<String> dict = new HashSet<>(Set.of("a", "b", "c"));
    assertEquals(expected, findLadders("a", "c", dict));
    expected = Arrays.asList(Arrays.asList("red", "ted", "tad", "tax"), Arrays.asList("red", "ted", "tex", "tax"),
        Arrays.asList("red", "rex", "tex", "tax"));
    dict = new HashSet<>(Set.of("ted", "tex", "red", "tax", "tad", "den", "rex", "pee"));
    assertEquals(new HashSet<>(expected), new HashSet<>(findLadders("red", "tax", dict)));
  }

  public List<List<String>> findLadders(String begin, String end, Set<String> set) {
    Map<String, List<String>> path = new HashMap<>();
    Set<String> result = bfs(new HashSet<>(Set.of(begin)), new HashSet<>(Set.of(end)), set, path, new HashSet<>());//generate the ladder
    return dfs(result, path, end);//find path
  }

  public Set<String> bfs(Set<String> start, Set<String> end, Set<String> set, Map<String, List<String>> map,
      Set<String> result) {
    if (start.size() > end.size()) return bfs(end, start, set, map, result);//search from both sides
    if (start.isEmpty()) return result;//no path
    Stream.of(start, end).forEach(set::removeAll);//insure no circle
    Set<String> next = new HashSet<>();//nodes in next level 
    for (String str : start) {
      char[] head = str.toCharArray();
      for (char i = 0, origin; i < str.length(); head[i++] = origin)
        for (origin = head[i], head[i] = 'a'; head[i] <= 'z'; head[i]++) {
          if (head[i] == origin) continue;
          String word = new String(head);
          if (end.contains(word)) log(word, str, result, map);//record the node level information
          else if (set.contains(word)) log(word, str, next, map);
        }
    }
    return result.isEmpty() ? bfs(next, end, set, map, result) : result;//stop when there is at least a path
  }

  private void log(String word, String parent, Set<String> set, Map<String, List<String>> map) {
    set.add(word);
    map.computeIfAbsent(word, k -> new ArrayList<>()).add(parent);//ancestor list
  }

  private List<List<String>> dfs(Set<String> set, Map<String, List<String>> paths, String endWord) {
    List<List<String>> result = new ArrayList<>();
    for (String s : set) {
      List<List<String>> lists = dfs(s, paths), head = new ArrayList<>(), end = new ArrayList<>();
      for (List<String> list : lists)
        if (endWord.equals(list.getFirst())) end.add(list);
        else head.add(list);
      result.addAll(end.isEmpty() ? head : join(head, end));//generate all possible paths
    }
    return result;
  }

  private List<List<String>> join(List<List<String>> heads, List<List<String>> ends) {
    List<List<String>> result = new ArrayList<>();
    for (List<String> head : heads)
      for (List<String> end : ends)
        result.add(joinString(head, end));
    return result;
  }

  private List<String> joinString(List<String> head, List<String> ends) {
    List<String> result = new ArrayList<>(head);
    for (int i = ends.size() - 2; i > -1; i--) result.add(ends.get(i));
    return result;
  }

  private List<List<String>> dfs(String word, Map<String, List<String>> paths) {
    List<List<String>> result = new ArrayList<>();
    if (!paths.containsKey(word)) result.add(new ArrayList<>(List.of(word)));
    else for (String parent : paths.get(word)) {
      List<List<String>> lists = dfs(parent, paths);
      lists.forEach(l -> l.add(word));//get head/end parts of the ladder
      result.addAll(lists);
    }
    return result;
  }
}

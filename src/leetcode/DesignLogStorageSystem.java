package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class DesignLogStorageSystem {

  /*
  You are given several logs that each log contains a unique id and timestamp. 
  Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, 
  for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
  
  Design a log storage system to implement the following functions:  
  void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.
  int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.
  
  Example 1:  
  put(1, "2017:01:01:23:59:59");
  put(2, "2017:01:01:22:59:59");
  put(3, "2016:01:01:00:00:00");
  retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
  retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
  
  Note:  
    There will be at most 300 operations of Put or Retrieve.
    Year ranges from [2000,2017]. Hour ranges from [00,23].
    Output for Retrieve has no order required.  
  */

  @Test
  public void test() {
    LogSystem logSystem = new LogSystem();
    logSystem.put(1, "2017:01:01:23:59:59");
    logSystem.put(2, "2017:01:01:22:59:59");
    logSystem.put(3, "2016:01:01:00:00:00");
    assertEquals(Arrays.asList(1, 2, 3), logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year"));
    assertEquals(Arrays.asList(1, 2), logSystem.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour"));
  }

  class LogSystem {
    int[] shifts = new int[] { 26, 22, 17, 12, 6, 0 };
    List<int[]> logs = new ArrayList<>();

    public void put(int id, String timestamp) {
      logs.add(new int[] { id, getTimestamp(timestamp, 6) });
    }

    private int getTimestamp(String timestamp, int gra) {
      int result = 0;
      String[] times = timestamp.split(":");
      result |= (Integer.parseInt(times[0]) - 2000) << 26;
      for (int i = 1; i < gra; i++)
        result |= Integer.parseInt(times[i]) << shifts[i];
      return result;
    }

    public List<Integer> retrieve(String s, String e, String gra) {
      int graInt = getGra(gra), shift = shifts[graInt - 1], start = getTimestamp(s, graInt) >> shift,
          end = getTimestamp(e, graInt) >> shift;
      return logs.stream().filter(log -> {
        int i = log[1] >> shift;
        return i >= start && i <= end;
      }).map(log -> log[0]).collect(Collectors.toList());
    }

    private int getGra(String gra) {
      char f = gra.charAt(0), s = gra.charAt(1);
      if (f == 'Y') return 1;
      if (f == 'D') return 3;
      if (f == 'H') return 4;
      if (f == 'S') return 6;
      if (s == 'o') return 2;
      return 5;
    }
  }

}

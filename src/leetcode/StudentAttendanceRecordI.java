package leetcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StudentAttendanceRecordI {

  /*
  You are given a string representing an attendance record for a student. 
  The record only contains the following three characters:
  
    'A' : Absent.
    'L' : Late.
    'P' : Present.
  
  A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
  You need to return whether the student could be rewarded according to his attendance record.
  
  Example 1:  
  Input: "PPALLP"
  Output: True
  
  Example 2:  
  Input: "PPALLL"
  Output: False  
  */

  @Test
  public void test() {
    assertTrue(checkRecord("PPALLP"));
    assertFalse(checkRecord("PPALLL"));
  }

  public boolean checkRecord(String s) {
    int absent = 0, late = 0;
    for (char c : s.toCharArray()) {
      if (c == 'L') late++;
      else {
        late = 0;
        if (c == 'A') absent++;
      }
      if (absent > 1 || late > 2) return false;
    }
    return true;
  }

}

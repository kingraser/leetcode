/*
 * $Id$
 *
 * Copyright (c) 2015 Sogou.com. All Rights Reserved.
 */
package leetcode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

//--------------------- Change Logs----------------------
//@author wangwenlong Initial Created at 2016年8月23日;
//-------------------------------------------------------
public class RansomNote {

  /*
  Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters from  all  the  magazines,  write  a  function  that  will  return  true  if  the  ransom   note  can  be  constructed  from  the  magazines ;  otherwise,  it  will  return  false.   
  
  Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.
  
  Note:
  You may assume that both strings contain only lowercase letters.
  
  canConstruct("a", "b") -> false
  canConstruct("aa", "ab") -> false
  canConstruct("aa", "aab") -> true  
  */

  @Test
  public void test() {
    assertTrue(canConstruct("aa", "aab"));
  }

  public boolean canConstruct(String ransomNote, String magazine) {
    int[] counts = new int[128];
    magazine.chars().forEach(c -> counts[c]++);
    return ransomNote.chars().allMatch(c -> counts[c]-- > 0);
  }

}

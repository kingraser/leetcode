package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class EncodeandDecodeStrings {

  /*
  Design an algorithm to encode a list of strings to a string. 
  The encoded string is then sent over the network and is decoded back to the original list of strings.
  
  Machine 1 (sender) has the function:
  string encode(vector<string> strs) {
    // ... your code
    return encoded_string;
  }
  
  Machine 2 (receiver) has the function:
  vector<string> decode(string s) {
    //... your code
    return strs;
  }
   
  So Machine 1 does:  
  string encoded_string = encode(strs);
  
  and Machine 2 does:
  vector<string> strs2 = decode(encoded_string);
  
  strs2 in Machine 2 should be the same as strs in Machine 1.
  Implement the encode and decode methods.
  
  Note:  
    The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
    Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
    Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
  */

  @Test
  public void test() {
    List<String> input = Arrays.asList("a", "bc", "def");
    assertEquals(input, decode(encode(input)));
  }

  public String encode(List<String> strs) {
    return String.join("", strs.stream().map(s -> s.length() + "a" + s).collect(Collectors.toList()));
  }

  public List<String> decode(String s) {
    List<String> result = new ArrayList<>();
    for (int i = 0, start; i < s.length();)
      result.add(s.substring(start = s.indexOf('a', i) + 1, i = start + Integer.valueOf(s.substring(i, --start))));
    return result;
  }

}

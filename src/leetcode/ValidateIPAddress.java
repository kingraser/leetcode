package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ValidateIPAddress {

  /*
  In this problem, your job to write a function to check whether a input string is a valid IPv4 address or IPv6 address or neither.  
  IPv4 addresses are canonically represented in dot-decimal notation, 
  which consists of four decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;  
  Besides, you need to keep in mind that leading zeros in the IPv4 is illegal. For example, the address 172.16.254.01 is illegal.  
  IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. 
  The groups are separated by colons (":"). 
  For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a legal one. 
  Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, 
  so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).  
  However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. 
  For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.  
  Besides, you need to keep in mind that extra leading zeros in the IPv6 is also illegal. 
  For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is also illegal.
  
  Note: You could assume there is no extra space in the test cases and there may some special characters in the input string.
  
  Example 1:  
  Input: "172.16.254.1"  
  Output: "IPv4"  
  Explanation: This is a valid IPv4 address, return "IPv4".
  
  Example 2:  
  Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"  
  Output: "IPv6"  
  Explanation: This is a valid IPv6 address, return "IPv6".
  
  Example 3:  
  Input: "256.256.256.256"  
  Output: "Neither"  
  Explanation: This is neither a IPv4 address nor a IPv6 address. 
  */

  @Test
  public void test() {
    assertEquals("Neither", validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
    assertEquals("IPv4", validIPAddress("172.16.254.1"));
    assertEquals("IPv6", validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
    assertEquals("Neither", validIPAddress("256.256.256.256"));
  }

  public String validIPAddress(String ip) {
    return isV4(ip) ? "IPv4" : isV6(ip) ? "IPv6" : "Neither";
  }

  private boolean isV4(String ip) {
    if (ip == null || ip.length() < 7 || ip.length() > 15) return false;
    String[] array = ip.split("\\.");
    if (array.length != 4 || getLen(array) != ip.length()) return false;
    return Arrays.stream(array).allMatch(s -> isV4Part(s));
  }

  private boolean isV4Part(String s) {
    if (s == null || s.length() < 1 || s.length() > 3) return false;
    for (int i = 0, val = 0, c, min = 1; i < s.length(); min *= 10, i++)
      if ((c = s.charAt(i) - '0') < 0 || c > 9 || ((val = val * 10 + c) < min && i > 0) || val > 255) return false;
    return true;
  }

  private boolean isV6(String ip) {
    if (ip == null || ip.length() < 15 || ip.length() > 39) return false;
    String[] array = ip.split(":");
    if (array.length != 8 || getLen(array) != ip.length()) return false;
    return Arrays.stream(array).allMatch(s -> isV6Part(s));
  }

  private int getLen(String[] array) {
    return array.length - 1 + Arrays.stream(array).mapToInt(s -> s.length()).sum();
  }

  private boolean isV6Part(String s) {
    if (s.length() < 1 || s.length() > 4) return false;
    return s.chars().allMatch(c -> isHex(c));
  }

  private boolean isHex(int c) {
    return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
  }

}

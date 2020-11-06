package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Wit
 */
public class ToLowerCase {
    /*
    Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.

    Example 1:
    Input: "Hello"
    Output: "hello"

    Example 2:
    Input: "here"
    Output: "here"

    Example 3:
    Input: "LOVELY"
    Output: "lovely"
    */
    public String toLowerCase(String str) {
        return str.toLowerCase();
    }

    @Test
    public void test() {
        Assert.assertEquals("hello", toLowerCase("Hello"));
        Assert.assertEquals("here", toLowerCase("here"));
        Assert.assertEquals("lovely", toLowerCase("LOVELY"));
    }
}

/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import org.junit.Assert;
import org.junit.Test;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月14日<p>
//-------------------------------------------------------
public class ValidPalindrome {

    /*
    Given a string, determine if it is a palindrome, 
    considering only alphanumeric characters and ignoring cases.
    
    For example,
    "A man, a plan, a canal: Panama" is a palindrome.
    "race a car" is not a palindrome. 
     */

    public boolean isPalindrome(String s) {
        for (int l = 0, r = (s = s.toLowerCase()).length() - 1; l < r;)
            if (!Character.isLetterOrDigit(s.charAt(l))) l++;
            else if (!Character.isLetterOrDigit(s.charAt(r))) r--;
            else if (s.charAt(l++) != s.charAt(r--)) return false;
        return true;
    }

    @Test
    public void test() {
        Assert.assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
        Assert.assertFalse(isPalindrome("race a car"));
    }

}

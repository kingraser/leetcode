/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package leetcode;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

//--------------------- Change Logs----------------------
// <p>@author wit Initial Created at 2015年9月17日<p>
//-------------------------------------------------------
public class TextJustification {

    /*
    Given an array of words and a length L, 
    format the text such that each line has exactly L characters and is fully (left and right) justified.
    You should pack your words in a greedy approach; 
    that is, pack as many words as you can in each line. 
    Pad extra spaces ' ' when necessary so that each line has exactly L characters.
    Extra spaces between words should be distributed as evenly as possible. 
    If the number of spaces on a line do not divide evenly between words, 
    the empty slots on the left will be assigned more spaces than the slots on the right.
    
    For the last line of text, 
    it should be left justified and no extra space is inserted between words.
    
    For example,
    words: ["This", "is", "an", "example", "of", "text", "justification."]
    L: 16. 
    
    Return the formatted lines as:
    
        [
            "This    is    an",
            "example  of text",
            "justification.  "
        ]
    
    */

    public List<String> fullJustify(String[] W, int m) {
        LinkedList<String> r = Lists.newLinkedList(), q = Lists.newLinkedList();
        for (int i = 0, l = 0, L = W.length; i < L; r.add(get(q, m, new StringBuilder(q.poll()), l, i == L)), l = 0)
            for (; i < L && m - l - q.size() - W[i].length() > -1; q.add(W[i]), l += W[i++].length());
        return r;

    }

    //a间隔数 b间隔的空格数 c额外的空格数 f最后行标志,true=>最后一行 m=>max
    private String get(LinkedList<String> q, int m, StringBuilder sb, int l, boolean f) {        
        for (int a = q.size(), b = a == 0 ? 0 : f ? 1 : ((m - l) / a), c = f || a == 0 ? 0 : (m - l) % a; !q.isEmpty(); sb.append(q.poll()), c--)
            for (int i = 0; i < (c > 0 ? b + 1 : b); sb.append(" "), i++);
        for (int i = sb.length(); i < m; sb.append(" "), i++);
        return sb.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals(Lists.newArrayList("This    is    an", "example  of text", "justification.  "),
                fullJustify(new String[] { "This", "is", "an", "example", "of", "text", "justification." }, 16));
    }

}

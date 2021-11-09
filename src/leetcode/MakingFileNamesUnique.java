package leetcode;

import leetcode.util.ArrayUtil;
import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wit
 */
public class MakingFileNamesUnique {
    /*
    Given an array of strings names of size n. You will create n folders in your file system such that, at the ith minute, you will create a folder with the name names[i].
    Since two files cannot have the same name, if you enter a folder name which is previously used, the system will have a suffix addition to its name in the form of (k), where, k is the smallest positive integer such that the obtained name remains unique.
    Return an array of strings of length n where ans[i] is the actual name the system will assign to the ith folder when you create it.

    Example 1:
    Input: names = ["pes","fifa","gta","pes(2019)"]
    Output: ["pes","fifa","gta","pes(2019)"]
    Explanation: Let's see how the file system creates folder names:
    "pes" --> not assigned before, remains "pes"
    "fifa" --> not assigned before, remains "fifa"
    "gta" --> not assigned before, remains "gta"
    "pes(2019)" --> not assigned before, remains "pes(2019)"

    Example 2:
    Input: names = ["gta","gta(1)","gta","avalon"]
    Output: ["gta","gta(1)","gta(2)","avalon"]
    Explanation: Let's see how the file system creates folder names:
    "gta" --> not assigned before, remains "gta"
    "gta(1)" --> not assigned before, remains "gta(1)"
    "gta" --> the name is reserved, system adds (k), since "gta(1)" is also reserved, systems put k = 2. it becomes "gta(2)"
    "avalon" --> not assigned before, remains "avalon"

    Example 3:
    Input: names = ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"]
    Output: ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece(4)"]
    Explanation: When the last folder is created, the smallest positive valid k is 4, and it becomes "onepiece(4)".

    Example 4:
    Input: names = ["wano","wano","wano","wano"]
    Output: ["wano","wano(1)","wano(2)","wano(3)"]
    Explanation: Just increase the value of k each time you create folder "wano".

    Example 5:
    Input: names = ["kaido","kaido(1)","kaido","kaido(1)"]
    Output: ["kaido","kaido(1)","kaido(2)","kaido(1)(1)"]
    Explanation: Please note that system adds the suffix (k) to current name even it contained the same suffix before.

    Constraints:
    1 <= names.length <= 5 * 10^4
    1 <= names[i].length <= 20
    names[i] consists of lower case English letters, digits and/or round brackets.
    */

    @Test
    public void test() {
        TestUtil.testArrayEquals(new Object[][]{
                {ArrayUtil.of("pes", "fifa", "gta", "pes(2019)"), ArrayUtil.of("pes", "fifa", "gta", "pes(2019)")},
                {ArrayUtil.of("gta", "gta(1)", "gta(2)", "avalon"), ArrayUtil.of("gta", "gta(1)", "gta", "avalon")},
                {ArrayUtil.of("onepiece", "onepiece(1)", "onepiece(2)", "onepiece(3)", "onepiece(4)"), ArrayUtil.of("onepiece", "onepiece(1)", "onepiece(2)", "onepiece(3)", "onepiece")},
                {ArrayUtil.of("wano", "wano(1)", "wano(2)", "wano(3)"), ArrayUtil.of("wano", "wano", "wano", "wano")},
                {ArrayUtil.of("kaido", "kaido(1)", "kaido(2)", "kaido(1)(1)"), ArrayUtil.of("kaido", "kaido(1)", "kaido", "kaido(1)")}
        });
    }

    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>(names.length << 1);
        Integer val;
        for (int i = 0; i < names.length; map.put(names[i++], 1)) {
            if ((val = map.get(names[i])) == null) continue;
            Word word = new Word(names[i], val);
            while (map.containsKey(word.toString())) word.inc();
            map.put(names[i], ++word.deque.val);
            names[i] = word.s;
        }
        return names;
    }

    public static class Word {
        static final int MAX_COUNT = 50_000, LEN = (int) Math.ceil(Math.log10(MAX_COUNT));

        int digitIdx, size;
        char chars[];
        String s;
        Deque deque;

        public Word(String s, int val) {
            copy(chars = new char[(digitIdx = s.length()) + LEN + 2], 0, digitIdx, s.toCharArray(), 0); // 2 for round brackets: ()
            chars[digitIdx++] = '(';
            deque = new Deque(LEN, val);
            set();
        }

        public void inc() {
            deque.inc();
            set();
        }

        void set() {copy(chars, digitIdx, size = digitIdx + deque.size(), deque.a, deque.idx);}

        public String toString() {return s = new String(chars, 0, size);}

        void copy(char[] A, int start, int end, char[] B, int startB) {while (start < end) A[start++] = B[startB++];}
    }

    public static class Deque {
        int idx, val;
        char a[];

        public Deque(int len, int val) {
            for (a = new char[idx = len], this.val = val, a[--idx] = ')'; val != 0; val /= 10)
                a[--idx] = (char) ('0' + val % 10);
        }

        public void inc() {
            val++;
            for (int i = a.length - 2; ; )
                if (++a[i] == ':') a[i--] = '0';
                else {
                    if (i < idx) a[--idx] = '1';
                    break;
                }
        }

        public int size() {return a.length - idx;}

        public String toString() {return new String(a, idx, size());}
    }
}

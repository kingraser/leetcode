package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

/**
 * @author Wit
 */
public class IteratorForCombination {
    /*
    Design the CombinationIterator class:
    CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
    next() Returns the next combination of length combinationLength in lexicographical order.
    hasNext() Returns true if and only if there exists a next combination.

    Example 1:
    Input
    ["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
    [["abc", 2], [], [], [], [], [], []]
    Output
    [null, "ab", true, "ac", true, "bc", false]
    Explanation
    CombinationIterator itr = new CombinationIterator("abc", 2);
    itr.next();    // return "ab"
    itr.hasNext(); // return True
    itr.next();    // return "ac"
    itr.hasNext(); // return True
    itr.next();    // return "bc"
    itr.hasNext(); // return False

    Constraints:
    1 <= combinationLength <= characters.length <= 15
    All the characters of characters are unique.
    At most 10^4 calls will be made to next and hasNext.
    It is guaranteed that all calls of the function next are valid.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new CombinationIterator("abc", 2),
                new String[]{"next", "hasNext", "next", "hasNext", "next", "hasNext"},
                new Object[]{"ab", true, "ac", true, "bc", false},
                new Object[6][0]);
    }

    public class CombinationIterator {
        int[] indexes;
        boolean finishedFlag;
        char[] chars, result;

        public CombinationIterator(String characters, int combinationLength) {
            indexes = new int[combinationLength];
            result = new char[combinationLength];
            chars = characters.toCharArray();
            for (int i = 0; i < combinationLength; i++) indexes[i] = i;
        }

        public String next() {
            int resultIndex = 0;
            for (int index : indexes) result[resultIndex++] = chars[index];
            int indexToMoveToNext = -1;
            for (int i = indexes.length - 1; i >= 0; i--)
                if (indexes[i] + indexes.length - i < chars.length) {
                    indexToMoveToNext = i;
                    break;
                }
            if (indexToMoveToNext == -1) finishedFlag = true;
            else {
                indexes[indexToMoveToNext]++;
                for (int i = indexToMoveToNext + 1; i < indexes.length; i++) indexes[i] = indexes[i - 1] + 1;
            }
            return new String(result);
        }

        public boolean hasNext() {return !finishedFlag;}
    }
}

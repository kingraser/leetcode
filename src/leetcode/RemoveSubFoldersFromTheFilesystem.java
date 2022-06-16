package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wit
 */
public class RemoveSubFoldersFromTheFilesystem {
    /*
    Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.
    If a folder[i] is located within another folder[j], it is called a sub-folder of it.
    The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.
    For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.

    Example 1:
    Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
    Output: ["/a","/c/d","/c/f"]
    Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.

    Example 2:
    Input: folder = ["/a","/a/b/c","/a/b/d"]
    Output: ["/a"]
    Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".

    Example 3:
    Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
    Output: ["/a/b/c","/a/b/ca","/a/b/d"]

    Constraints:
    1 <= folder.length <= 4 * 10^4
    2 <= folder[i].length <= 100
    folder[i] contains only lowercase letters and '/'.
    folder[i] always starts with the character '/'.
    Each folder name is unique.
    */
    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {List.of("/a", "/c/d", "/c/f"), new String[]{"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"}},
                {List.of("/a"), new String[]{"/a", "/a/b/c", "/a/b/d"}},
                {List.of("/a/b/c", "/a/b/ca", "/a/b/d"), new String[]{"/a/b/c", "/a/b/ca", "/a/b/d"}}
        });
    }

    public List<String> removeSubfolders(String[] folder) {
        List<String> result = new ArrayList<>(folder.length);
        Arrays.sort(folder);
        for (int i = 0; i < folder.length; ) {
            result.add(folder[i]);
            for (String s = folder[i++] + '/'; i < folder.length && folder[i].startsWith(s); ) i++;
        }
        return result;
    }
}

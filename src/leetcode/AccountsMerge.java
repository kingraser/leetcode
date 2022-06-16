package leetcode;

import leetcode.util.TestUtil;
import org.junit.Test;

import java.util.*;

import static leetcode.util.TestUtil.transfer;

/**
 * @author Wit
 */
public class AccountsMerge {
    /*
    Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
    Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts certainly have the same name.
    After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

    Example 1:
    Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
    Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
    Explanation:
    The first and second John's are the same person as they have the common email "johnsmith@mail.com".
    The third John and Mary are different people as none of their email addresses are used by other accounts.
    We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
    ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

    Example 2:
    Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
    Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]

    Constraints:
    1 <= accounts.length <= 1000
    2 <= accounts[i].length <= 10
    1 <= accounts[i][j].length <= 30
    accounts[i][0] consists of English letters.
    accounts[i][j] (for j > 0) is a valid email.
    */

    @Test
    public void test() {
        TestUtil.testEquals(new Object[][]{
                {
                        transfer("[[\"David\",\"David0@m.co\",\"David1@m.co\",\"David2@m.co\",\"David3@m.co\",\"David4@m.co\",\"David5@m.co\"]]"),
                        transfer("[[\"David\",\"David0@m.co\",\"David1@m.co\"],[\"David\",\"David3@m.co\",\"David4@m.co\"],[\"David\",\"David4@m.co\",\"David5@m.co\"],[\"David\",\"David2@m.co\",\"David3@m.co\"],[\"David\",\"David1@m.co\",\"David2@m.co\"]]")
                },
                {
                        transfer("[[\"John\",\"john00@mail.com\",\"john_newyork@mail.com\",\"johnsmith@mail.com\"],[\"Mary\",\"mary@mail.com\"],[\"John\",\"johnnybravo@mail.com\"]]"),
                        transfer("[[\"John\",\"johnsmith@mail.com\",\"john_newyork@mail.com\"],[\"John\",\"johnsmith@mail.com\",\"john00@mail.com\"],[\"Mary\",\"mary@mail.com\"],[\"John\",\"johnnybravo@mail.com\"]]")
                },
        });
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<Integer, String> idNameMap = new HashMap<>(accounts.size() << 1);
        Map<String, Integer> mailIdMap = new HashMap<>((1000 * (10 - 1)) << 1);
        int parents[] = new int[accounts.size()];
        for (int i = 1; i < parents.length; ) parents[i] = i++;
        for (int i = 0; i < accounts.size(); idNameMap.put(i, accounts.get(i++).get(0)))
            for (Integer j = 1, root = i, newRoot, parent; j < accounts.get(i).size(); mailIdMap.put(accounts.get(i).get(j++), root))
                if ((parent = mailIdMap.get(accounts.get(i).get(j))) != null)
                    root = parents[Math.max(root, (newRoot = FriendCircles.getRoot(parents, parent)))] = Math.min(root, newRoot);
        Map<Integer, TreeSet<String>> idMailMap = new HashMap<>(accounts.size() << 1);
        mailIdMap.forEach((mail, id) -> idMailMap.computeIfAbsent(FriendCircles.getRoot(parents, id), k -> new TreeSet<>()).add(mail));
        List<List<String>> result = new ArrayList<>();
        idMailMap.forEach((id, mailSet) -> result.add(new ArrayList<>(mailSet.size() + 1) {{
            add(idNameMap.get(id));
            addAll(mailSet);
        }}));
        return result;
    }

}

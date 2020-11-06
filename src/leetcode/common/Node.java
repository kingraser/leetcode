package leetcode.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Wit
 */
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    public int val;
    public List<Node> children;

    public Node(int val) {
        this.val = val;
    }
}

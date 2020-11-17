package leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

/**
 * @author Wit
 */
public class WalkingRobotSimulation {
    /*
    A robot on an infinite grid starts at point (0, 0) and faces north.  The robot can receive one of three possible types of commands:
    -2: turn left 90 degrees
    -1: turn right 90 degrees
    1 <= x <= 9: move forward x units
    Some of the grid squares are obstacles.
    The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])
    If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)
    Return the square of the maximum Euclidean distance that the robot will be from the origin.

    Example 1:
    Input: commands = [4,-1,3], obstacles = []
    Output: 25
    Explanation: robot will go to (3, 4)

    Example 2:
    Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
    Output: 65
    Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)

    Note:
    0 <= commands.length <= 10000
    0 <= obstacles.length <= 10000
    -30000 <= obstacle[i][0] <= 30000
    -30000 <= obstacle[i][1] <= 30000
    The answer is guaranteed to be less than 2 ^ 31.
    */
    /**
     * Row:     0 up, 1 right, 2 down, 3 left
     * Column:  0 x-coordinate, 1 y-coordinate
     * value:   0 stable, 1 increase, -1 decrease
     */
    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int robotSim(int[] commands, int[][] obstacles) {
        Map<Integer, TreeSet<Integer>> xObstacleMap = new HashMap<>(), yObstacleMap = new HashMap<>();
        for (int[] obstacle : obstacles) {
            xObstacleMap.computeIfAbsent(obstacle[0], k -> new TreeSet<>()).add(obstacle[1]);
            yObstacleMap.computeIfAbsent(obstacle[1], k -> new TreeSet<>()).add(obstacle[0]);
        }
        /**
         * direction: 0 up
         *            1 right
         *            2 down
         *            3 left
         * coordinates: [0] current x
         *              [1] current y
         * idx: the idx of coordinates to move
         */
        int result = 0, direction = 0, coordinates[] = new int[2], idx, directions[];
        for (int command : commands)
            if (command == -1) direction = ++direction & 3;
            else if (command == -2) direction = (--direction + 4) & 3;
            else {
                coordinates[idx = (directions = DIRECTIONS[direction])[0] == 0 ? 1 : 0] = getToMove((idx == 1 ? xObstacleMap : yObstacleMap).get(coordinates[idx ^ 1]), coordinates[idx] + directions[idx] * command, coordinates[idx]);
                result = Integer.max(result, sumOfPow2(coordinates[0], coordinates[1]));
            }
        return result;
    }

    int sumOfPow2(int i, int j) {return i * i + j * j;}

    Integer getToMove(TreeSet<Integer> set, int toMove, int current) {
        if (Objects.isNull(set)) return toMove;
        Integer obstacle = toMove < current ? set.lower(current) : set.higher(current);
        if (Objects.isNull(obstacle)) return toMove;
        if (toMove < current) return obstacle < toMove ? toMove : ++obstacle;
        return obstacle > toMove ? toMove : --obstacle;
    }

    @Test
    public void test() {
        Assert.assertEquals(25, robotSim(new int[]{4, -1, 3}, new int[][]{}));
        Assert.assertEquals(65, robotSim(new int[]{4, -1, 4, -2, 4}, new int[][]{{2, 4}}));
        Assert.assertEquals(80, robotSim(new int[]{2, -1, 8, -1, 6}, new int[][]{{1, 5}, {-5, -5}, {0, 4}, {-1, -1}, {4, 5}, {-5, -3}, {-2, 1}, {-2, -5}, {0, 5}, {0, -1}}));
    }
}

package leetcode;

/**
 * @author Wit
 */
public class DivisorGame {
    /*
    Alice and Bob take turns playing a game, with Alice starting first.
    Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
    Choosing any x with 0 < x < N and N % x == 0.
    Replacing the number N on the chalkboard with N - x.
    Also, if a player cannot make a move, they lose the game.
    Return True if and only if Alice wins the game, assuming both players play optimally.

    Example 1:
    Input: 2
    Output: true
    Explanation: Alice chooses 1, and Bob has no more moves.

    Example 2:
    Input: 3
    Output: false
    Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.

    Note:
    1 <= N <= 1000
    */

    /*
    Easy to find out, Alice will win when the game starts with 2.
    So if Alice gets 2, she will win, otherwise Bob will win.
    If the game starts with an even number, Alice just need choose 1 as X to makes next number N-1 as an odd number.
    Since odd number's factor must be odd, so the next number after Bob's move will be even.
    Then Alice still get an even number, she just repeat the move above, after some rounds, she'll get 2 to win.
    */

    public boolean divisorGame(int n) {
        return (n & 1) == 0;
    }
}

package leetcode;

/**
 * @author Wit
 */
public class AvailableCapturesforRook {
    /*
    On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.  These are given as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces, and lowercase characters represent black pieces.
    The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south), then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite colored pawn by moving to the same square it occupies.  Also, rooks cannot move into the same square as other friendly bishops.
    Return the number of pawns the rook can capture in one move.

    Example 1:
    Input: [[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
    Output: 3
    Explanation:
    In this example the rook is able to capture all the pawns.

    Example 2:
    Input: [[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
    Output: 0
    Explanation:
    Bishops are blocking the rook to capture any pawn.

    Example 3:
    Input: [[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
    Output: 3
    Explanation:
    The rook can capture the pawns at positions b5, d6 and f5.

    Note:
    board.length == board[i].length == 8
    board[i][j] is either 'R', '.', 'B', or 'p'
    There is exactly one cell with board[i][j] == 'R'
    */
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numRookCaptures(char[][] board) {
        for (int i = 0; i < 8; ++i)
            for (int j = 0; j < 8; ++j)
                if (board[i][j] == 'R') return count(board, i, j);
        return 0;
    }

    int count(char[][] board, int row, int col) {
        int res = 0;
        for (int[] d : directions)
            for (int r = row, c = col; r >= 0 && r < 8 && c >= 0 && c < 8; r = r + d[0], c = c + d[1])
                if (board[r][c] == 'p') {
                    res++;
                    break;
                } else if (board[r][c] == 'B') break;
        return res;
    }
}

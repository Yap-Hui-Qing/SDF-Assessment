package vttp.batch5.sdf.task02;

public class TicTacToe {

    // this function returns true if there are moves
    // remaining on the board
    // returns false if there are no moves left to play
    public static boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.') {
                    return true;
                }
            }
        }
        return false;
    }

    public static int minimax(char[][] board, int depth, boolean isMax) {
        int score = utility(board);

        // if maximiser has won, return his evaluated score
        if (score == 1) {
            return score;
        }

        // if minimiser has won, return his evaluated score
        if (score == -1) {
            return score;
        }

        // if there are no more moves and there is no winner, then it is a tie
        if (isMovesLeft(board) == false) {
            return 0;
        }

        // if this is maximiser's move
        if (isMax) {

            int best = -1000;

            // traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // check if cell is empty
                    if (board[i][j] == '.') {

                        // Create a deep copy of the board
                        char[][] newBoard = new char[board.length][board[0].length];
                        for (int x = 0; x < board.length; x++) {
                            newBoard[x] = board[x].clone();
                        }

                        // Make the move on the copied board
                        newBoard[i][j] = Constants.player;

                        // call minimax recursively and choose the max value
                        best = Math.max(best, minimax(newBoard, depth + 1, !isMax));

                    }
                }
            }

            return best;

        } else {
            int best = 1000;

            // traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // check if cell is empty
                    if (board[i][j] == '.') {

                        // Create a deep copy of the board
                        char[][] newBoard = new char[board.length][board[0].length];
                        for (int x = 0; x < board.length; x++) {
                            newBoard[x] = board[x].clone();
                        }

                        // Make the move on the copied board
                        newBoard[i][j] = Constants.opponent;

                        // call minimax recursively and choose the max value
                        best = Math.min(best, minimax(newBoard, depth + 1, isMax));

                    }
                }
            }
            return best;
        }

    }

    public static void possibleMoves(char[][] board){
         // traverse all cells
         for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // check if cell is empty
                if (board[i][j] == '.') {
                    // Create a deep copy of the board
                    char[][] newBoard = new char[board.length][board[0].length];
                    for (int x = 0; x < board.length; x++) {
                        newBoard[x] = board[x].clone();
                    }

                    // Make the move on the copied board
                    newBoard[i][j] = Constants.player;
                    // compute evaluation function for this move
                    int utility = minimax(newBoard, 0, false);

                    System.out.printf("y=%d, x=%d, utility=%d\n", i, j, utility);
                }
            }
        }
    }

    // utility
    public static int utility(char[][] board) {
        int score = 10;
        // checking for rows for X or O victory
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1]
                    && board[i][1] == board[i][2]) {
                if (board[i][0] == Constants.player) {
                    return 1;
                } else if (board[i][0] == Constants.opponent) {
                    return -1;
                }
            }
        }

        // checking for columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i]
                    && board[1][i] == board[2][i]) {
                if (board[0][i] == Constants.player) {
                    return 1;
                } else if (board[0][i] == Constants.opponent) {
                    return -1;
                }
            }
        }

        // checking for diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == Constants.player) {
                return 1;
            } else if (board[0][0] == Constants.opponent) {
                return -1;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == Constants.player) {
                return 1;
            } else if (board[0][2] == Constants.opponent) {
                return -1;
            }
        }

        return 0;
    }

}

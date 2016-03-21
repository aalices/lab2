package tictactoe.game;

public class Board {

    private int BOARD_SIZE = 3;
    private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private String boardState = "game";

    public void initBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++){
                board[row][col] = '-';
            }
        }
    }

    public boolean applyMove(String move, char figure) {
        String coordinates[] = move.split("\\s");
        int x = Integer.parseInt(coordinates[0])-1;
        int y = Integer.parseInt(coordinates[1])-1;
        if (!isMoveCorrect(x, y)) {
            return false;
        }

        board[x][y] = figure;
        return true;
    }

    public boolean winnerExists() {
        if (rowWin() || columnWin() || diagonalWin()) {
            this.boardState = "winner";
            return true;
        }
        return false;
    }

    private boolean rowWin() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][0] == board[row][1] &&
                board[row][1] == board[row][2] &&
                board[row][0] != '-'){
                return true;
            }
        }
        return false;
    }

    private boolean diagonalWin() {
        return board[1][1] != '-' &&
                ((board[0][0] == board[1][1] &&
                board[2][2] == board[1][1]) ||
                (board[0][2] == board[1][1] &&
                board[2][0] == board[1][1]));
    }

    private boolean columnWin() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[0][row] == board[1][row] &&
                board[1][row] == board[2][row] &&
                board[0][row] != '-'){
                return true;
            }
        }
        return false;
    }

    public String printBoard() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < BOARD_SIZE; ++row) {
            for (int col = 0; col < BOARD_SIZE; ++col) {
                builder.append(" ").append(board[row][col]).append(" ");
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    private boolean isMoveCorrect(int x, int y) {
        return !(x > BOARD_SIZE || y > BOARD_SIZE || x < 0 || y < 0 || board[x][y] != '-');
    }

    public boolean hasDraw() {
        int blanks = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == '-') {
                    blanks++;
                }
            }
        }
        if (blanks == 0){
            this.boardState = "draw";
            return true;
        }
        return false;
    }

    public String getBoardState() {
        return boardState;
    }
}

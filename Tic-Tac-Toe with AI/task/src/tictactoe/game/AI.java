package tictactoe.game;

import java.util.Arrays;
import java.util.HashMap;

public class AI extends Player {
    private final HashMap<Integer, Integer[]> map;
    private final String difficulty;
    private final char opponent;

    public AI(Field field, char playerSymbol, String difficulty) {
        super(field, playerSymbol);
        this.map = new HashMap<>();
        this.difficulty = difficulty;
        this.opponent = playerSymbol == 'X' ? 'O' : 'X';
        generateMap();
    }

    @Override
    protected void makeMove() {
        switch (difficulty) {
            case "easy" -> easy();
            case "medium" -> mediumAlt();
//            case "hard" -> hard();
        }
    }

    private void makeRandomMove() {
        do {
            System.out.println("random");
            int random = (int) (Math.random() * 9 + 1);
            if (field.isEmpty(map.get(random)[0], map.get(random)[1])) {
                System.out.println(map.get(random)[0] + " " + map.get(random)[1]);
                field.setCell(map.get(random)[0], map.get(random)[1], playerSymbol);
                break;
            }
        } while (true);
    }

    private void easy() {
        System.out.println("Making move level \"easy\"");
        makeRandomMove();
    }

    private void medium() {
        System.out.println("Making move level \"medium\"");
        if (field.getMoves() == 0) {
            makeRandomMove();
        } else {
            findBestMove();
        }
    }

    int evaluate(char[][] board) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == playerSymbol) {
                    return +10;
                } else if (board[row][0] == opponent) {
                    return -10;
                }
            }
        }
        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == playerSymbol) return +10;
                else if (board[0][col] == opponent) return -10;
            }
        }
        // Checking for Diagonals for X or O victory.
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == playerSymbol) return +10;
            else if (board[0][0] == opponent) return -10;
        }
        // Checking for Diagonals for X or O victory.
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == playerSymbol) return +10;
            else if (board[0][2] == opponent) return -10;
        }
        return 0;
    }

    static Boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') return true;
        return false;
    }

    int minimax(char[][] board, int depth, Boolean isMax) {
        int score = evaluate(board);
        if (score == 10) return score;
        if (score == -10) return score;
        if (!isMovesLeft(board)) return 0;
        if (isMax) {
            int best = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = playerSymbol;
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = opponent;
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    private void findBestMove() {
        int bestVal = -1000;
        int[] bestMove = {-1, -1};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field.getField()[i][j] == ' ') {
                    field.setCell(i + 1, j + 1, playerSymbol);
                    int moveVal = minimax(field.getField(), 0, false);
                    System.out.println(moveVal);
                    field.setCell(i + 1, j + 1, ' ');
                    if (moveVal > bestVal) {
                        bestMove[0] = i + 1;
                        bestMove[1] = j + 1;
                        bestVal = moveVal;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(bestMove) + " " + bestVal);
        System.out.println(Arrays.deepToString(field.getField()));
        if (bestVal != -10 && bestVal != 10 && field.getMoves() < 8 && bestVal == 0) {
            makeRandomMove();
        } else if (bestVal == -10) {
            field.setCell(bestMove[0], bestMove[1], playerSymbol);
        } else {
            field.setCell(bestMove[0], bestMove[1], playerSymbol);
        }
    }

    private void mediumAlt() {
        char[][] board = field.getField();

        if (board[0][0] == board[1][1] || board[1][1] == board[2][2]) {
            if (board[0][0] == ' ' && board[1][1] != ' ') {
                field.setCell(1, 1, playerSymbol);
                return;
            } else if (board[2][2] == ' ' && board[1][1] != ' ') {
                field.setCell(3, 3, playerSymbol);
                return;
            }
        } else if (board[0][2] == board[1][1] || board[1][1] == board[2][0]) {
            if (board[0][2] == ' ' && board[1][1] != ' ') {
                field.setCell(1, 3, playerSymbol);
                return;
            } else if (board[2][0] == ' ' && board[1][1] != ' ') {
                field.setCell(3, 1, playerSymbol);
                return;
            }
        } else if (board[0][0] == board[2][2] || board[0][2] == board[2][0]) {
            if (board[0][0] == ' ' && board[2][2] != ' ') {
                field.setCell(1, 1, playerSymbol);
                return;
            } else if (board[0][2] == ' ' && board[2][0] != ' ') {
                field.setCell(1, 3, playerSymbol);
                return;
            }
        } else {
            //rows
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == board[i][1] || board[i][1] == board[i][2]) {
                    if (board[i][0] == ' ' && board[i][1] != ' ') {
                        field.setCell(i + 1, 1, playerSymbol);
                        return;
                    } else if (board[i][2] == ' ' && board[i][1] != ' ') {
                        field.setCell(i + 1, 3, playerSymbol);
                        return;
                    }
                } else if (board[i][0] == board[i][2]) {
                    if (board[i][0] == ' ' && board[i][2] != ' ') {
                        field.setCell(i + 1, 1, playerSymbol);
                        return;
                    }
                }
            }
            //columns
            for (int i = 0; i < 3; i++) {
                if (board[0][i] == board[1][i] || board[1][i] == board[2][i]) {
                    if (board[0][i] == ' ' && board[1][i] != ' ') {
                        field.setCell(1, i + 1, playerSymbol);
                        return;
                    } else if (board[2][i] == ' ' && board[1][i] != ' ') {
                        field.setCell(3, i + 1, playerSymbol);
                        return;
                    }
                } else if (board[0][i] == board[2][i] && board[0][i] != ' ') {
                    if (board[0][i] == ' ') {
                        field.setCell(1, i + 1, playerSymbol);
                        return;
                    }
                }
            }
        }
        makeRandomMove();
    }

    private void generateMap() {
        int i = 1;
        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                map.put(i, new Integer[]{x, y});
                i++;
            }
        }
    }
}


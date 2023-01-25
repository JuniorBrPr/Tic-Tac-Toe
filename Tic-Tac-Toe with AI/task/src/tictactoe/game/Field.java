package tictactoe.game;

import java.util.HashSet;

public class Field {
    private final char[][] field;
    private final int SIZE = 3;
    private char lastMove;
    private int moves = 0;

    public Field() {
        this.field = new char[SIZE][SIZE];
        generateField();
    }

    protected void setCell(int x, int y, char c) {
        this.lastMove = c;
        field[x - 1][y - 1] = c;
    }

    protected void checkRows() {
        if (field[0][0] == field[0][1] && field[0][1] == field[0][2] && field[0][0] != ' '
                || field[1][0] == field[1][1] && field[1][1] == field[1][2] && field[1][0] != ' '
                || field[2][0] == field[2][1] && field[2][1] == field[2][2] && field[2][0] != ' '
                || field[0][0] == field[1][0] && field[1][0] == field[2][0] && field[0][0] != ' '
                || field[0][1] == field[1][1] && field[1][1] == field[2][1] && field[0][1] != ' '
                || field[0][2] == field[1][2] && field[1][2] == field[2][2] && field[0][2] != ' '
                || field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] != ' '
                || field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[0][2] != ' ') {
            System.out.println(lastMove + " wins");
            System.exit(0);
        }
        if (moves == 9) {
            System.out.println("Draw");
            System.exit(0);
        }
    }

    private void generateField() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = ' ';
            }
        }
    }

    protected boolean isEmpty(int x, int y) {
        return field[x - 1][y - 1] == ' ';
    }

    protected char[][] getField() {
        return field;
    }

    protected int getMoves() {
        return moves;
    }

    protected void incrementMove() {
        moves++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("---------\n");
        for (int i = 0; i < SIZE; i++) {
            sb.append("| ");
            for (int j = 0; j < SIZE; j++) {
                sb.append(field[i][j]).append(" ");
            }
            sb.append("|\n");
        }
        return sb.append("---------").toString();
    }
}

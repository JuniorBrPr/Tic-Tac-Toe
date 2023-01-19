package tictactoe.game;

import java.util.HashSet;
import java.util.regex.Pattern;

public class Field {
    private final char[][] field;
    private final HashSet<Integer> emptyCells = new HashSet<>();
    private final int SIZE = 3;
    private final Pattern SET_FIELD_PATTERN;
    private int xCount;
    private int oCount;
    private char lastMove;

    public Field() {
        this.field = new char[SIZE][SIZE];
        generateField();
        SET_FIELD_PATTERN = Pattern.compile("^[XO_]{9}$");
        xCount = 0;
        oCount = 0;
    }

    protected void setCell(int x, int y) {
        char c;
        if (xCount > oCount) {
            c = 'O';
            oCount++;
        } else {
            c = 'X';
            xCount++;
        }
        this.lastMove = c;

        field[x - 1][y - 1] = c;
        emptyCells.remove(x * SIZE + y);
    }

    protected void checkRows(Boolean lastCheck) {
        if (emptyCells.isEmpty()) {
            System.out.println("Draw");
            System.exit(0);
        } else {
            if (lastCheck) {
                System.out.println("Game not finished");
            }
        }
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
    }

    protected void setField(String initialFieldState) {
        int CELLS = SIZE * SIZE;
        if (initialFieldState.length() != CELLS) {
            System.out.println("Initial field state should be 9 characters long!");
            return;
        }
        if (!SET_FIELD_PATTERN.matcher(initialFieldState).matches()) {
            System.out.println("Initial field state should contain only digits and underscores!\n" +
                    "Example: \"_XO__X_O_\"");
            return;
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                char c = initialFieldState.charAt(i * SIZE + j);
                if (c != '_') {
                    field[i][j] = c;
                    if (c == 'X') {
                        xCount++;
                    } else {
                        oCount++;
                    }
                }
            }
        }
    }

    private void generateField() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = ' ';
                emptyCells.add(i * SIZE + j);
            }
        }
    }

    protected boolean isEmpty(int x, int y) {
        return field[x - 1][y - 1] == ' ';
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

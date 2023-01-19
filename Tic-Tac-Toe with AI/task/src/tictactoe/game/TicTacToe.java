package tictactoe.game;

import java.util.Scanner;

public class TicTacToe {
    private final Field field;
    private final Scanner in;

    public TicTacToe() {
        this.field = new Field();
        this.in = new Scanner(System.in);
    }

    public void play() {
        System.out.println("Enter the cells:");
        field.setField(in.nextLine());
        field.checkRows(false);
        System.out.println(field);
        System.out.println("Enter the coordinates:");
        makeMove();
        System.out.println(field);
        field.checkRows(true);
    }

    private void makeMove() {
        int x, y;
        do {
            try {
                x = in.nextInt();
                y = in.nextInt();
                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (!field.isEmpty(x, y)) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    field.setCell(x, y);
                    break;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                in.nextLine();
            }
        } while (true);
    }
}

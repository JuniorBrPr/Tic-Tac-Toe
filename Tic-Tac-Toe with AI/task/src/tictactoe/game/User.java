package tictactoe.game;

import java.util.Scanner;

public class User extends Player {
    private final Scanner in;

    public User(Field field, char playerSymbol, Scanner in) {
        super(field, playerSymbol);
        this.in = in;
    }

    @Override
    protected void makeMove() {
        int x, y;
        System.out.println("Enter the coordinates:");
        do {
            try {
                x = in.nextInt();
                y = in.nextInt();
                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (!field.isEmpty(x, y)) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    field.setCell(x, y, playerSymbol);
                    break;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                in.nextLine();
            }
        } while (true);
    }
}

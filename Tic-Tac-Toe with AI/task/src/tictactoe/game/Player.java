package tictactoe.game;

import java.util.Scanner;

public class Player {
    private final Field field;
    private final char playerSymbol;
    private final Scanner in;

    public Player(Field field, char playerSymbol) {
        this.field = field;
        this.playerSymbol = playerSymbol;
        this.in = new Scanner(System.in);
    }

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

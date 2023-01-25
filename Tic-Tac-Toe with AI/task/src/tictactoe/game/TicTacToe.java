package tictactoe.game;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TicTacToe {
    private final Field field;
    private final Scanner in;
    private final Pattern pattern;

    public TicTacToe() {
        this.field = new Field();
        this.in = new Scanner(System.in);
        this.pattern = Pattern.compile("user|easy|medium");
    }

    public void menu(){
        String badCommand = "Bad parameters!";
        do {
            System.out.println("Input command:");
            String[] command = in.nextLine().split(" ");
            switch (command[0]) {
                case "start" -> {
                    if (command.length == 3) {
                        if (pattern.matcher(command[1]).matches() && pattern.matcher(command[2]).matches()) {
                            startGame(command[1], command[2]);
                        } else {
                            System.out.println(badCommand);
                        }
                    } else {
                        System.out.println(badCommand);
                    }
                }
                case "exit" -> System.exit(0);
                default -> System.out.println(badCommand);
            }
        } while (true);
    }

    private void startGame(String playerType1, String playerType2){
        Player player1 = Objects.equals(playerType1, "user") ? new User(field, 'X', in) :
                new AI(field, 'X', playerType1);
        Player player2 = Objects.equals(playerType2, "user") ? new User(field, 'O', in) :
                new AI(field, 'O', playerType2);
       play(player1, player2);
    }

    private void play(Player player1, Player player2) {
        //noinspection InfiniteLoopStatement
        do {
            System.out.println(field);
            field.checkRows();
            player1.makeMove();
            field.incrementMove();
            System.out.println(field);
            field.checkRows();
            player2.makeMove();
            field.incrementMove();
        } while (true);
    }
}


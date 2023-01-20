package tictactoe.game;

public class TicTacToe {
    private final Field field;
    private final Player player;
    private final AI ai;

    public TicTacToe() {
        this.field = new Field();
        this.player = new Player(field, 'X');
        this.ai = new AI(field, 'O');
    }

    public void play() {
        //noinspection InfiniteLoopStatement
        do {
            System.out.println(field);
            field.checkRows();
            player.makeMove();
            System.out.println(field);
            field.checkRows();
            System.out.println("Making move level \"easy\"");
            ai.makeMove();
        } while (true);
    }
}


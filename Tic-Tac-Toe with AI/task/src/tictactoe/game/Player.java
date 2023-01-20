package tictactoe.game;

abstract class Player {
    protected final Field field;
    protected final char playerSymbol;

    public Player(Field field, char playerSymbol) {
        this.field = field;
        this.playerSymbol = playerSymbol;
    }

    abstract void makeMove();
}

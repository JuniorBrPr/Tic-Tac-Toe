package tictactoe.game;

import java.util.HashMap;

public class AI {
    private final Field field;
    private final char aiSymbol;
    private final HashMap<Integer, Integer[]> map;

    public AI(Field field, char aiSymbol) {
        this.field = field;
        this.aiSymbol = aiSymbol;
        this.map = new HashMap<>();
        generateMap();
    }

    protected void makeMove() {
        int random = (int) (Math.random() * 9 + 1);
        if (field.isEmpty(map.get(random)[0], map.get(random)[1])) {
            field.setCell(map.get(random)[0], map.get(random)[1], aiSymbol);
        } else {
            makeMove();
        }
    }

    private void generateMap(){
        int i = 1;
        for (int x = 1; x <= 3; x++) {
            for (int y = 1; y <= 3; y++) {
                map.put(i, new Integer[]{x, y});
                i++;
            }
        }
    }
}


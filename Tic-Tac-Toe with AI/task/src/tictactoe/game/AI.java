package tictactoe.game;

import java.util.HashMap;

public class AI extends Player {
    private final HashMap<Integer, Integer[]> map;

    public AI(Field field, char playerSymbol) {
        super(field, playerSymbol);
        this.map = new HashMap<>();
        generateMap();
    }

    @Override
    protected void makeMove() {
        System.out.println("Making move level \"easy\"");
        do {
            int random = (int) (Math.random() * 9 + 1);
            if (field.isEmpty(map.get(random)[0], map.get(random)[1])) {
                field.setCell(map.get(random)[0], map.get(random)[1], playerSymbol);
                break;
            }
        } while (true);
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


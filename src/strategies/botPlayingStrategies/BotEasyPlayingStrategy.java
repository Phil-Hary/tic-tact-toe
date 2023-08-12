package strategies.botPlayingStrategies;

import java.util.List;

import models.Board;
import models.Cell;

public class BotEasyPlayingStrategy implements BotPlayingStrategy {
    public Integer[] getBotMove(Board board){
        Integer[] nextMove = new Integer[2];

        List<List<Cell>> cells = board.getCells();
        
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                Cell cell = cells.get(i).get(j);

                if(cell == null) {
                    nextMove[0] = i;
                    nextMove[1] = j;

                    return nextMove;
                }
            }
        }

        return nextMove;
    }
}

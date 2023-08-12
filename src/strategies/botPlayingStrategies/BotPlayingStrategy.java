package strategies.botPlayingStrategies;

import models.Board;

public interface BotPlayingStrategy {
    public Integer[] getBotMove(Board board);
}

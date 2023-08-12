package factories;

import models.Game;
import strategies.checkWinStrategies.CheckWinStrategy;
import strategies.checkWinStrategies.NaiveCheckWinStrategy;

public class GameWinStrategyFactory {
    public static CheckWinStrategy getGameWinStrategy(Game game) {
        return new NaiveCheckWinStrategy(game);
    }
}

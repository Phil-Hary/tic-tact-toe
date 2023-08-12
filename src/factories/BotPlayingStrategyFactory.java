package factories;

import models.BotLevel;
import strategies.botPlayingStrategies.BotEasyPlayingStrategy;
import strategies.botPlayingStrategies.BotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotLevel botLevel) {
        switch(botLevel) {
            case EASY:
            case INTERMEDIATE:
            case DIFFICULT:
                return new BotEasyPlayingStrategy();
        }
        throw new UnsupportedOperationException("Bot type" + botLevel + " is not supported");
    }
}

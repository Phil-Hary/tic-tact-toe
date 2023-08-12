package models;

public class Bot extends Player {
    BotLevel botLevel;

    public Bot(String name, Character symbol, BotLevel botLevel) {
        super(name, symbol);
        this.botLevel = botLevel;
    }
}

package models;

public class Player {
    String name;
    Character symbol;

    public Player(String name, Character symbol) {
        this.name = name;
        this.symbol = symbol;
    } 
    
    public String getName() {
        return this.name;
    }

    public Character getSymbol() {
        return this.symbol;
    }
}



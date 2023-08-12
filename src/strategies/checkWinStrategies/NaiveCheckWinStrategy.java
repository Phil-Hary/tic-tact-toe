package strategies.checkWinStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import models.Cell;
import models.Game;
import models.Move;
import models.Player;

public class NaiveCheckWinStrategy implements CheckWinStrategy {
    
    Game game;
    HashMap<Integer, HashMap<Character, Integer>> rows = new HashMap<Integer, HashMap<Character, Integer>>() {};
    HashMap<Integer, HashMap<Character, Integer>> cols = new HashMap<Integer, HashMap<Character, Integer>>() {};
    HashMap<Character, Integer> diagonal = new HashMap<Character, Integer>() {};
    HashMap<Character, Integer> antiDiagonal = new HashMap<Character, Integer>() {};

    public NaiveCheckWinStrategy(Game game) {
        this.game = game;
        ArrayList<Character> characters = new ArrayList<Character>();
        game.getPlayers().forEach(player -> characters.add(player.getSymbol()));

        for(int i = 0; i < game.getBoard().getSize(); i++) {
            HashMap<Character, Integer> rowCharacters = new HashMap<Character,Integer>() {};
            HashMap<Character, Integer> columnCharacters = new HashMap<Character,Integer>() {};
            for (int j = 0; j < characters.size(); j++) {
                rowCharacters.put(characters.get(j), 0);
                columnCharacters.put(characters.get(j), 0);
            }

            this.rows.put(i, rowCharacters);
            this.cols.put(i, columnCharacters);
        }

        characters.forEach(character -> {
            this.diagonal.put(character, 0);
            this.antiDiagonal.put(character, 0);
        });
    }

    public boolean checkRow(Integer row, Character symbol) {
        Integer numberOfSymbols = this.rows.get(row).get(symbol);
        numberOfSymbols++;

        if(numberOfSymbols == this.game.getBoard().getSize()) {
            return true;
        }

        this.rows.get(row).put(symbol, numberOfSymbols);
        return false;
    }

    public boolean checkColumn(Integer col, Character symbol) {
        Integer numberOfSymbols = this.cols.get(col).get(symbol);
        numberOfSymbols++;

        if(numberOfSymbols == this.game.getBoard().getSize()) {
            return true;
        }

        this.cols.get(col).put(symbol, numberOfSymbols);
        return false;
    }

    public boolean checkDiagonal(Character symbol) {
        Integer numberOfSymbols = this.diagonal.get(symbol);
        numberOfSymbols++;

        if(numberOfSymbols == this.game.getBoard().getSize()) {
            return true;
        }

        this.diagonal.put(symbol, numberOfSymbols);
        return false;
    }

    public boolean checkAntiDiagonal(Character symbol) {
        Integer numberOfSymbols = this.antiDiagonal.get(symbol);
        numberOfSymbols++;

        if(numberOfSymbols == this.game.getBoard().getSize()) {
            return true;
        }

        this.antiDiagonal.put(symbol, numberOfSymbols);
        return false;
    }

    public Boolean isGameWon() {
        Move lastMove = game.getMoves().get(game.getMoves().size() - 1);
        
        Cell lastMoveCell = lastMove.getCell();

        Integer row = lastMoveCell.getRow();
        Integer column = lastMoveCell.getColumn();
        Character symbol = lastMoveCell.getCellSymbol();
        
        return this.checkRow(row, symbol) || this.checkColumn(column, symbol) || this.checkDiagonal(symbol)
                || this.checkAntiDiagonal(symbol);
    }
}

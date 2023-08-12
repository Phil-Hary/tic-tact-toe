package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.*;
import factories.*;
import models.*;

import strategies.botPlayingStrategies.BotPlayingStrategy;

public class GameController {
    Scanner sc = new Scanner(System.in);

    public Game createGame(List<Player> players) throws InvalidGameConstructionException{
        Game game = Game
            .getBuilder()
            .setPlayers(players)
            .build();
        
        game.setGameWinStrategy();
        return game;
    }

    public void validateMove(Board board, Integer row,  Integer col) throws InvalidCellException {

        if (row < 0 || row >= board.getSize()  || col < 0 || col >= board.getSize()) {
            throw new InvalidCellException("Invalid cell");
        }
    }

    public void validateCellStatus(Cell cell) throws InvalidCellException {
        if (cell != null && cell.getCellStatus() != CellStatus.AVAILABLE) {
            throw new InvalidCellException("Cell already occupied");
        }
    }

    public void makeMove(Game game, Integer row, Integer col) throws InvalidCellException {
        Board board = game.getBoard();
        this.validateMove(board, row, col);


        Cell cell = board.getCell(row, col);
        Player currentPlayer = game.getPlayers().get(game.getCurrentPlayerIndex());

        if(cell == null) {
            cell = new Cell(row, col, currentPlayer);
        } else {
            this.validateCellStatus(cell);
        }
        
        Move move = new Move(cell, currentPlayer);

        game.addMove(move);
        System.out.println("Board");
        board.displayBoard();
    }

    public void undoMove(Game game) {
        Board board = game.getBoard();
        System.out.println(game.getMoves().size());
        Move lastMove = game.getMoves().remove(game.getMoves().size() - 1);

        Cell cell = lastMove.getCell();
        
        Integer cellRow = cell.getRow();
        Integer cellColumn = cell.getColumn();

        board.setCellToNull(cellRow, cellColumn);

        System.out.println("Board");
        board.displayBoard();
    }

    public List<Player> getPlayers(Integer numberOfPlayers) {
        
        Integer idx  = 1;
        List<Player> players= new ArrayList<Player>();

        while (idx <= numberOfPlayers) {
            System.out.println("Enter the name of player " + idx );
            String playerName = sc.next();
            System.out.println("Enter the symbol for player " + playerName );
            Character playerCharacter = sc.next().charAt(0);
            Player player = this.createPlayer(playerName, playerCharacter);

            players.add(player);

            idx += 1;

        }

        return players;
    }

    public List<Player> getBots(Integer numberOfBots) {
        List<Player> bots= new ArrayList<>();

        for(int i = 0; i < numberOfBots; i++) {
            Bot bot = new Bot("1", Integer.toString(i+1).charAt(0), BotLevel.EASY);
            bots.add(bot);
        }

        return bots;
    }

    public Game initGame() throws InvalidGameConstructionException {

        System.out.println("Tic Tac Toe");
        System.out.println("Enter the board size");
        Integer boardSize = sc.nextInt();

        System.out.println("Enter the number of players");
        Integer numberOfPlayers = sc.nextInt();
        Integer numberOfBots = boardSize - numberOfPlayers - 1;

        List<Player> players = this.getPlayers(numberOfPlayers);

        if (numberOfBots > 0) {
            List<Player> bots = this.getBots(numberOfBots);
            players.addAll(bots);
            System.out.println("Added " + numberOfBots + " bot(s) to the game");
        }
        
        Game game =  this.createGame(players);

        return game;
    }


    public void playGame (Game game) {
        Board board = game.getBoard();
        Integer row = null;
        Integer col = null;

        while (game.getMoves().size() <= board.getCells().size() * board.getCells().size()) {
            Integer currentPlayerIndex = game.getCurrentPlayerIndex();
            Player currentPlayer = game.getPlayers().get(currentPlayerIndex);

            if (currentPlayer.getClass().getName().equals("models.Bot")) {
                System.out.println("Bot " + currentPlayer.getName() + "'s turn");
                BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(BotLevel.EASY);
                Integer[] botMove = botPlayingStrategy.getBotMove(board);

                row = botMove[0];
                col = botMove[1];
            } else {
                System.out.println("Player " + currentPlayer.getName() + " your turn");
                System.out.println("Enter the row");
                row = sc.nextInt();

                System.out.println("Enter the col");
                col = sc.nextInt();
            }
            

            try {
                this.makeMove(game, row, col);
                
                if (currentPlayer.getClass().getName().equals("models.Player")) {
                    System.out.println("Do you want to undo the move (Y/N)");
                    Character undoChoice = sc.next().charAt(0);

                    if (undoChoice == 'Y') {
                        this.undoMove(game);
                        continue;
                    }
                }

                if (game.getMoves().size() > 0 && game.checkForDraw()) {
                    System.out.println("Game drawn");
                    break;
                }

                if (game.getMoves().size() > 0 && game.checkForWin()) {
                    System.out.println("Game won");
                    System.out.println("Player " + currentPlayer.getName() + " has won the game!!!");
                    break;
                }
                
                
                game.setCurrentPlayerIndex((currentPlayerIndex + 1) % (board.getSize() - 1));

            } catch (InvalidCellException e) {
                System.out.println(e.getMessage());
                // e.printStackTrace();
                continue;
            }   
        }
        
    }

    public Player createPlayer(String name, Character symbol) {
        return new Player(name, symbol);
    }
}

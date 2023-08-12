package models;

import java.util.ArrayList;
import java.util.List;

import exceptions.InvalidGameConstructionException;
import factories.GameWinStrategyFactory;
import strategies.checkWinStrategies.CheckWinStrategy;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private Integer currentPlayerIndex;
    private CheckWinStrategy gameWinStrategy;

    private Game(GameBuilder gameBuilder) {
        this.board = gameBuilder.board;
        this.players = gameBuilder.players;
        this.moves = gameBuilder.moves;
        this.gameStatus = gameBuilder.gameStatus;
        this.currentPlayerIndex = gameBuilder.currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(Integer currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public Board getBoard() {
        return this.board;
    }

    public boolean checkForDraw() {
        return this.moves.size() == this.board.getSize() * this.board.getSize();
    }

    public boolean checkForWin() {
        return this.gameWinStrategy.isGameWon();
    }

    public Integer getCurrentPlayerIndex() {
        return this.currentPlayerIndex;
    }

    public List<Player> getPlayers () {
        return this.players;
    }

    public void addMove(Move move) {
        this.moves.add(move);
        board.setCell(move.cell);
    }

    public List<Move> getMoves() {
        return this.moves;
    }

    public void setGameWinStrategy() {
        this.gameWinStrategy = GameWinStrategyFactory.getGameWinStrategy(this);
    }

    public static class GameBuilder  {
        private Board board;
        private List<Player> players;
        private List<Move> moves;
        private GameStatus gameStatus;
        private Integer currentPlayerIndex;

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build() throws InvalidGameConstructionException {
            if(players == null || players.size() == 0) {
                throw new InvalidGameConstructionException("Payers cannot be null or empty");
            }
            
            board = new Board(players.size() + 1);
            currentPlayerIndex = 0;
            gameStatus = GameStatus.IN_PROGRESS;
            moves = new ArrayList<Move>();


            return new Game(this);
        }
    }
}

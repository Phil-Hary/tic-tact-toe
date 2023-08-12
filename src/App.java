import java.util.Scanner;

import controllers.GameController;
import exceptions.InvalidGameConstructionException;
import models.Game;


public class App {
        
    public static void main(String[] args) throws Exception {
        GameController gameController = new GameController();
        
        try {
            Game game = gameController.initGame();
            gameController.playGame(game);
        } catch (InvalidGameConstructionException e) {
            System.out.println("Sorry, we encountered an error while starting the game!");
        }
    }
}

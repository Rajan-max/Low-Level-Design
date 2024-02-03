package DesignPatternPractice;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Strategy pattern for dice rolling
interface DiceStrategy {
    int roll();
}

// Singleton pattern for the Game Board
class GameBoard {
    private static GameBoard instance;
    // Represents the position of players on the board
    private Map<String, Integer> playerPositions = new HashMap<>();

    private GameBoard() {
    }

    public static GameBoard getInstance() {
        if (instance == null) {
            instance = new GameBoard();
        }
        return instance;
    }

    public int getPlayerPosition(String playerName) {
        return playerPositions.getOrDefault(playerName, 0);
    }

    public void setPlayerPosition(String playerName, int position) {
        playerPositions.put(playerName, position);
    }
}

// Concrete strategy for a regular six-sided dice
class RegularDiceStrategy implements DiceStrategy {
    private Random random = new Random();

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}

// Player class
class Player {
    private String name;
    private DiceStrategy diceStrategy;

    public Player(String name, DiceStrategy diceStrategy) {
        this.name = name;
        this.diceStrategy = diceStrategy;
    }

    public int rollDice() {
        return diceStrategy.roll();
    }

    public String getName() {
        return name;
    }
}

// Game class using SOLID principles
class SnakeAndLadderGame {
    private GameBoard gameBoard;
    private Player player;

    public SnakeAndLadderGame(GameBoard gameBoard, Player player) {
        this.gameBoard = gameBoard;
        this.player = player;
    }

    public void playTurn() {
        int diceResult = player.rollDice();
        int currentPosition = gameBoard.getPlayerPosition(player.getName());
        int newPosition = currentPosition + diceResult;

        // Check for snakes and ladders
        newPosition = handleSnakesAndLadders(newPosition);

        gameBoard.setPlayerPosition(player.getName(), newPosition);

        System.out.println(player.getName() + " rolled a " + diceResult + ". Moved to position " + newPosition);
    }

    private int handleSnakesAndLadders(int position) {
        // Implement logic to handle snakes and ladders
        // For simplicity, we are not providing the actual implementation here
        // You can define a mapping of snakes and ladders and update the position accordingly
        // E.g., if position is the head of a snake, move to the tail position

        // This is a placeholder and should be implemented based on game rules
        return position;
    }
}

public class SnakeAndLadderGameExample {
    public static void main(String[] args) {
        // Create game board (Singleton)
        GameBoard gameBoard = GameBoard.getInstance();

        // Create players with different dice strategies
        Player player1 = new Player("Player 1", new RegularDiceStrategy());
        Player player2 = new Player("Player 2", new RegularDiceStrategy());

        // Create game instances for each player
        SnakeAndLadderGame gameForPlayer1 = new SnakeAndLadderGame(gameBoard, player1);
        SnakeAndLadderGame gameForPlayer2 = new SnakeAndLadderGame(gameBoard, player2);

        // Play a few turns for each player
        for (int i = 0; i < 5; i++) {
            gameForPlayer1.playTurn();
            gameForPlayer2.playTurn();
        }
    }
}


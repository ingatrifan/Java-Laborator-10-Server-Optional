package GameLogic;

import java.util.Random;

public class Game {
    int id;
    Player firstPlayer;
    Player secondPlayer;
    Boolean readyGame;
    int winner;
    public Game(Player firstPlayer){
        this.firstPlayer = firstPlayer;
        Random random = new Random();
        this.id = random.nextInt();
        this.readyGame = false;
        this.winner = 0;
    }
    public String joinGame(Player secondPlayer){
        this.secondPlayer = secondPlayer;
        this.readyGame = true;
        return "Successfully entered the game";
    }
    public Board startGame(){
        if (readyGame){
            Board board = new Board();
            return board;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public int getWinner() {
        return winner;
    }
}

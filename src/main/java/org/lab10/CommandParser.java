package org.lab10;

import GameLogic.Board;
import GameLogic.Game;
import GameLogic.HtmlBuilder;
import GameLogic.Player;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    static List<Game> openGames = new ArrayList<>();
    public  String parseCommand(String command, Player player){
        if (command.contains("create game"))return createGame(player); else
            if (command.contains("join game"))return joinGame(command,player); else
                if (command.contains("make move")) return makeMove(command,player); else
                    if (command.contains("start game")) return startGame(player);
                        if (command.contains("stop")) return stopGame(player);
        return "Command not recognized";
    }


    private String createGame(Player player){
        if (player.getGame() == null){
            Game game = new Game(player);
            openGames.add(game);
            player.setGame(game);
            player.setColor(1);
            return String.valueOf(game.getId());
        }
        return "You already joined a game";
    }
    private String joinGame(String command,Player player){
        if (player.getGame() == null){
            String words[] = command.split(" ");
            Game chosenGame = openGames.stream()
                    .filter(game -> words[words.length-1].equals(String.valueOf(game.getId())))
                    .findAny()
                    .orElse(null);
            if (chosenGame != null) {
                player.setGame(chosenGame);
                player.setColor(2);
                return chosenGame.joinGame(player);
            }
            return "No game with such id";
        }
        return "You already joined a game";
    }

    private String makeMove(String command,Player player){
        if (player.getBoard()!=null && player.getGame().getWinner()==0){
            if (player.getBoard().getLastPlayer() != player){
                String words[] = command.split(" ");
                int posX = Integer.parseInt(words[2]);
                int posY = Integer.parseInt(words[3]);
                if (player.getBoard().checkForWinner()) {
                    player.getGame().setWinner(player.getColor());
                    HtmlBuilder htmlBuilder = new HtmlBuilder(player.getBoard());
                    return "You won";
                }
                return player.getBoard().makeMove(posX,posY,player);
            }
            return "Not your turn yet";
        }
        if (player.getGame().getWinner()==player.getColor()){
            return "You won";
        } else
            if (player.getGame().getWinner() != 0) return "You lost";

        return "You didn't start a game";
    }
    private String startGame(Player player){
        if (player.getGame() != null){
            Board board = player.getGame().startGame();
            if (board != null){
                player.getGame().getFirstPlayer().setBoard(board);
                player.getGame().getSecondPlayer().setBoard(board);
                return "Game started";
            }
            return "Not all players are ready";
        }
        return "You didn't join a game";
    }
    private String stopGame(Player player){
        if (player.getGame()!= null){
            player.setGame(null);
            return "Successfully exited the game";
        }
        return "You didn't enter a game";
    }
}

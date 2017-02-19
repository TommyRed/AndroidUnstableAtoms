package com.example.semanticer.unstable.domain.ai;

import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.GameImpl;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Rechtig on 11.02.2017.
 */

public class Ai {

    private Ai() {

    }

    public static GameBoard makeTurn(Game game) {

        if (game.getBoard() == null && game.getBoard().fields().isEmpty())
            throw new IllegalArgumentException("Gameboard can't be empty!");

//        List<GameBoard> possibleTurns = getPossibleTurns(game);

        return getBestValue(getPossibleTurns(game.getBoard(), game), game);
//        return game.canPlay() ? makeTurn(game.getBoard(), game) : game.getBoard();

    }

    private static GameBoard makeTurn(GameBoard board, Game game){
        for (int x = 0; x < game.getBoard().rows(); x++) {
            for (int y = 0; y < game.getBoard().columns(); y++) {
                if (board.fields().get(x).get(y).player() == game.getPlayer()){
//                    if (board.fields().get(x).get(y).atomCount() == 3){
//                        return game.resolveTurn(x, y, board);
//                    }else if (board.fields().get(x).get(y).atomCount() == 2){
//                        return game.resolveTurn(x, y, board);
//                    }else if (board.fields().get(x).get(y).atomCount() == 1){
//                        return game.resolveTurn(x, y, board);
//                    }

                    return game.resolveTurn(x, y, board);
                }
            }
        }
        return randomTurn(board, game);
    }

    private static GameBoard randomTurn(GameBoard board, Game game) {

        int x = new Random().nextInt(board.rows());
        int y = new Random().nextInt(board.columns());

        return game.isMovePossible(x, y) ? game.resolveTurn(x, y, board) : randomTurn(board, game);
    }

    private static GameBoard getBestValue(List<GameBoard> arr, Game game) {

        if (arr.isEmpty())
            throw new IllegalArgumentException("List of possible solutions can't be empty!");

        GameBoard bestValue = game.getBoard();
        Player player = game.getPlayer();

        for (GameBoard gameBoard : arr) {

            System.out.println(game.getPlayerScore(player, gameBoard));

            if (game.getPlayerScore(player, bestValue) < game.getPlayerScore(player, gameBoard))
                bestValue = gameBoard;

//            bestValue = game.getPlayerScore(player, bestValue) < game.getPlayerScore(player, gameBoard)
//                    ? gameBoard : bestValue;
        }

        return bestValue;
    }

    private static List<GameBoard> getPossibleTurns(GameBoard board, Game game) {

        List<GameBoard> possibleTurns = new ArrayList<>();

        for (int x = 0; x < game.getBoard().rows(); x++) {
            for (int y = 0; y < game.getBoard().columns(); y++) {
                if (board.fields().get(x).get(y).player() == game.getPlayer()){
//                    if (board.fields().get(x).get(y).atomCount() == 3){
//                        return game.resolveTurn(x, y, board);
//                    }else if (board.fields().get(x).get(y).atomCount() == 2){
//                        return game.resolveTurn(x, y, board);
//                    }else if (board.fields().get(x).get(y).atomCount() == 1){
//                        return game.resolveTurn(x, y, board);
//                    }

//                    System.out.println("befoooooooooooooor copy and fok " + game.getPlayerScore(game.getPlayer(), board));

                    Game copy = GameImpl.cloneGame((GameImpl) game);

                    possibleTurns.add(copy.resolveTurn(x, y, board));

                    System.out.println("after copy and fok " + game.getPlayerScore(game.getPlayer(), game.resolveTurn(x, y, board)));
                }
            }
        }

        if (possibleTurns.isEmpty())
            possibleTurns.add(randomTurn(board, game));

        return possibleTurns;
    }
}

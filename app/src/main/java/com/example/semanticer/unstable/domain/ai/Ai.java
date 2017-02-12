package com.example.semanticer.unstable.domain.ai;

import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.GameField;
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
//
//        return getBestValue(possibleTurns, game);
        return game.canPlay() ? randomTurn(game.getBoard(), game) : game.getBoard();

    }

    private static GameBoard randomTurn(GameBoard board, Game game){

        int x = new Random().nextInt(board.rows());
        int y = new Random().nextInt(board.columns());

        return game.isMovePossible(x, y) ? game.resolveTurn(x, y) : randomTurn(board, game);
    }

    private static GameBoard getBestValue(List<GameBoard> arr, Game game) {

        if (arr.isEmpty())
            throw new IllegalArgumentException("List of possible solutions can't be empty!");

        GameBoard bestValue = null;
        Player player = game.getPlayer();

        for (GameBoard gameBoard : arr) {

            if (bestValue == null) bestValue = gameBoard;

            System.out.println("#   Best score : " + game.getPlayerScore(player, bestValue));

            bestValue = game.getPlayerScore(player, bestValue) < game.getPlayerScore(player, gameBoard)
                    ? gameBoard
                    : bestValue;
        }

        return bestValue;
    }

    private static List<GameBoard> getPossibleTurns(Game game) {

        List<GameBoard> possibleTurns = new ArrayList<>();

        for (int x = 0; x < game.getBoard().rows(); x++) {
            for (int y = 0; y < game.getBoard().columns(); y++) {
                if (game.isMovePossible(x, y)) {
                    System.out.println("Adding x : " + x + " y : " + y + " to possible turns");
                    possibleTurns.add(game.resolveTurn(x, y));
                    System.out.println("@   " + game.getPlayerScore(Player.SECOND_PLAYER, game.resolveTurn(x, y)));
                }
            }
        }

        return possibleTurns;
    }
}

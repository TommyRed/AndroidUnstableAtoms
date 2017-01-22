package com.example.semanticer.unstable.domain;

import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.GameField;
import com.example.semanticer.unstable.domain.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by semanticer on 15.01.2017.
 */

public class GameImpl implements Game {

    private GameBoard gameBoard;
    private Player playerOnTurn;

    public static GameImpl createNew(int rowCount, int columnCount) {
        return new GameImpl(rowCount, columnCount);
    }

    private GameImpl(int rowCount, int columnCount) {

        List<List<GameField>> fields = getFields();

        playerOnTurn = Player.FIRST_PLAYER;

        gameBoard = GameBoard.create(fields);
    }


    private void switchPlayerOnTurn() {
        playerOnTurn = playerOnTurn == Player.FIRST_PLAYER ? Player.SECOND_PLAYER : Player.FIRST_PLAYER;
    }

    @Override
    public GameBoard onMoveMade(int x, int y) {
        if (!isMovePossible(x, y)) {
            throw new IllegalStateException("Impossible to make move to position x: " + x + " y: " + y);
        }

        switch (gameBoard.fields().get(x).get(y).atomCount()){
            case 1:
                gameBoard.fields().get(x).set(y, GameField.create(2, playerOnTurn));
                break;
            case 2:
                gameBoard.fields().get(x).set(y, GameField.create(3, playerOnTurn));
                break;
            case 3:
                gameBoard.fields().get(x).set(y, GameField.create(1, playerOnTurn));
                break;
            default:
                gameBoard.fields().get(x).set(y, GameField.create(1, playerOnTurn));
                break;
        }

        switchPlayerOnTurn();
        return gameBoard;
    }

    @Override
    public boolean isMovePossible(int x, int y) {
        // TODO return if

        if (gameBoard.fields().get(x).get(y).player() == playerOnTurn || gameBoard.fields().get(x).get(y).player() == Player.ANON) return true;

        return false;
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }


    // TODO remove this example method
    public List<List<GameField>> getFields() {
        return Arrays.asList(
                Arrays.asList(GameField.createBlank(), GameField.createBlank(), GameField.createBlank(), GameField.createBlank()),
                Arrays.asList(GameField.createBlank(), GameField.createBlank(), GameField.createBlank(), GameField.createBlank()),
                Arrays.asList(GameField.createBlank(), GameField.createBlank(), GameField.createBlank(), GameField.createBlank()),
                Arrays.asList(GameField.createBlank(), GameField.createBlank(), GameField.createBlank(), GameField.createBlank()),
                Arrays.asList(GameField.createBlank(), GameField.createBlank(), GameField.createBlank(), GameField.createBlank()),
                Arrays.asList(GameField.createBlank(), GameField.createBlank(), GameField.createBlank(), GameField.createBlank())
        );
    }
}

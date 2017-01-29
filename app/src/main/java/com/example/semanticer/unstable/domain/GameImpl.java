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
        playerOnTurn = Player.ANON;
        gameBoard = GameBoard.create(getFields(rowCount, columnCount));
    }


    private void switchPlayerOnTurn() {
        playerOnTurn = playerOnTurn == Player.FIRST_PLAYER ? Player.SECOND_PLAYER : Player.FIRST_PLAYER;
    }

    @Override
    public GameBoard onMoveMade(int x, int y) {
        if (!isMovePossible(x, y)) {
            throw new IllegalStateException("Impossible to make move to position x: " + x + " y: " + y);
        }

        switchPlayerOnTurn();

        return resolveTurn(x, y);
    }

    private GameBoard resolveTurn(int x, int y){

        if(gameBoard.fields().get(x).get(y).atomCount() < 3){
            gameBoard.fields().get(x).set(y, GameField.create(gameBoard.fields().get(x).get(y).atomCount() + 1, playerOnTurn));
        } else {
            System.out.println("----------------------------------------------");
            gameBoard.fields().get(x).set(y, GameField.createBlank());
            if (x > 0){
                System.out.println("# x > 0   x: " + x + " y: " + y);
                resolveTurn(x - 1, y);
            }
            if (x < gameBoard.rows() - 1){
                System.out.println("# x < " + gameBoard.rows() + "   x: " + x + " y: " + y);
                resolveTurn(x + 1, y);
            }
            if (y > 0) {
                System.out.println("# y > 0   x: " + x + " y: " + y);
                resolveTurn(x, y - 1);
            }
            if (y < gameBoard.columns() - 1){
                System.out.println("# y < " + gameBoard.columns() + "   x: " + x + " y: " + y);
                resolveTurn(x, y + 1);
            }
        }

        return gameBoard;
    }

    @Override
    public boolean isMovePossible(int x, int y) {
        return gameBoard.fields().get(x).get(y).player() != playerOnTurn || gameBoard.fields().get(x).get(y).player() == Player.ANON;
    }

    @Override
    public GameBoard getBoard() {
        return gameBoard;
    }

    private List<List<GameField>> getFields(int rows, int columns) {
        List<List<GameField>> board = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            board.add(i, new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                board.get(i).add(j, GameField.createBlank());
            }
        }

        return board;
    }
}

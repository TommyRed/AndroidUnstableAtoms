package com.example.semanticer.unstable.domain;

import android.widget.Toast;

import com.example.semanticer.unstable.domain.ai.Ai;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.GameField;
import com.example.semanticer.unstable.domain.model.Player;
import com.example.semanticer.unstable.presentation.MainActivity;

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
        this.playerOnTurn = Player.FIRST_PLAYER;
        gameBoard = GameBoard.create(getFields(rowCount, columnCount));
    }

    private void switchPlayerOnTurn() {
        playerOnTurn = playerOnTurn == Player.FIRST_PLAYER ? Player.SECOND_PLAYER : Player.FIRST_PLAYER;
    }

    @Override
    public GameBoard onMoveMade(int x, int y, boolean mode) {
        return isMovePossible(x, y) ? (mode ? playerTurn(x, y) : aiTurn(x, y)) : gameBoard;
    }

    private GameBoard aiTurn(int x, int y){

        this.gameBoard = resolveTurn(x, y);

        switchPlayerOnTurn();

        this.gameBoard = Ai.makeTurn(this);

        switchPlayerOnTurn();

        return gameBoard;
    }

    private GameBoard playerTurn(int x, int y){

        GameBoard newGameboard = resolveTurn(x, y);

        switchPlayerOnTurn();

        return newGameboard;
    }

    public GameBoard resolveTurn(int x, int y) {
        if (isValidField(x, y)) {
            if (gameBoard.fields().get(x).get(y).atomCount() < 3) {
                gameBoard.fields().get(x).set(y, GameField.create(gameBoard.fields().get(x).get(y).atomCount() + 1, playerOnTurn));
            } else {
                splashCell(x, y);
            }
        }
        return gameBoard;
    }

    private void splashCell(int x, int y){
        gameBoard.fields().get(x).set(y, GameField.createBlank());
        resolveTurn(x - 1, y);
        resolveTurn(x + 1, y);
        resolveTurn(x, y - 1);
        resolveTurn(x, y + 1);
    }

    @Override
    public int getPlayerScore(Player player, GameBoard board){
        int index = 0;
        for (List<GameField> columns : board.fields()) {
            for (GameField row : columns) {
                index += row.player() == player ? row.atomCount() : 0;
            }
        }
        return index;
    }

    @Override
    public int getPlayerScore(Player player){
        return getPlayerScore(player, gameBoard);
    }

    private boolean isValidField(int x, int y) {
        return x >= 0 && x < gameBoard.rows() && y >= 0 && y < gameBoard.columns();
    }

    @Override
    public boolean isMovePossible(int x, int y) {
        return gameBoard.fields().get(x).get(y).player() == playerOnTurn || gameBoard.fields().get(x).get(y).player() == Player.ANON;
    }

    @Override
    public boolean canPlay() {
        return !((getPlayerScore(Player.FIRST_PLAYER) == 0 && getPlayerScore(Player.SECOND_PLAYER) > 1) ||
                (getPlayerScore(Player.SECOND_PLAYER) == 0 && getPlayerScore(Player.FIRST_PLAYER) > 1));
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

    @Override
    public Player getPlayer() {
        return playerOnTurn;
    }
}

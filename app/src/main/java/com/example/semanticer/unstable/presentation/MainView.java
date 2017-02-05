package com.example.semanticer.unstable.presentation;

import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

/**
 * Created by semanticer on 15.01.2017.
 */
public interface MainView {
    void showGameBoard(GameBoard gameBoard);
    void showPlayer(Player player);
    void showScore(Player firstPlayer, Player secondPlayer, Game game);
    void showWinner(Player player);
}

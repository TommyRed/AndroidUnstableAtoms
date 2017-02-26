package com.example.semanticer.unstable.presentation.game;

import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

/**
 * Created by Rechtig on 07.02.2017.
 */

public interface GameView {
    void showGameBoard(GameBoard gameBoard);
    void showPlayer(Player player);
    void showScore(Player firstPlayer, Player secondPlayer, Game game);
    void showWinner(Player player, Game game);
    void calculateProgress(Player player1, Player player2, Game game);
    void warn(String message);
}

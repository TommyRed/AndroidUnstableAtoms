package com.example.semanticer.unstable.domain;

import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

/**
 * Created by semanticer on 15.01.2017.
 */

public interface Game {
    GameBoard onMoveMade(int x, int y);
    boolean isMovePossible(int x, int y);
    GameBoard getBoard();
    Player getPlayer();
    int getPlayerScore(Player player);
    boolean canPlay();
}

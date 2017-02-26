package com.example.semanticer.unstable.domain;

import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

import java.util.List;

/**
 * Created by semanticer on 15.01.2017.
 */

public interface Game {
    GameBoard onMoveMade(int x, int y, boolean mode);
    boolean isMovePossible(int x, int y);
    GameBoard getBoard();
    Player getPlayer();
    int getPlayerScore(Player player);
    int getPlayerScore(Player player, GameBoard board);
    boolean canPlay();
    GameBoard resolveTurn(int x, int y, GameBoard board);
    // TODO can be reused for List<GameBoard>
    List<String> getHistory();
}

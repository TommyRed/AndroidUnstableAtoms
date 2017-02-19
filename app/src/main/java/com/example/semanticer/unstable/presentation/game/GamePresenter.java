package com.example.semanticer.unstable.presentation.game;

import android.os.Bundle;

import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.GameImpl;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

import nucleus.presenter.RxPresenter;

/**
 * Created by Rechtig on 07.02.2017.
 */

public class GamePresenter extends RxPresenter<GameView> {

    private Game game;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        game = GameImpl.createNew(6, 4);

        view().subscribe(view -> {
            if (view != null) {
                view.showGameBoard(game.getBoard());
                view.showPlayer(Player.FIRST_PLAYER);
                view.showScore(Player.FIRST_PLAYER, Player.SECOND_PLAYER, game);
            }
        });
    }

    public void onMoveMade(int x, int y, boolean mode) {
        if (game.canPlay()) {
            if (!game.isMovePossible(x, y)) {
                view().subscribe(view -> {
                            if (view != null) {
                                view.warn("You can't play here, try again!");
                            }
                        }
                );
            } else {
                GameBoard newGameBoard = game.onMoveMade(x, y, mode);

                view().subscribe(view -> {
                    if (view != null) {
                        view.showGameBoard(newGameBoard);
                        view.showPlayer(game.getPlayer());
                        view.showScore(Player.FIRST_PLAYER, Player.SECOND_PLAYER, game);
                        view.calculateProgress(Player.FIRST_PLAYER, Player.SECOND_PLAYER, game);
                    }
                });

                if (!game.canPlay()) {
                    view().subscribe(view -> {
                        if (view != null) {
                            view.showWinner(game.getPlayerScore(Player.FIRST_PLAYER) > 0 ? Player.FIRST_PLAYER : Player.SECOND_PLAYER);
                        }
                    });
                }
            }
        }
    }
}

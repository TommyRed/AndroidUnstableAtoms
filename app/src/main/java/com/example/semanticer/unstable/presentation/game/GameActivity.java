package com.example.semanticer.unstable.presentation.game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.semanticer.unstable.R;
import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

/**
 * Created by Rechtig on 07.02.2017.
 */
@RequiresPresenter(GamePresenter.class)
public class GameActivity extends NucleusActivity<GamePresenter> implements GameView{

    @BindView(R.id.game_board_layout)
    GameBoardLayout gameBoardLayout;

    @BindView(R.id.currentPlayerTextView)
    TextView currPlayer;

    @BindView(R.id.firstPlayerScore)
    TextView firstPlayerScore;

    @BindView(R.id.secondPlayerScore)
    TextView secondPlayerScore;

    @BindView(R.id.winnerTextView)
    TextView winnerTextView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        ButterKnife.bind(this);

        boolean gameMode = getIntent().getBooleanExtra("Type", false);

        gameBoardLayout.setOnMoveListener((x, y) -> getPresenter().onMoveMade(x, y, gameMode));
    }

    @Override
    public void showGameBoard(GameBoard gameBoard) {
        gameBoardLayout.setBoard(gameBoard);
    }

    @Override
    public void showPlayer(Player player) {
        if (player == null)
            currPlayer.setText(" ");
        else
            currPlayer.setText(player == Player.SECOND_PLAYER ? "Player 2" : "Player 1");
    }

    @Override
    public void calculateProgress(Player player1, Player player2, Game game) {
        float x = game.getPlayerScore(player1);
        float y = game.getPlayerScore(player2);

        int result = (int) Math.floor((x / (x + y)) * 100);

        progressBar.setProgress(result);

    }

    @Override
    public void showScore(Player firstPlayer, Player secondPlayer, Game game) {
        firstPlayerScore.setText(game.getPlayerScore(firstPlayer) + "");
        secondPlayerScore.setText("" + game.getPlayerScore(secondPlayer));
    }

    @Override
    public void showWinner(Player player) {
        winnerTextView.setText(player == Player.SECOND_PLAYER ? "Vyhrál : Player 2" : "Vyhrál : Player 1");
    }

    @Override
    public void warn(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

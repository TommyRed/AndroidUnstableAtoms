package com.example.semanticer.unstable.presentation;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.semanticer.unstable.R;
import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends NucleusActivity<MainPresenter> implements MainView {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gameBoardLayout.setOnMoveListener((x, y) -> getPresenter().onMoveMade(x, y));
//        progressBar.setProgress(50);
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
    public void showScore(Player firstPlayer, Player secondPlayer, Game game) {
        firstPlayerScore.setText(game.getPlayerScore(firstPlayer) + " ");
        secondPlayerScore.setText(" " + game.getPlayerScore(secondPlayer));
    }

    @Override
    public void showWinner(Player player) {
        winnerTextView.setText(player == Player.SECOND_PLAYER ? "Vyhrál : Player 2" : "Vyhrál : Player 1");
    }
}

package com.example.semanticer.unstable.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.semanticer.unstable.R;
import com.example.semanticer.unstable.domain.Game;
import com.example.semanticer.unstable.domain.model.GameBoard;
import com.example.semanticer.unstable.domain.model.Player;
import com.example.semanticer.unstable.presentation.game.GameActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

@RequiresPresenter(MainPresenter.class)
public class MainActivity extends NucleusActivity<MainPresenter> implements MainView {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void startSinglePlayer(View view){
        startGame(false);
    }

    public void startMultiPlayer(View view){
        startGame(true);
    }

    private void startGame(boolean gameType){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("Type", gameType);
        startActivity(intent);
    }
}

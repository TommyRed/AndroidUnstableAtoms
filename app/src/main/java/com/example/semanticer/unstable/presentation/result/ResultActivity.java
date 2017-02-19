package com.example.semanticer.unstable.presentation.result;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.semanticer.unstable.R;
import com.example.semanticer.unstable.presentation.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

/**
 * Created by Rechtig on 15.02.2017.
 */

@RequiresPresenter(ResultPresenter.class)
public class ResultActivity extends NucleusActivity<ResultPresenter> implements ResultView{

    @BindView(R.id.resultTextView)
    TextView winnerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        String winner = getIntent().getStringExtra("winner");

        winnerTextView.setText(winner);
    }


    @Override
    public void resetGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

package com.example.semanticer.unstable.presentation.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.semanticer.unstable.R;
import com.example.semanticer.unstable.presentation.MainActivity;
import com.example.semanticer.unstable.presentation.utils.Adapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActivity;

/**
 * Created by Rechtig on 15.02.2017.
 */

@RequiresPresenter(ResultPresenter.class)
public class ResultActivity extends NucleusActivity<ResultPresenter> implements ResultView{

//    @BindView(R.id.resultTextView)
//    TextView winnerTextView;

    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        List<String> gameHistory = getIntent().getStringArrayListExtra("history");

        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new Adapter(gameHistory);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void resetGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

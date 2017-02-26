package com.example.semanticer.unstable.presentation.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.semanticer.unstable.R;

import java.util.List;

/**
 * Created by Rechtig on 20.02.2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<String> mData;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(TextView x) {
            super(x);
            mTextView = x;
        }
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public Adapter(List<String> data) {
        super();
        mData = data;
    }
}

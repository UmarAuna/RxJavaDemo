package com.rxjavademo.livedatademo1.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPositon;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position){
        mCurrentPositon = position;
        clear();
    }

    public int getCurrentPosition(){
        return mCurrentPositon;
    }


}

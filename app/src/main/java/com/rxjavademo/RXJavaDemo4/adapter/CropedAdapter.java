package com.rxjavademo.RXJavaDemo4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rxjavademo.R;
import com.rxjavademo.RXJavaDemo4.model.CropsItems;

import java.util.ArrayList;
import java.util.List;

public class CropedAdapter extends RecyclerView.Adapter<CropedAdapter.MyViewHolder> {
    List<CropsItems> cropsItems;

    public CropedAdapter(List<CropsItems> cropsItems){
        this.cropsItems = cropsItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crop_list_mvp, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.crop_names.setText(cropsItems.get(position).getName());
    }

    @Override
    public int getItemCount() {
     return cropsItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView crop_names;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            crop_names = itemView.findViewById(R.id.crop_names);
        }
    }
}


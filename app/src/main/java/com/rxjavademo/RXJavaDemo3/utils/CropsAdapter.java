package com.rxjavademo.RXJavaDemo3.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rxjavademo.R;
import com.rxjavademo.RXJavaDemo3.model.CropsItem;
import com.rxjavademo.RXJavaDemo3.model.Results;

import java.util.List;

public class CropsAdapter extends RecyclerView.Adapter<CropsAdapter.ViewHolder> {

    private Context context;
    private List<CropsItem> crop_list;

    public CropsAdapter(Context context, List<CropsItem> crop_list) {
        this.context = context;
        this.crop_list = crop_list;
    }
    @NonNull
    @Override
    public CropsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crop_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CropsAdapter.ViewHolder holder, int position) {
        holder.crop_name.setText(position+1 +" "+ crop_list.get(position).getName());

       /* if(crop_list.get(position).getName().equals("Cassava")){
            holder.crop_name.setText("AUNA");
        }*/
    }

    @Override
    public int getItemCount() {
        if (crop_list.isEmpty()) {
            return 0;
        } else {
            return crop_list.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;
        TextView crop_name;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            crop_name = mView.findViewById(R.id.crop_name);

        }
    }
}

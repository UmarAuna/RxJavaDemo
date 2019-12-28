package com.rxjavademo.livedatademo1.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.RequestBuilder;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
import com.rxjavademo.R;
import com.rxjavademo.livedatademo1.model.Country;

import java.util.List;


public class CountryAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "Countryadapter";

    private List<Country> mCountryList;

    private RequestBuilder<PictureDrawable> requestBuilder;

    public CountryAdapter(List<Country> countryList){
        mCountryList = countryList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_countries_livedata,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
       holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (mCountryList !=null & mCountryList.size() > 0){
            return mCountryList.size();
        }else {
            return 0;
        }
    }

    public  class ViewHolder extends BaseViewHolder{
        ImageView ivThumbnail;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvLink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvLink = itemView.findViewById(R.id.tvLink);
        }


        @Override
        protected void clear() {
            ivThumbnail.setImageDrawable(null);
            tvTitle.setText("");
            tvLink.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final Country mCountry = mCountryList.get(position);

            if (mCountry.getFlag() != null) {
               /* Glide.with(itemView.getContext())
                        .load(mCountry.getFlag())
                        .into(ivThumbnail);*/
                GlideToVectorYou
                        .init()
                        .with(itemView.getContext())
                        .withListener(new GlideToVectorYouListener() {
                            @Override
                            public void onLoadFailed() {
                                Toast.makeText(itemView.getContext(), "Load failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResourceReady() {
                                Toast.makeText(itemView.getContext(), "Image ready", Toast.LENGTH_SHORT).show();
                            }
                        })
                        //.setPlaceHolder(placeholderLoading, placeholderError)
                        .load(Uri.parse(mCountry.getFlag()), ivThumbnail);
            }

            if (mCountry.getName() != null) {
                tvTitle.setText(mCountry.getName());
            }

            if (mCountry.getCapital() != null) {
                tvDescription.setText(mCountry.getCapital());
            }
            if(mCountry.getDemonym() != null){
                tvLink.setText(mCountry.getDemonym());
            }


            tvLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(mCountry.getCapital()));
                    }catch (Exception e){
                        Log.e(TAG, "onClick: Image url is not correct");
                    }
                }
            });



            }
        }
}

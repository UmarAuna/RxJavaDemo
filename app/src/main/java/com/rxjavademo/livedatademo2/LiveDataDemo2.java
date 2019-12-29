package com.rxjavademo.livedatademo2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rxjavademo.R;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

public class LiveDataDemo2 extends AppCompatActivity {
    //https://www.journaldev.com/21168/android-livedata
    //Viewmodel, SQLite, LiveData
    private FavAdapter mFavAdapter;
    private  FavouritesViewModel mFavViewModel;
    private List<FavouritesModel> mFav;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_demo2);
        fab = findViewById(R.id.fab);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFavViewModel = ViewModelProviders.of(this).get(FavouritesViewModel.class);
        final Observer<List<FavouritesModel>> favObserver = new Observer<List<FavouritesModel>>() {
            @Override
            public void onChanged(List<FavouritesModel> updatedList) {
                if(mFav == null){
                    mFav = updatedList;
                    mFavAdapter = new FavAdapter();
                    recyclerView.setAdapter(mFavAdapter);
                }else{
                    DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                        @Override
                        public int getOldListSize() {
                            return mFav.size();
                        }

                        @Override
                        public int getNewListSize() {
                            return updatedList.size();
                        }

                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            return mFav.get(oldItemPosition).mId == updatedList.get(newItemPosition).mId;
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            FavouritesModel oldFav = mFav.get(oldItemPosition);
                            FavouritesModel newFav = updatedList.get(newItemPosition);
                            return oldFav.equals(newFav);
                        }
                    });
                    result.dispatchUpdatesTo(mFavAdapter);
                    mFav = updatedList;
                }
            }
        };

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText inUrl = new EditText(LiveDataDemo2.this);
                AlertDialog dialog = new AlertDialog.Builder(LiveDataDemo2.this)
                        .setTitle("New favourite")
                        .setMessage("Add a url link below")
                        .setView(inUrl)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url = String.valueOf(inUrl.getText());
                                long date = (new Date()).getTime();

                               mFavViewModel.addFav(url, date);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });

        mFavViewModel.getFavs().observe(this, favObserver);
    }

    public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {

        @NotNull
        @Override
        public FavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav_list_row, parent, false);
            return new FavViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(FavViewHolder holder, int position) {
            FavouritesModel favourites = mFav.get(position);
            holder.mTxtUrl.setText(favourites.mUrl);
            holder.mTxtDate.setText((new Date(favourites.mDate).toString()));
        }

        @Override
        public int getItemCount() {
            return mFav.size();
        }

        class FavViewHolder extends RecyclerView.ViewHolder {

            TextView mTxtUrl;
            TextView mTxtDate;

            FavViewHolder(View itemView) {
                super(itemView);
                mTxtUrl = itemView.findViewById(R.id.tvUrl);
                mTxtDate = itemView.findViewById(R.id.tvDate);
                ImageButton btnDelete = itemView.findViewById(R.id.btnDelete);
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = getAdapterPosition();
                        FavouritesModel favourites = mFav.get(pos);
                        mFavViewModel.removeFav(favourites.mId);
                    }
                });
            }
        }
    }
}



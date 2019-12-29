package com.rxjavademo.livedatademo2;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.rxjavademo.livedatademo2.db.DbSettings;
import com.rxjavademo.livedatademo2.db.FavouritesDbHelper;

import java.util.ArrayList;
import java.util.List;

public class FavouritesViewModel extends AndroidViewModel {

    private FavouritesDbHelper mFavHelper;
    private MutableLiveData<List<FavouritesModel>> mFavs;

    public FavouritesViewModel(@NonNull Application application) {
        super(application);
        mFavHelper = new FavouritesDbHelper(application);
    }

    public MutableLiveData<List<FavouritesModel>> getFavs(){
        if(mFavs ==null){
            mFavs = new MutableLiveData<>();
            loadFavs();
        }
        return mFavs;
    }

    private void loadFavs(){
        List<FavouritesModel> newFavs = new ArrayList<>();
        SQLiteDatabase db = mFavHelper.getReadableDatabase();
        Cursor cursor = db.query(DbSettings.DBEntry.TABLE,
                new String[]{
                        DbSettings.DBEntry._ID,
                        DbSettings.DBEntry.COL_FAV_URL,
                        DbSettings.DBEntry.COL_FAV_DATE
                },null, null, null, null, null, null);
        while (cursor.moveToNext()){
            int idxId = cursor.getColumnIndex(DbSettings.DBEntry._ID);
            int idxUrl = cursor.getColumnIndex(DbSettings.DBEntry.COL_FAV_URL);
            int idxDate = cursor.getColumnIndex(DbSettings.DBEntry.COL_FAV_DATE);
            newFavs.add( new FavouritesModel(cursor.getLong(idxId), cursor.getString(idxUrl), cursor.getLong(idxDate)));
        }
        cursor.close();
        db.close();
        mFavs.setValue(newFavs);
    }

    public void addFav(String url, long date){
        SQLiteDatabase db = mFavHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbSettings.DBEntry.COL_FAV_URL, url);
        values.put(DbSettings.DBEntry.COL_FAV_DATE, date);
        long id = db.insertWithOnConflict(DbSettings.DBEntry.TABLE,
                null,
                values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();

        List<FavouritesModel> favouritesModels = mFavs.getValue();

        ArrayList<FavouritesModel> clonedFavs;
        if(favouritesModels == null){
            clonedFavs = new ArrayList<>();
        }else{
            clonedFavs = new ArrayList<>(favouritesModels.size());
            for(int i=0; i< favouritesModels.size(); i++){
                clonedFavs.add(new FavouritesModel(favouritesModels.get(i)));
            }
        }
        FavouritesModel fav = new FavouritesModel(id, url, date);
        clonedFavs.add(fav);
        mFavs.setValue(clonedFavs);
    }

    public void removeFav(long id){
        SQLiteDatabase db = mFavHelper.getWritableDatabase();
        db.delete(
                DbSettings.DBEntry.TABLE,
                DbSettings.DBEntry._ID + " = ?",
                new String[] {Long.toString(id)}
        );
        db.close();

        List<FavouritesModel> favs = mFavs.getValue();
        ArrayList<FavouritesModel> clonedFavs = new ArrayList<>(favs.size());
        for(int i=0; i<favs.size(); i++){
            clonedFavs.add(new FavouritesModel(favs.get(i)));
        }

        int index = -1;
        for(int i=0; i<clonedFavs.size(); i++){
            FavouritesModel favouritesModel = clonedFavs.get(i);
            if(favouritesModel.mId == id){
                index = i;
            }
        }

        if(index != -1){
            clonedFavs.remove(index);
        }
        mFavs.setValue(clonedFavs);

    }
}
